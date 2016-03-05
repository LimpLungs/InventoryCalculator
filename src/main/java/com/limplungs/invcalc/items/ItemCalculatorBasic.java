package com.limplungs.invcalc.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.limplungs.invcalc.InventoryCalculator;
import com.limplungs.invcalc.handlers.GUIHandler;
import com.limplungs.invcalc.help.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCalculatorBasic extends Item
{
	private final String name;
	
	public ItemCalculatorBasic(String name, CreativeTabs tab)
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
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
				player.openGui(InventoryCalculator.INSTANCE, GUIHandler.ID_GUICALCULATORBASIC, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		
		return itemstack;
	}
	
	
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 1;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, final List list, boolean par4)
	{
		list.add(EnumChatFormatting.DARK_RED + "Battery Level: ");
		
		if (stack.hasTagCompound())
			list.add(stack.getTagCompound().getInteger("battery") / 100 + "." + stack.getTagCompound().getInteger("battery") % 100 + "%");
		
		
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			list.add(EnumChatFormatting.DARK_PURPLE + "Hold SHIFT");

		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add(EnumChatFormatting.DARK_AQUA + "[WIP] " +
					 EnumChatFormatting.DARK_GRAY + "Modular " + 
		             EnumChatFormatting.DARK_AQUA + "Basic Calculator " + 
					 EnumChatFormatting.DARK_GRAY + "that can do simple,");
			list.add(EnumChatFormatting.DARK_GRAY + "Minecraft useful math. Use " +
					 EnumChatFormatting.DARK_AQUA + "Calculator upgrades ");
			list.add(EnumChatFormatting.DARK_GRAY + "to add other functions!");
		}
	}
}
