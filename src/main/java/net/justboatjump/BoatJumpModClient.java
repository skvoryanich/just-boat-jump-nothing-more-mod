package net.justboatjump;

import net.fabricmc.api.ClientModInitializer;

public class BoatJumpModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BoatJumpMod.LOGGER.info("Boat Jump Client initialized!");
	}
}
