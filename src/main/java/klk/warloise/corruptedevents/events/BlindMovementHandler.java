package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlindMovementHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player.level().isClientSide) {
            Player player = event.player;

            // Verificar si el jugador tiene el efecto activo.
            if (player.hasEffect(ModEffects.BLIND_MOVEMENT.get())) {
                // Verificar si el jugador está quieto.
                if (player.getDeltaMovement().lengthSqr() < 0.01) {
                    // Aplicar el efecto de ceguera.
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));
                } else {
                    // Remover el efecto de ceguera si el jugador se está moviendo.
                    player.removeEffect(MobEffects.BLINDNESS);
                }
            }
        }
    }
}