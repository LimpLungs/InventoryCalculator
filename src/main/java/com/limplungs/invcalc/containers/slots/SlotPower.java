package com.limplungs.invcalc.containers.slots;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPower extends Slot
{
	public SlotPower(IInventory inv, int x, int y, int z)
	{
		super(inv, x, y, z);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		if (itemstack.getItem() == Items.redstone || itemstack.getItem() == new ItemStack(Blocks.redstone_block).getItem())
			return true;
		else
			return false;
	}
}
