package net.justmagnet.item.custom;

import net.justmagnet.component.ModComponents;
import net.justmagnet.util.MagnetHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class MagnetItem extends Item {
	public MagnetItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		ConsumableComponent consumableComponent = itemStack.get(DataComponentTypes.CONSUMABLE);

		if (!world.isClient()) {
			MagnetHelper.toggleIsActive(itemStack);
		}

		if (consumableComponent != null) {
			return consumableComponent.consume(player, itemStack, hand);
		} else {
			EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
			return equippableComponent != null && equippableComponent.swappable() 
				? equippableComponent.equip(itemStack, player) 
				: ActionResult.PASS;
		}
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return MagnetHelper.getIsActive(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		boolean isActive = MagnetHelper.getIsActive(stack);

		if (isActive) {
			textConsumer.accept(Text.translatable("item.justmagnet.magnet.active").formatted(Formatting.GOLD));
		} else {
			textConsumer.accept(Text.translatable("item.justmagnet.magnet.not_active"));
		}
	}
}

