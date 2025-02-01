package klk.warloise.corruptedevents.client.glitch;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HalfArmorEffectRenderer {
    private static final Minecraft MC = Minecraft.getInstance();
    private static long lastTriggerTime = 0; // Momento en que el glitch comenzó a mostrarse.
    private static final int GLITCH_INTERVAL = 15 * 1000; // 30 segundos en milisegundos.
    private static final int GLITCH_DURATION = 500; // Duración del glitch en milisegundos (1 segundo).

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (MC.player == null || MC.level == null) return;

        // Verificar si el jugador tiene el efecto activo.
        MobEffectInstance effectInstance = MC.player.getEffect(ModEffects.HALF_ARMOR_EFFECT.get());
        if (effectInstance != null) {
            long currentTime = System.currentTimeMillis(); // Tiempo actual en milisegundos.

            // Calcular si el glitch debe estar activo.
            if (currentTime - lastTriggerTime >= GLITCH_INTERVAL) {
                lastTriggerTime = currentTime; // Reiniciar el temporizador.
            }

            // Mostrar el glitch si está dentro de la duración.
            if (currentTime - lastTriggerTime < GLITCH_DURATION) {
                renderGlichOverlay(event.getGuiGraphics(), effectInstance.getAmplifier());
            }
        }
    }

    private static void renderGlichOverlay(GuiGraphics graphics, int amplifier) {
        int width = MC.getWindow().getGuiScaledWidth();
        int height = MC.getWindow().getGuiScaledHeight();

        int numberOfLines = 1 + amplifier * 3; // Número de líneas, aumenta con el amplificador del efecto.

        for (int i = 0; i < numberOfLines; i++) {
            // Calcular posición y tamaño de la línea.
            int startX = (int) (width * (Math.random() * 1));; // Todas las líneas comienzan desde la izquierda.
            int endX = (int) (width * (0.3 + Math.random() * 0.7)); // Longitud de la línea (30%-100% del ancho de la pantalla).
            int posY = (int) (Math.random() * height); // Posición vertical aleatoria.
            int thickness = 2 + (int) (Math.random() * 4); // Grosor de la línea (2-5 píxeles).

            // Dibujar la línea horizontal.
            graphics.fill(
                    startX,             // Inicio en X.
                    posY,               // Inicio en Y.
                    endX,               // Fin en X.
                    posY + thickness,   // Fin en Y (depende del grosor).
                    (0xFF << 24) | (int) (Math.random() * 0xFFFFFF) // Color aleatorio con opacidad completa.
            );
        }
    }
}
