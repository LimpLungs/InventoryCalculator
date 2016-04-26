package com.limplungs.invcalc.items;

import java.util.List;
import com.limplungs.invcalc.help.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBattery extends Item
{
	private final String name;
	int batteryMax;

	public ItemBattery(String name, CreativeTabs tab, int batMax)
	{
		GameRegistry.registerItem(this, name);
		this.name = name;
		this.setUnlocalizedName(Reference.MODID + "_" + name);
		this.setCreativeTab(tab);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		
		this.batteryMax = batMax;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if (stack.hasTagCompound())
		{
			stack.getTagCompound().setInteger("batMax", this.batteryMax);
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer player, final List list, boolean par4)
	{
		list.add(EnumChatFormatting.DARK_RED + "Battery Level: ");

		if (stack.hasTagCompound())
		{
			list.add(stack.getTagCompound().getInteger("battery") / 100 + "." + stack.getTagCompound().getInteger("battery") % 100 + "%");
		}
	}
}
