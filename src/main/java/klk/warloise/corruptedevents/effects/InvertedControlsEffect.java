package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class InvertedControlsEffect extends MobEffect {
    public InvertedControlsEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Por ahora, no necesitamos lógica aquí. Todo se manejará en el cliente.
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Activar cada 20 ticks (ajustable si se necesita).
        return duration % 20 == 0;
    }
}
