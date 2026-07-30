package net.justboatjump;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

/**
 * Client-side gate: boat jump is off when connected to a blocked multiplayer address.
 * Singleplayer and missing server entries leave jumping enabled.
 */
public final class BoatJumpGate {
	private BoatJumpGate() {
	}

	public static boolean isEnabled() {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client == null) {
			return true;
		}

		ServerInfo entry = client.getCurrentServerEntry();
		if (entry == null) {
			return true;
		}

		return !ServerBlocklist.isBlocked(entry.address);
	}
}
