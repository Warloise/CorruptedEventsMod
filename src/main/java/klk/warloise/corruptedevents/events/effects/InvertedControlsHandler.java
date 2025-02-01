package klk.warloise.corruptedevents.events.effects;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class InvertedControlsHandler {

    private static boolean invertedControlsActive = false; // Estado del efecto

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.level().isClientSide) {
            Player player = event.player;

            // Revisa si el jugador tiene el efecto activo
            MobEffectInstance effectInstance = player.getEffect(ModEffects.INVERTED_CONTROLS.get());
            if (effectInstance != null) {
                invertedControlsActive = true;
            } else {
                invertedControlsActive = false;
            }
        }
    }

    @SubscribeEvent
    public static void onKeyInput(MovementInputUpdateEvent event) {
        if (!invertedControlsActive) return; // Si el efecto no está activo, no se hace nada

        // Invertir los controles de movimiento
        event.getInput().leftImpulse = -event.getInput().leftImpulse; // Invertir A y D
        event.getInput().forwardImpulse = -event.getInput().forwardImpulse; // Invertir W y S
    }
}
