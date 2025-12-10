package net.justboatjump.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class BoatEntityMixin {

    @Unique
    private Entity instance = (Entity) (Object) this;

    @Inject(
        method = "tick",
        at = @At("HEAD")
    )
    private void onTick(CallbackInfo ci) {
        // Check if this is a boat
        if (!(instance instanceof BoatEntity boat)) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        // Check if player is controlling this boat
        if (!boat.hasPassenger(client.player)) {
            return;
        }

        // Get jump key (spacebar)
        KeyBinding jumpKey = client.options.jumpKey;

        // If spacebar is pressed and boat is on ground or water
        if (jumpKey.isPressed() && (boat.isOnGround() || boat.isTouchingWater())) {
            // Get current velocity
            Vec3d velocity = boat.getVelocity();
            
            // Determine jump height: lower in water
            double jumpHeight = boat.isTouchingWater() ? 0.2 : 0.35;
            
            // Check if boat is on ice (ICE, PACKED_ICE, or BLUE_ICE)
            BlockPos blockPos = boat.getBlockPos().down();
            boolean isOnIce = boat.getEntityWorld().getBlockState(blockPos).isOf(Blocks.ICE) ||
                             boat.getEntityWorld().getBlockState(blockPos).isOf(Blocks.PACKED_ICE) ||
                             boat.getEntityWorld().getBlockState(blockPos).isOf(Blocks.BLUE_ICE);
            
            // Preserve and boost horizontal velocity
            // +100% acceleration on ice, 0% on other blocks
            double speedMultiplier = isOnIce ? 2.0 : 1.0;
            double horizontalSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
            double boostedSpeed = horizontalSpeed * speedMultiplier;
            
            // If boat is moving, preserve direction and boost speed
            // Otherwise, use minimum speed in forward direction
            double newX, newZ;
            if (horizontalSpeed > 0.01) {
                // Preserve current direction, boost speed
                double directionX = velocity.x / horizontalSpeed;
                double directionZ = velocity.z / horizontalSpeed;
                newX = directionX * boostedSpeed;
                newZ = directionZ * boostedSpeed;
            } else {
                // Boat is not moving, use forward direction with minimum speed
                float yaw = boat.getYaw();
                double yawRad = Math.toRadians(yaw);
                newX = Math.sin(yawRad) * 0.1;
                newZ = -Math.cos(yawRad) * 0.1;
            }
            
            // Set velocity: preserve/boost horizontal, add vertical
            boat.setVelocity(newX, velocity.y + jumpHeight, newZ);
        }
    }
}
