package net.justmagnet.event;

import net.justmagnet.JustMagnetMod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ModEvents {
	private ModEvents() {
	}

	public static void registerModEvents() {
		JustMagnetMod.LOGGER.info("Registering mod events");
		// События регистрируются через Mixin
	}
}

