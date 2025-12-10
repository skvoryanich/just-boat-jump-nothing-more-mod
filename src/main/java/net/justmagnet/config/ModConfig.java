package net.justmagnet.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.justmagnet.JustMagnetMod;

@Config(name = JustMagnetMod.MOD_ID)
public class ModConfig implements ConfigData {
	public double baseMagnetPickupDistance = 4.0;
	public double advancedMagnetPickupDistance = 8.0;
	public int baseMagnetDurability = 2048;
	public int advancedMagnetDurability = 4096;
}

