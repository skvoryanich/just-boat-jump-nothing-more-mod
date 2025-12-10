package net.justmagnet;

import net.justmagnet.config.ModConfigManager;
import net.justmagnet.component.ModComponents;
import net.justmagnet.event.ModEvents;
import net.justmagnet.item.ModItemGroups;
import net.justmagnet.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JustMagnetMod implements ModInitializer {
	public static final String MOD_ID = "justmagnet";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Just Magnet mod");

		// Инициализация конфига ПЕРВОЙ (до всего остального)
		ModConfigManager.init();

		// Регистрация компонентов
		ModComponents.registerComponents();

		// Регистрация предметов (магниты инициализируются в static блоке после загрузки конфига)
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		// Регистрация событий
		ModEvents.registerModEvents();

		LOGGER.info("Just Magnet mod initialized successfully");
	}
}

