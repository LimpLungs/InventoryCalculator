package com.limplungs.invcalc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.limplungs.invcalc.handlers.GUIHandler;
import com.limplungs.invcalc.help.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class InventoryCalculator
{
	@Instance(Reference.MODID)
	public static InventoryCalculator INSTANCE;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(InventoryCalculator.INSTANCE, new GUIHandler());
		
		Construct.createCalculators();
	}


	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
		{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			
			Construct.registerModels(renderItem);
			
		}
		
		// Recipes
		Construct.craftCalculators();
	}
	
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	
	
	public static CreativeTabs tabInvCalc = new CreativeTabs("tabInvCalc")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return new ItemStack(Construct.calculatorBasic).getItem();
		}
	};
}
