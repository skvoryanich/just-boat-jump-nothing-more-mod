package net.justmagnet.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {
	private InventoryUtil() {
	}

	public static List<Integer> getInventoryIndices(PlayerEntity player, Item item) {
		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < player.getInventory().size(); i++) {
			ItemStack stack = player.getInventory().getStack(i);
			if (stack.getItem() == item) {
				indices.add(i);
			}
		}
		return indices;
	}
}

