package com.limplungs.invcalc.items;

import java.util.List;

import com.limplungs.invcalc.help.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBattery extends Item
{
	private final String name;
	
	public ItemBattery(String name, CreativeTabs tab)
	{
		GameRegistry.registerItem(this, name);
		this.name = name;
		this.setUnlocalizedName(Reference.MODID + "_" + name);
		this.setCreativeTab(tab);
	    this.setMaxStackSize(1);
	    this.setHasSubtypes(true);
	}

	
	
	public String getName()
	{
		return name;
	}
	
	
	
	@Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) 
	{
		
	}
	
	
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 1;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer player, final List list, boolean par4)
	{
		list.add(EnumChatFormatting.DARK_RED + "Battery Level: " + Integer.toString(this.getMaxDamage(stack) - this.getDamage(stack)));
	}
}
