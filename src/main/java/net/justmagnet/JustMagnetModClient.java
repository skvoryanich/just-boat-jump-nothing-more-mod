package net.justmagnet;

import net.fabricmc.api.ClientModInitializer;

public class JustMagnetModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		JustMagnetMod.LOGGER.info("Initializing Just Magnet mod client");
	}
}

