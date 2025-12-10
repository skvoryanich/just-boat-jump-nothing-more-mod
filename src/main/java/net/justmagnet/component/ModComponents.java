package net.justmagnet.component;

import com.mojang.serialization.Codec;
import net.justmagnet.JustMagnetMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.justmagnet.component.ModComponentKeys.MAGNET_IS_ACTIVE;

public class ModComponents {
	private ModComponents() {
	}

	public static final ComponentType<Boolean> MAGNET_IS_ACTIVE_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(JustMagnetMod.MOD_ID, MAGNET_IS_ACTIVE),
			ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
	);

	public static void registerComponents() {
		JustMagnetMod.LOGGER.info("Registering mod components");
	}
}

