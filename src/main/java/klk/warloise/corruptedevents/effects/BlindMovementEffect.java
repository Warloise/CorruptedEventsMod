package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BlindMovementEffect extends MobEffect {
    public BlindMovementEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // No se necesita lógica aquí, todo se manejará en el handler.
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Aplicar el efecto en cada tick.
    }
}