package com.limplungs.invcalc.inventories;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;

public class InventoryBattery implements IInventory
{
	private int battery = 0;
	private int batMax = 0;

	public EntityPlayer player = Minecraft.getMinecraft().thePlayer;

	public InventoryBattery(ItemStack stack, EntityPlayer player)
	{
		this.player = player;

		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		else
		{
			readFromNBT(stack.getTagCompound());
		}
	}

	@Override
	public String getName()
	{
		return "Battery";
	}

	@Override
	public boolean hasCustomName()
	{
		return true;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeInventory()
	{
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 0;
	}

	@Override
	public void markDirty()
	{
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return false;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("battery", this.battery);
		compound.setInteger("batMax", this.batMax);
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		this.battery = compound.getInteger("battery");
		this.batMax = compound.getInteger("batMax");
	}
	
	public void setBatMax(int value)
	{
		this.batMax = value;
	}
	
	public void setBatLevel(int value)
	{
		this.battery = value;
	}
	
	public int getBatMax()
	{
		return this.batMax;
	}

	public int getBatLevel()
	{
		return this.battery;
	}
}
