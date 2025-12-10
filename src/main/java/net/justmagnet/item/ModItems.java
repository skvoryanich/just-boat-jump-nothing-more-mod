package net.justmagnet.item;

import net.justmagnet.JustMagnetMod;
import net.justmagnet.component.ModComponents;
import net.justmagnet.config.ModConfigManager;
import net.justmagnet.item.custom.MagnetItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
	private ModItems() {
	}

	// Ядро магнита
	public static final RegistryKey<Item> BASE_CORE_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "base_core"));
	public static final Item BASE_CORE = registerItem(
			new Item(new Item.Settings()
					.registryKey(BASE_CORE_KEY)
					.maxCount(1)),
			BASE_CORE_KEY);

	// Продвинутое ядро магнита
	public static final RegistryKey<Item> ADVANCED_CORE_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "advanced_core"));
	public static final Item ADVANCED_CORE = registerItem(
			new Item(new Item.Settings()
					.registryKey(ADVANCED_CORE_KEY)
					.maxCount(1)),
			ADVANCED_CORE_KEY);

	// Магнит
	public static final RegistryKey<Item> BASE_MAGNET_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "base_magnet"));
	public static final Item BASE_MAGNET;
	
	// Продвинутый магнит
	public static final RegistryKey<Item> ADVANCED_MAGNET_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "advanced_magnet"));
	public static final Item ADVANCED_MAGNET;
	
	static {
		// Инициализируем магниты после загрузки конфига
		// Прочность устанавливается динамически через Mixin в getMaxDamage()
		// НЕ устанавливаем компонент MAX_DAMAGE здесь, чтобы Mixin мог переопределить значение
		BASE_MAGNET = registerItem(
				new MagnetItem(
						new Item.Settings()
								.registryKey(BASE_MAGNET_KEY)
								.maxCount(1)
								.component(ModComponents.MAGNET_IS_ACTIVE_COMPONENT, false)
				),
				BASE_MAGNET_KEY
		);
		
		ADVANCED_MAGNET = registerItem(
				new MagnetItem(
						new Item.Settings()
								.registryKey(ADVANCED_MAGNET_KEY)
								.maxCount(1)
								.component(ModComponents.MAGNET_IS_ACTIVE_COMPONENT, false)
				),
				ADVANCED_MAGNET_KEY
		);
	}

	// Сломанный магнит
	public static final RegistryKey<Item> BROKEN_BASE_MAGNET_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "broken_base_magnet"));
	public static final Item BROKEN_BASE_MAGNET = registerItem(
			new Item(new Item.Settings()
					.registryKey(BROKEN_BASE_MAGNET_KEY)
					.maxCount(1)),
			BROKEN_BASE_MAGNET_KEY);

	// Сломанный продвинутый магнит
	public static final RegistryKey<Item> BROKEN_ADVANCED_MAGNET_KEY = RegistryKey.of(
			RegistryKeys.ITEM,
			Identifier.of(JustMagnetMod.MOD_ID, "broken_advanced_magnet"));
	public static final Item BROKEN_ADVANCED_MAGNET = registerItem(
			new Item(new Item.Settings()
					.registryKey(BROKEN_ADVANCED_MAGNET_KEY)
					.maxCount(1)),
			BROKEN_ADVANCED_MAGNET_KEY);

	private static Item registerItem(Item item, RegistryKey<Item> registryKey) {
		return Registry.register(Registries.ITEM, registryKey.getValue(), item);
	}

	public static void registerModItems() {
		JustMagnetMod.LOGGER.info("Registering mod items");
	}
}

