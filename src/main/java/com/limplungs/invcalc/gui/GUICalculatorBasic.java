package com.limplungs.invcalc.gui;

import com.limplungs.invcalc.containers.ContainerCalculatorBasic;
import com.limplungs.invcalc.help.Reference;
import com.limplungs.invcalc.inventories.InventoryCalculatorBasic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GUICalculatorBasic extends GuiContainer
{
	
	private final InventoryCalculatorBasic calculator;
	
	private double batPerc = 0.1;
	private int sun = 0;
	
	
	public GUICalculatorBasic(ContainerCalculatorBasic calculator)
	{
		super(calculator);

		this.calculator = calculator.calculator;

		this.xSize = 194;
		this.ySize = 108;
		this.guiLeft = (this.width / 2) - 97;
		this.guiTop = this.height - 108;
	}

	ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/calculators/calcbasic.png");
	
	// TODO: TODO: TODO: // Add Mirror and recharged battery gui stuff.
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		this.xSize = 194;
		this.ySize = 108;
		this.guiLeft = (this.width / 2) - 97;
		this.guiTop = this.height - 108;

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 194, 108);
		
		this.batPerc = ((double) this.calculator.battery) / ((double) this.calculator.batMax) * 100.0;
		this.sun = this.calculator.getLevelSolar();
		
		if      (this.batPerc > 90)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 21, 10);
		else if (this.batPerc > 80)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 19, 10);
		else if (this.batPerc > 70)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 17, 10);
		else if (this.batPerc > 60)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 15, 10);
		else if (this.batPerc > 50)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 13, 10);
		else if (this.batPerc > 40)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0, 11, 10);
		else if (this.batPerc > 30)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0,  9, 10);
		else if (this.batPerc > 20)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0,  7, 10);
		else if (this.batPerc > 10)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0,  5, 10);
		else if (this.batPerc >  0)
			drawTexturedModalRect(this.guiLeft + 166, this.guiTop + 77, 195, 0,  3, 10);
		
		if (this.sun > 4)
			drawTexturedModalRect(this.guiLeft + 114, this.guiTop + 12, 195, 12,  41, 46);
		else if (this.sun > 3)
			drawTexturedModalRect(this.guiLeft + 114, this.guiTop + 17, 195, 17,  41, 41);
		else if (this.sun > 2)
			drawTexturedModalRect(this.guiLeft + 114, this.guiTop + 27, 195, 27,  41, 31);
		else if (this.sun > 1)
			drawTexturedModalRect(this.guiLeft + 114, this.guiTop + 37, 195, 37,  41, 21);
		else if (this.sun > 0)
			drawTexturedModalRect(this.guiLeft + 114, this.guiTop + 47, 195, 47,  41, 11);
    }

	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		this.xSize = 194;
		this.ySize = 108;
		this.guiLeft = (this.width / 2) - 97;
		this.guiTop = this.height - 108;
	}
	
	
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		if (this.calculator.battery > 0)
		{
			if (Minecraft.getMinecraft().theWorld.isRemote)
			{
				if (button.id == 10)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("0");
				
				if (button.id == 11)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("1");
				
				if (button.id == 12)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("2");
				
				if (button.id == 13)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("3");
			
				if (button.id == 14)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("4");
			
				if (button.id == 15)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("5");
			
				if (button.id == 16)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("6");
			
				if (button.id == 17)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("7");
			
				if (button.id == 18)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("8");
			
				if (button.id == 19)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("9");
				
				if (button.id == 20)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("+");
				
				if (button.id == 21)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("-");
				
				if (button.id == 22)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("*");
				
				if (button.id == 23)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("\\");
				
				if (button.id == 24)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("=");
			}
		}
	}
	
	
	
	@Override
	public void initGui()
	{
		super.initGui();

		this.xSize = 194;
		this.ySize = 108;
		this.guiLeft = (this.width / 2) - 97;
		this.guiTop = this.height - 108;
		
		if (Minecraft.getMinecraft().theWorld.isRemote)
		{
			this.buttonList.add(new GuiButton(17, this.guiLeft +  2, this.guiTop +  3, 20, 20, "7"));
			this.buttonList.add(new GuiButton(18, this.guiLeft + 23, this.guiTop +  3, 20, 20, "8"));
			this.buttonList.add(new GuiButton(19, this.guiLeft + 44, this.guiTop +  3, 20, 20, "9"));
			this.buttonList.add(new GuiButton(20, this.guiLeft + 65, this.guiTop +  3, 29, 20, "ADD"));
			this.buttonList.add(new GuiButton(14, this.guiLeft +  2, this.guiTop + 24, 20, 20, "4"));
			this.buttonList.add(new GuiButton(15, this.guiLeft + 23, this.guiTop + 24, 20, 20, "5"));
			this.buttonList.add(new GuiButton(16, this.guiLeft + 44, this.guiTop + 24, 20, 20, "6"));
			this.buttonList.add(new GuiButton(21, this.guiLeft + 65, this.guiTop + 24, 29, 20, "SUB"));
			this.buttonList.add(new GuiButton(11, this.guiLeft +  2, this.guiTop + 45, 20, 20, "1"));
			this.buttonList.add(new GuiButton(12, this.guiLeft + 23, this.guiTop + 45, 20, 20, "2"));
			this.buttonList.add(new GuiButton(13, this.guiLeft + 44, this.guiTop + 45, 20, 20, "3"));
			this.buttonList.add(new GuiButton(22, this.guiLeft + 65, this.guiTop + 45, 29, 20, "MULT"));
			this.buttonList.add(new GuiButton(10, this.guiLeft +  2, this.guiTop + 66, 20, 20, "0"));
			this.buttonList.add(new GuiButton(23, this.guiLeft + 65, this.guiTop + 66, 29, 20, "DIV"));
			this.buttonList.add(new GuiButton(24, this.guiLeft + 23, this.guiTop + 66, 41, 20, "ENTER"));
		}
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void drawScreen(int x, int y, float f)
	{
		super.drawScreen(x, y, f);

		this.xSize = 194;
		this.ySize = 108;
		this.guiLeft = (this.width / 2) - 97;
		this.guiTop = this.height - 108;
		
		if (this.calculator.hasClock)
		{
			java.util.Calendar cal = Minecraft.getMinecraft().theWorld.getCurrentDate();
			
			this.fontRendererObj.drawString(I18n.format(Reference.getDigitalTime(), new Object[0]), this.guiLeft + 116, this.guiTop + 2, 4210752);
			this.fontRendererObj.drawString(I18n.format(Integer.toString(cal.DAY_OF_MONTH) + "|" + 
			                                            Integer.toString(cal.MONTH) + "|" + 
					                                    Integer.toString(cal.YEAR), new Object[0]), this.guiLeft + 155, this.guiTop + 2, 4210752);
		}
		
		this.calculator.updateBooleans();
	}
	
	
	@Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}