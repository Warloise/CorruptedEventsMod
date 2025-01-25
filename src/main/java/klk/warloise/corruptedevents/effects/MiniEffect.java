package klk.warloise.corruptedevents.effects;

import io.netty.util.AttributeMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleOperations;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.api.ScaleTypes;

// FIXME No hace pequeño

public class MiniEffect extends MobEffect {
    public MiniEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entity).getTargetScale(), 0.5));
        }
    }

    public void removeAttributeModifiers (LivingEntity entity, AttributeMap am, int amplifier) {
        super.removeAttributeModifiers(entity, (net.minecraft.world.entity.ai.attributes.AttributeMap) am, amplifier);
        if (!entity.level().isClientSide) {
            ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entity).getTargetScale(), 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}