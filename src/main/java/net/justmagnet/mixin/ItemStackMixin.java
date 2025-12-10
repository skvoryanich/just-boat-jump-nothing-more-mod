package net.justmagnet.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.justmagnet.JustMagnetMod;
import net.justmagnet.config.ModConfigManager;
import net.justmagnet.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	private void getMaxDamage(CallbackInfoReturnable<Integer> cir) {
		ItemStack self = (ItemStack) (Object) this;
		
		// На сервере: устанавливаем компонент MAX_DAMAGE из конфига
		if (FabricLoader.getInstance().getEnvironmentType() == net.fabricmc.api.EnvType.SERVER) {
			if (self.getItem() == ModItems.BASE_MAGNET) {
				int durability = ModConfigManager.getMagnetDurability();
				JustMagnetMod.LOGGER.debug("ItemStackMixin: BASE_MAGNET getMaxDamage called on SERVER, setting component to {}", durability);
				self.set(DataComponentTypes.MAX_DAMAGE, durability);
				cir.setReturnValue(durability);
				return;
			}
			if (self.getItem() == ModItems.ADVANCED_MAGNET) {
				int durability = ModConfigManager.getAdvancedMagnetDurability();
				JustMagnetMod.LOGGER.debug("ItemStackMixin: ADVANCED_MAGNET getMaxDamage called on SERVER, setting component to {}", durability);
				self.set(DataComponentTypes.MAX_DAMAGE, durability);
				cir.setReturnValue(durability);
				return;
			}
		} else {
			// На клиенте: читаем компонент MAX_DAMAGE из ItemStack (синхронизируется с сервера)
			// Если компонента нет, не переопределяем - пусть используется дефолтное поведение
			if (self.getItem() == ModItems.BASE_MAGNET || self.getItem() == ModItems.ADVANCED_MAGNET) {
				Integer maxDamage = self.get(DataComponentTypes.MAX_DAMAGE);
				if (maxDamage != null && maxDamage > 0) {
					// Компонент уже установлен сервером, используем его
					JustMagnetMod.LOGGER.debug("ItemStackMixin: {} getMaxDamage called on CLIENT, using component value {}", 
						self.getItem() == ModItems.BASE_MAGNET ? "BASE_MAGNET" : "ADVANCED_MAGNET", maxDamage);
					cir.setReturnValue(maxDamage);
					return;
				}
				// Компонента нет - не переопределяем, пусть используется дефолтное поведение
			}
		}
	}
}

