package io.github.GlacialSkyfarer.notcoppertools.block.entity;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import io.github.GlacialSkyfarer.notcoppertools.recipe.AnvilRecipeHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class AnvilBlockEntity extends BlockEntity implements Inventory {

    private ItemStack[] inventory = new ItemStack[3];

    @Override
    public int size() { return 3; }

    @Override
    public ItemStack getStack(int slot) { return inventory[slot]; }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (inventory[slot] != null) {
            if (inventory[slot].count <= amount) {
                ItemStack itemInSlot = inventory[slot];
                inventory[slot] = null;
                checkItems();
                markDirty();
                return itemInSlot;
            } else {
                ItemStack split = inventory[slot].split(amount);
                if (inventory[slot].count == 0) {
                    inventory[slot] = null;
                }
                checkItems();
                this.markDirty();
                return split;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }
        checkItems();
        this.markDirty();
    }

    public void clearStacks() {
        for (int i = 0; i < size(); i++) {
            inventory[i] = null;
        }
        this.markDirty();
    }

    @Override
    public String getName() {
        return "Anvil";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.getSquaredDistance((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        NbtList inventoryNbt = new NbtList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NbtCompound slotCompound = new NbtCompound();
                slotCompound.putByte("Slot", (byte) i);
                inventory[i].writeNbt(slotCompound);
                inventoryNbt.add(slotCompound);
            }
        }

        nbt.put("Items", inventoryNbt);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList inventoryList = nbt.getList("Items");
        inventory = new ItemStack[this.size()];

        for(int i = 0; i < inventoryList.size(); ++i) {
            NbtCompound itemCompound = (NbtCompound)inventoryList.get(i);
            byte slot = itemCompound.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = new ItemStack(itemCompound);
            }
        }
    }

    private void checkItems() {

        ItemStack material = getStack(0);
        ItemStack gear = getStack(1);

        if (material == null || gear == null) {
            inventory[2] = null;
            return;
        }

        Item gearItem = gear.getItem();
        Item materialItem = material.getItem();

        if (tryCombineTools(material, gear, gearItem, materialItem)) return;
        if (tryUseMaterial(gear, gearItem, materialItem)) return;

        if (gear.getDamage() <= 0 && material.getDamage() <= 0) {
            ItemStack result = AnvilRecipeHandler.getItem(gearItem, materialItem);

            if (result != null) {
                inventory[2] = result;
                return;
            }
        }
    }

    private boolean tryCombineTools(ItemStack material, ItemStack gear, Item gearItem, Item materialItem) {
        if (materialItem == gearItem && material.getDamage() > 0) {
            inventory[2] = gear.copy();
            inventory[2].setDamage(Math.max(
                    gear.getDamage() - (material.getMaxDamage() - material.getDamage())
                            - gear.getMaxDamage() / 20,
                    0
            ));
            return true;
        }
        return false;
    }

    private boolean tryUseMaterial (ItemStack gear, Item gearItem, Item materialItem) {
        if (gearItem instanceof IHasRepairMaterial hasRepairMaterial && materialItem == hasRepairMaterial.gamma_173$getRepairMaterial()) {
            float repairAmount = getRepairAmount(gearItem);
            if (repairAmount == 0f) return false;
            inventory[2] = gear.copy();
            inventory[2].setDamage(Math.max(
                    gear.getDamage() - (int)(gear.getMaxDamage() * getRepairAmount(gearItem)),
                    0
            ));
            return true;
        }
        return false;
    }

    private float getRepairAmount(Item item) {

        if (item instanceof PickaxeItem ||
                item instanceof AxeItem) return 0.4f;
        if (item instanceof HoeItem ||
                item instanceof SwordItem ||
                item instanceof ShearsItem ||
                item instanceof FishingRodItem) return 0.65f;
        if (item instanceof ShovelItem ||
        item instanceof FlintAndSteel) return 1f;

        if (item instanceof ArmorItem armor) {
            int slot = armor.equipmentSlot;
            if (slot == 0) return 0.25f;
            if (slot == 1) return 0.17f;
            if (slot == 2) return 0.19f;
            if (slot == 3) return 0.34f;
        }

        return 0f;
    }

}
