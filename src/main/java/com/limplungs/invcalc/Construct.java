package com.limplungs.invcalc;

import com.limplungs.invcalc.help.Reference;
import com.limplungs.invcalc.items.ItemBattery;
import com.limplungs.invcalc.items.ItemCalculatorBasic;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Construct
{
	public static Item calculatorBasic;
	public static Item battery10000;
	
	public static void createCalculators()
	{
		calculatorBasic = new ItemCalculatorBasic(new String("calculatorBasic"), InventoryCalculator.tabInvCalc);
		
		battery10000 = new ItemBattery(new String("battery10000"), InventoryCalculator.tabInvCalc, 10000);
	}

	public static void registerModels(RenderItem renderItem) 
	{
		renderItem.getItemModelMesher().register(calculatorBasic, 0, new ModelResourceLocation(Reference.MODID + ":" + ((ItemCalculatorBasic) calculatorBasic).getName(), "inventory"));
		renderItem.getItemModelMesher().register(battery10000, 0, new ModelResourceLocation(Reference.MODID + ":" + ((ItemBattery) battery10000).getName(), "inventory"));
	}
	
	public static void craftCalculators()
	{
		//GameRegistry.addRecipe(NoMirrorRecipe.addNoMirrorRecipe(new ItemStack(Construct.calculatorBasic), new Object[] {"IGI", "IBI", "CRT" , 'I', Items.iron_ingot, 'G', Blocks.glass, 'B', Blocks.stone_button, 'C', Items.comparator, 'R', Items.redstone, 'T', Items.repeater}));
		GameRegistry.addRecipe(new ItemStack(Construct.calculatorBasic), new Object[] {"IGI", "IBI", "RRR" , 'I', Items.iron_ingot, 'G', Blocks.glass, 'B', Blocks.stone_button, 'R', Items.redstone});
		GameRegistry.addRecipe(new ItemStack(Construct.battery10000), new Object[] {"WWW", "GRG", "WWW" , 'W', Blocks.heavy_weighted_pressure_plate, 'G', Items.gold_nugget, 'R', Items.redstone});
	}
}
