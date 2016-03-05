package com.limplungs.invcalc.containers.slots;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMirror extends Slot
{
	public SlotMirror(IInventory inv, int x, int y, int z)
	{
		super(inv, x, y, z);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		if (itemstack.getItem() == new ItemStack(Blocks.heavy_weighted_pressure_plate).getItem())
			return true;
		else
			return false;
	}
}
