package com.limplungs.invcalc.containers;

import com.limplungs.invcalc.containers.slots.SlotBattery;
import com.limplungs.invcalc.containers.slots.SlotClock;
import com.limplungs.invcalc.containers.slots.SlotMirror;
import com.limplungs.invcalc.containers.slots.SlotPower;
import com.limplungs.invcalc.containers.slots.SlotSolar;
import com.limplungs.invcalc.inventories.InventoryCalculatorBasic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCalculatorBasic extends Container
{
	public final InventoryCalculatorBasic calculator;
	private int battery = 0;
	
	
	
	public ContainerCalculatorBasic(EntityPlayer player, InventoryCalculatorBasic calculator)
	{
		this.calculator = calculator;
		
		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 9 + 20 * i, 89));
		}

		for (int j = 0; j < this.calculator.getSizeInventory(); j++)
		{
			if (j == 0)
				this.addSlotToContainer(new SlotPower(calculator, j, 97, 58));
			
			else if (j == 1)
				this.addSlotToContainer(new SlotBattery(calculator, j, 116, 58));
			
			else if (j == 2)
				this.addSlotToContainer(new SlotSolar(calculator, j, 135, 58));
			
			else if (j == 3)
				this.addSlotToContainer(new SlotClock(calculator, j, 154, 58));
			
			else if (j == 4)
				this.addSlotToContainer(new SlotMirror(calculator, j, 173, 58));
			
		}
	}

	
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.battery != this.calculator.battery)
			{
				icrafting.sendProgressBarUpdate(this, 0, (int) this.calculator.battery);
			}
		}
		
		this.battery = this.calculator.battery;

		this.calculator.updateBooleans();
		
		this.calculator.updateFuelStack();
		
		this.calculator.batteryIncSolar();
		this.calculator.batteryIncBattery();
		this.calculator.batteryDecClock();
		this.calculator.batteryDecUsage();
	}

	
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int index, int updatedNumDetectSendChanges)
	{
		if (index == 0)
		{
			this.calculator.battery = updatedNumDetectSendChanges;
		}
	}
	
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		return null;
	}
	
	
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) 
	{
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) 
		{
			return null;
		}
		
		return super.slotClick(slot, button, flag, player);
	}
}
