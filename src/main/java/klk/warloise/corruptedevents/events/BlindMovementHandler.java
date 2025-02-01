package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class BlindMovementHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player.level().isClientSide) {
            Player player = event.player;

            if (player.hasEffect(ModEffects.BLIND_MOVEMENT.get())) {
                if (player.getDeltaMovement().lengthSqr() < 0.01) {
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));
                } else {
                    player.removeEffect(MobEffects.BLINDNESS);
                }
            }
        }
    }
}