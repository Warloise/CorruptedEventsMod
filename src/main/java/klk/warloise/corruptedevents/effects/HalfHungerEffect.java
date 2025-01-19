package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HalfHungerEffect extends MobEffect {
    public HalfHungerEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Nivel de comida original del jugador
            int originalHungerLevel = 20; // Nivel máximo estándar de comida del jugador.
            int reducedHungerLevel = originalHungerLevel / 2;

            // Reducir el nivel de comida si es mayor que el límite reducido
            if (player.getFoodData().getFoodLevel() > reducedHungerLevel) {
                player.getFoodData().setFoodLevel(reducedHungerLevel);
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity,
                                         net.minecraft.world.entity.ai.attributes.AttributeMap attributeMap,
                                         int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (entity instanceof Player player) {
            // Restaurar el nivel de comida del jugador a la original
            int defaultHungerLevel = 20; // Nivel máximo estándar de comida.
            if (player.getFoodData().getFoodLevel() < defaultHungerLevel) {
                player.getFoodData().setFoodLevel(defaultHungerLevel);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Este efecto se aplica en cada tick mientras está activo.
    }
}
