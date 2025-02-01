package klk.warloise.corruptedevents.effects.lifeandhunger;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class HalfLifeEffect extends MobEffect {
    public HalfLifeEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Vida máxima original del jugador
            double originalMaxHealth = 20.0; // Vida base estándar del jugador en Minecraft.
            double reducedMaxHealth = originalMaxHealth / 2.0;

            // Reducir la vida máxima si es mayor que el límite reducido
            if (player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() > reducedMaxHealth) {
                player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(reducedMaxHealth);

                // Asegurar que la vida actual no sea mayor que la vida máxima
                if (player.getHealth() > reducedMaxHealth) {
                    player.setHealth((float) reducedMaxHealth);
                }
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity,
                                         AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (entity instanceof Player player) {
            // Restaurar la vida máxima del jugador a la original
            double defaultMaxHealth = 20.0;
            player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(defaultMaxHealth);

            // Ajustar la vida actual si excede la vida máxima
            if (player.getHealth() > defaultMaxHealth) {
                player.setHealth((float) defaultMaxHealth);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Este efecto se aplica en cada tick mientras está activo.
    }
}
