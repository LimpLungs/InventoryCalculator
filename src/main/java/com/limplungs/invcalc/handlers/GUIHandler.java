package com.limplungs.invcalc.handlers;

import com.limplungs.invcalc.containers.ContainerCalculatorBasic;
import com.limplungs.invcalc.gui.GUICalculatorBasic;
import com.limplungs.invcalc.inventories.InventoryCalculatorBasic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler
{

	public static final int ID_GUICALCULATORBASIC = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == ID_GUICALCULATORBASIC && !world.isRemote)
		{
			return new ContainerCalculatorBasic(player, new InventoryCalculatorBasic(player.getHeldItem(), player));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == ID_GUICALCULATORBASIC && world.isRemote)
		{
			return new GUICalculatorBasic(new ContainerCalculatorBasic(player, new InventoryCalculatorBasic(player.getHeldItem(), player)));
		}
		
		return null;
	}

}
