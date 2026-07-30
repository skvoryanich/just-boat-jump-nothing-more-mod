package net.justboatjump;

import java.util.List;
import java.util.Locale;

/**
 * Hardcoded host substrings for boat-racing networks where jumping must stay off.
 */
public final class ServerBlocklist {
	private static final List<String> BLOCKED_HOST_SUBSTRINGS = List.of(
		"boatlabs.net",
		"frosthex.com",
		"wolfnetwork.com"
	);

	private ServerBlocklist() {
	}

	/**
	 * @param address multiplayer address as stored by the client (may include port)
	 * @return true if the address contains a blocked host substring (case-insensitive)
	 */
	public static boolean isBlocked(String address) {
		if (address == null || address.isBlank()) {
			return false;
		}

		String normalized = address.toLowerCase(Locale.ROOT);
		for (String blocked : BLOCKED_HOST_SUBSTRINGS) {
			if (normalized.contains(blocked)) {
				return true;
			}
		}
		return false;
	}
}
