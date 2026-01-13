package io.github.GlacialSkyfarer.notcoppertools.block.entity;

import io.github.GlacialSkyfarer.notcoppertools.recipe.StonecutterRecipeHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class StonecutterBlockEntity extends BlockEntity implements Inventory {

    private ItemStack[] inventory = new ItemStack[21];

    @Override
    public int size() { return 21; }

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
        return "Stonecutter";
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

        ItemStack source = getStack(0);

        if (source == null) {
            setResults(null);
            return;
        }
        ItemStack[] result = StonecutterRecipeHandler.getItems(source.getItem());

        if (result != null) {
            setResults(result);
            return;
        }
    }

    private void setResults(ItemStack[] results) {
        for (int i = 1; i < inventory.length; i++) {
            if (results == null || i >= results.length + 1) {
                inventory[i] = null;
                continue;
            }
            inventory[i] = results[i - 1];
        }
    }

}
