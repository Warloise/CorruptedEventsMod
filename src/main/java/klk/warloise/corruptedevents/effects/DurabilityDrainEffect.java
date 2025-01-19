package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DurabilityDrainEffect extends MobEffect {
    public DurabilityDrainEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Reducir la durabilidad de los ítems en el inventario
            player.getInventory().items.forEach(itemStack -> reduceDurability(itemStack, player));

            // Reducir la durabilidad de los ítems en los slots de armadura
            player.getInventory().armor.forEach(itemStack -> reduceDurability(itemStack, player));

            // Reducir la durabilidad de los ítems en la mano secundaria
            player.getInventory().offhand.forEach(itemStack -> reduceDurability(itemStack, player));
        }
        super.applyEffectTick(entity, amplifier);
    }


    /**
     * Reduce la durabilidad de un ItemStack en 1, si es posible.
     *
     * @param itemStack El ItemStack a modificar.
     */
    private void reduceDurability(ItemStack itemStack, Player player) {
        if (!itemStack.isEmpty() && itemStack.isDamageableItem()) {
            itemStack.hurtAndBreak(1, player, (entity) -> {
                entity.broadcastBreakEvent(player.getUsedItemHand());
            });
        }
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0; // Se ejecuta una vez por segundo (20 ticks).
    }
}
