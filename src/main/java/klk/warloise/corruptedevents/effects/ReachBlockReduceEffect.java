package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import virtuoel.pehkui.api.ScaleOperations;
import virtuoel.pehkui.api.ScaleTypes;

public class ReachBlockReduceEffect extends MobEffect {
    public ReachBlockReduceEffect(MobEffectCategory category, int color) {
        super(category, color);

    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Por ahora, no necesitamos lógica aquí. Todo se manejará en el cliente.
        if (!entity.level().isClientSide) {
            ScaleTypes.BLOCK_REACH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.BLOCK_REACH.getScaleData(entity).getTargetScale(), 0.5));
        }
    }
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        if (!entity.level().isClientSide) {
            ScaleTypes.BLOCK_REACH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.BLOCK_REACH.getScaleData(entity).getTargetScale(), 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Activar cada 20 ticks (ajustable si se necesita).
        return duration % 20 == 0;
    }
}
