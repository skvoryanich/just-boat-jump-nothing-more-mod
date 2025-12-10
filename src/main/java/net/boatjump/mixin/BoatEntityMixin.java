package net.boatjump.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        // Проверяем, что это клиентская сторона
        if (!this.getWorld().isClient()) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        // Проверяем, что игрок управляет этой лодкой
        if (!this.hasPassenger(client.player)) {
            return;
        }

        // Получаем клавишу прыжка (пробел)
        KeyBinding jumpKey = client.options.jumpKey;

        // Если нажат пробел и лодка на земле или воде
        if (jumpKey.isPressed() && (this.isOnGround() || this.isTouchingWater())) {
            // Добавляем вертикальную скорость (прыжок)
            this.setVelocity(this.getVelocity().add(0.0, 0.4, 0.0));
        }
    }
}
