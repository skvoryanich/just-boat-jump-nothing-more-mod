package net.justmagnet.item;

import net.justmagnet.JustMagnetMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
	private ModItemGroups() {
	}

	public static final ItemGroup JUST_MAGNET_GROUP = Registry.register(
			Registries.ITEM_GROUP,
			Identifier.of(JustMagnetMod.MOD_ID, "just_magnet"),
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemgroup.justmagnet"))
					.icon(() -> new ItemStack(ModItems.BASE_MAGNET))
					.entries((displayContext, entries) -> {
						entries.add(ModItems.BASE_CORE);
						entries.add(ModItems.ADVANCED_CORE);
						entries.add(ModItems.BASE_MAGNET);
						entries.add(ModItems.ADVANCED_MAGNET);
						entries.add(ModItems.BROKEN_BASE_MAGNET);
						entries.add(ModItems.BROKEN_ADVANCED_MAGNET);
					})
					.build()
	);

	public static void registerItemGroups() {
		JustMagnetMod.LOGGER.info("Registering item groups");
	}
}

