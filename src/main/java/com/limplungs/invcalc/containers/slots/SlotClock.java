package com.limplungs.invcalc.containers.slots;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClock extends Slot
{
	public SlotClock(IInventory inv, int x, int y, int z)
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
		if (itemstack.getItem() == Items.clock)
			return true;
		else
			return false;
	}
}
