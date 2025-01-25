package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HalfArmorHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.HALF_ARMOR_EFFECT.get())) {
                event.setAmount(event.getAmount()*2);
                System.out.println("EntitySoure= "+event.getEntity());
            }
        }
    }
}
