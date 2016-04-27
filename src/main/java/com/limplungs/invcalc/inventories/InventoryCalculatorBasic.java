package com.limplungs.invcalc.inventories;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;

public class InventoryCalculatorBasic implements IInventory
{

	private ItemStack[] itemslots = new ItemStack[5];

	public int battery = 0;
	public int batMax = 10000;

	public boolean hasBattery = false;
	public boolean hasMirror = false;
	public boolean hasSolar = false;
	public boolean hasClock = false;

	public EntityPlayer player = Minecraft.getMinecraft().thePlayer;

	public InventoryCalculatorBasic(ItemStack stack, EntityPlayer player)
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
		return "iC-Basic";
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
		return itemslots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return itemslots[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		ItemStack itemstack = getStackInSlot(slot);

		if (itemstack != null)
		{
			if (itemstack.stackSize <= count)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				itemstack = itemstack.splitStack(count);

				markDirty();
			}
		}

		return itemstack;
	}

	@Override
	public ItemStack removeStackFromSlot(int i)
	{
		ItemStack itemstack = getStackInSlot(i);

		setInventorySlotContents(i, null);

		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		itemslots[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}

		markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return true;
	}

	@Override
	public void markDirty()
	{
		writeToNBT(player.inventory.getCurrentItem().getTagCompound());

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
			{
				itemslots[i] = null;
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getField(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub

	}

	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack stack = this.getStackInSlot(i);

			if (stack != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag("Items", items);

		compound.setInteger("battery", this.battery);
		compound.setInteger("batMax", this.batMax);

		compound.setBoolean("hasBattery", this.hasBattery);
		compound.setBoolean("hasMirror", this.hasMirror);
		compound.setBoolean("hasSolar", this.hasSolar);
		compound.setBoolean("hasClock", this.hasClock);
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		NBTTagList items = compound.getTagList("Items", compound.getId());

		for (int i = 0; i < items.tagCount(); i++)
		{
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");

			if (slot >= 0 && slot < this.getSizeInventory())
			{
				this.itemslots[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}

		this.battery = compound.getInteger("battery");
		this.batMax = compound.getInteger("batMax");

		this.hasBattery = compound.getBoolean("hasBattery");
		this.hasMirror = compound.getBoolean("hasMirror");
		this.hasSolar = compound.getBoolean("hasSolar");
		this.hasClock = compound.getBoolean("hasClock");
	}

	// Runs a standard usage cycle.
	public void batteryDecUsage()
	{
		this.battery -= 1;

		if (this.battery < 0)
		{
			this.battery = 0;
		}

		this.markDirty();
	}

	// Increases Calculator battery using up some of the actual battery.
	public void batteryIncBattery()
	{
		if (this.hasBattery && this.getStackInSlot(1).getTagCompound().getInteger("battery") >= 5 && !this.hasSolar)
		{
			int used = 0;

			if (this.batMax - this.battery >= 5)
			{
				used = 5;
			}
			else
				used = this.batMax - this.battery;

			this.battery += used;
			this.getStackInSlot(1).getTagCompound().setInteger("battery", this.getStackInSlot(1).getTagCompound().getInteger("battery") - used);

			this.markDirty();
		}
	}

	// Increases Calculator battery using solar power, and stores extra in
	// battery.
	public void batteryIncSolar()
	{
		if (this.hasSolar)
		{
			if (this.battery < this.batMax)
			{
				this.battery += getLevelSolar();
			}
			else
			{
				this.battery = this.batMax;

				// Increases extra battery if calculator battery full
				if (this.hasBattery)
				{
					if (this.getStackInSlot(1).getTagCompound().getInteger("battery") < this.getStackInSlot(1).getTagCompound().getInteger("batMax"))
					{
						this.getStackInSlot(1).getTagCompound().setInteger("battery", this.getStackInSlot(1).getTagCompound().getInteger("battery") + getLevelSolar());
					}
					else
					{
						this.getStackInSlot(1).getTagCompound().setInteger("battery", this.getStackInSlot(1).getTagCompound().getInteger("batMax"));
					}
				}
			}

			this.markDirty();
		}
	}

	// Gets the current solar power for calculator
	public int getLevelSolar()
	{
		int solar = 0;
		
		long time = Minecraft.getMinecraft().theWorld.getWorldTime();
		
		if (time >= 23000 || time < 12500)
			solar = 2;
		
		if (time >= 23500 || time < 12000)
			solar = 3;
		
		if (time >= 24000)
			time -= 24000;
		
		if (time >= 0 && time < 11500)
			solar = 4;
		
		if (time >= 500 && time < 11000)
			solar = 5;
		
		if (time >= 1000 && time < 10500)
			solar = 6;
		
		if (Minecraft.getMinecraft().theWorld.isRaining())
			solar -= 1;
		
		if (solar < 0)
			solar = 0;
		
		return solar;
	}

	// Decreases battery level if clock is active.
	public void batteryDecClock()
	{
		if (this.hasClock)
		{
			this.batteryDecUsage();
		}

		this.markDirty();
	}

	// Updates the fuel stack for the battery, both internal and extra.
	public void updateFuelStack()
	{
		if (!this.hasBattery && !this.hasSolar && this.getStackInSlot(0) != null && this.getStackInSlot(0) != new ItemStack(Blocks.air))
		{
			if (this.battery <= 1000 && this.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.redstone_block))
			{
				this.decrStackSize(0, 1);
				this.battery += 9000;
			}
			else if (this.battery <= 9000 && this.getStackInSlot(0).getItem() == Items.redstone)
			{
				this.decrStackSize(0, 1);
				this.battery += 1000;
			}
		}

		else if (this.hasBattery && this.getStackInSlot(0) != null && this.getStackInSlot(0) != new ItemStack(Blocks.air))
		{
			if (this.getStackInSlot(1).getTagCompound().getInteger("battery") <= 1000 && this.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.redstone_block))
			{
				this.decrStackSize(0, 1);
				this.getStackInSlot(1).getTagCompound().setInteger("battery", this.getStackInSlot(1).getTagCompound().getInteger("battery") + 9000);
			}
			else if (this.getStackInSlot(1).getTagCompound().getInteger("battery") <= 9000 && this.getStackInSlot(0).getItem() == Items.redstone)
			{
				this.decrStackSize(0, 1);
				this.getStackInSlot(1).getTagCompound().setInteger("battery", this.getStackInSlot(1).getTagCompound().getInteger("battery") + 1000);
			}
		}

		this.markDirty();
	}

	public void updateBooleans()
	{
		// hasSolar
		if (this.getStackInSlot(2) != null && this.getStackInSlot(2) != new ItemStack(Blocks.air))
		{
			this.hasSolar = true;
		}
		else
		{
			this.hasSolar = false;
		}

		// hasBattery
		if (this.getStackInSlot(1) != null && this.getStackInSlot(1) != new ItemStack(Blocks.air))
		{
			if (this.getStackInSlot(1).hasTagCompound())
				this.hasBattery = true;
		}
		else
		{
			this.hasBattery = false;
		}

		// hasClock
		if (this.getStackInSlot(3) != null && this.getStackInSlot(3) != new ItemStack(Blocks.air))
		{
			this.hasClock = true;
		}
		else
		{
			this.hasClock = false;
		}

		// hasMirror
		if (this.getStackInSlot(4) != null && this.getStackInSlot(4) != new ItemStack(Blocks.air))
		{
			this.hasMirror = true;
		}
		else
		{
			this.hasMirror = false;
		}

		this.markDirty();
	}
}
