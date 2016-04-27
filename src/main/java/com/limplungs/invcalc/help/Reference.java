package com.limplungs.invcalc.help;

import net.minecraft.client.Minecraft;

public class Reference
{
	public static final String MODID = "invcalc";
	public static final String VERSION = "1.0.0dev7";
	
	
	public static String getDigitalTime()
	{
		double time = Minecraft.getMinecraft().theWorld.getWorldTime();
		String digital = "";
		String meridiem = "";
		int minutes = 0;
		int hours = 0;
		
		if (time < 6000 || time >= 18000)
			meridiem = new String("am");
		else
			meridiem = new String("pm");

		while (time >= 24000)
		{
			time -= 24000;
		}
		
		if (time < 1000)
			hours = 6;
		else if (time < 2000)
			hours = 7;
		else if (time < 3000)
			hours = 8;
		else if (time < 4000)
			hours = 9;
		else if (time < 5000)
			hours = 10;
		else if (time < 6000)
			hours = 11;
		else if (time < 7000)
			hours = 12;
		else if (time < 8000)
			hours = 1;
		else if (time < 9000)
			hours = 2;
		else if (time < 10000)
			hours = 3;
		else if (time < 11000)
			hours = 4;
		else if (time < 12000)
			hours = 5;
		else if (time < 13000)
			hours = 6;
		else if (time < 14000)
			hours = 7;
		else if (time < 15000)
			hours = 8;
		else if (time < 16000)
			hours = 9;
		else if (time < 17000)
			hours = 10;
		else if (time < 18000)
			hours = 11;
		else if (time < 19000)
			hours = 12;
		else if (time < 20000)
			hours = 1;
		else if (time < 21000)
			hours = 2;
		else if (time < 22000)
			hours = 3;
		else if (time < 23000)
			hours = 4;
		else if (time < 24000)
			hours = 5;
			
		minutes = (int) ((time % 1000) / 16.666666667);
		
		if (minutes < 10)
			digital = Integer.toString(hours) + ":0" + Integer.toString(minutes) + meridiem;
		else
			digital = Integer.toString(hours) + ":" + Integer.toString(minutes) + meridiem;
		
		return digital;
	}
}
