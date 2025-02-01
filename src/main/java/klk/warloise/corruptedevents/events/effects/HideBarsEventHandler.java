package klk.warloise.corruptedevents.events.effects;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HideBarsEventHandler {
    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        // Verificar si el jugador tiene el efecto HIDE_LIFE_AND_HUNGER_GLICH
        if (minecraft.player.hasEffect(ModEffects.HIDE_LIFE_AND_HUNGER_GLICH.get())) {
            // Ocultar la barra de salud
            if (VanillaGuiOverlay.PLAYER_HEALTH.id().equals(event.getOverlay().id())) {
                event.setCanceled(true);
            }

            // Ocultar la barra de comida
            if (VanillaGuiOverlay.FOOD_LEVEL.id().equals(event.getOverlay().id())) {
                event.setCanceled(true);
            }
        }
    }
}
