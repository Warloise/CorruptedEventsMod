package klk.warloise.corruptedevents.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GlichEffect extends MobEffect {
    public GlichEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Aquí defines lo que hace el efecto cada "tick".
        //Quiero que me haga la pantalla el efecto de glich
        
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Define cuándo se activa el efecto. Este ejemplo activa el efecto cada 20 ticks.
        return duration % 20 == 0;
    }
}