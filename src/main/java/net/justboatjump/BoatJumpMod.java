package net.justboatjump;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatJumpMod implements ModInitializer {
	public static final String MOD_ID = "just-boat-jump";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Just boat jump, nothing more!");
	}
}
