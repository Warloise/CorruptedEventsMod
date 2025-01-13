package klk.warloise.corruptedevents.client;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RedScreenEffectRenderer {
    private static final Minecraft MC = Minecraft.getInstance();
    private static long lastTriggerTime = 0; // Momento en que el glitch comenzó a mostrarse.
    private static final int GLITCH_INTERVAL = 15 * 1000; // Intervalo de 15 segundos en milisegundos.
    private static final int GLITCH_DURATION = 500; // Duración del glitch en milisegundos (0.5 segundos).

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (MC.player == null || MC.level == null) return;

        // Verificar si el jugador tiene el efecto activo.
        MobEffectInstance effectInstance = MC.player.getEffect(ModEffects.RED_SCREEN_EFFECT.get());
        if (effectInstance != null) {
            renderGlichOverlay(event.getGuiGraphics(), effectInstance.getAmplifier());
        }
    }

    private static void renderGlichOverlay(GuiGraphics graphics, int amplifier) {
        int width = MC.getWindow().getGuiScaledWidth();
        int height = MC.getWindow().getGuiScaledHeight();

        // Dibujar un filtro rojo translúcido delante de todo.
        int redColor = (0x05 << 24) | (0xFF << 16); // Rojo con 50% de transparencia (0x80 es opacidad al 50%).
        graphics.fill(0, 0, width, height, redColor);
    }


}
