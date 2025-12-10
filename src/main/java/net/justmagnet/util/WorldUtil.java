package net.justmagnet.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class WorldUtil {
	private WorldUtil() {
	}

	public static int getClosestEntity(List<? extends Entity> entities, Entity target) {
		if (entities.isEmpty()) {
			return -1;
		}

		double targetX = target.getX();
		double targetY = target.getY();
		double targetZ = target.getZ();
		double minDistance = Double.MAX_VALUE;
		int closestIndex = -1;

		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			double dx = entity.getX() - targetX;
			double dy = entity.getY() - targetY;
			double dz = entity.getZ() - targetZ;
			double distance = dx * dx + dy * dy + dz * dz;
			if (distance < minDistance) {
				minDistance = distance;
				closestIndex = i;
			}
		}

		return closestIndex;
	}
}

