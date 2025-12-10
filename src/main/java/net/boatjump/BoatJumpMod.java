package net.boatjump;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatJumpMod implements ModInitializer {
	public static final String MOD_ID = "boatjump";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Just boat jump, nothing more!");
	}
}
