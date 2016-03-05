package com.limplungs.invcalc.containers.slots;

import com.limplungs.invcalc.items.ItemBattery;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBattery extends Slot
{
	public SlotBattery(IInventory inv, int x, int y, int z)
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
		if (itemstack.getItem() instanceof ItemBattery)
			return true;
		else
			return false;
	}
}
