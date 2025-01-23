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
public class DamageLowerEffectRenderer {
    private static final Minecraft MC = Minecraft.getInstance();
    private static long lastTriggerTime = 0;
    private static final int GLITCH_INTERVAL = 15 * 1000;
    private static final int GLITCH_DURATION = 500;

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (MC.player == null || MC.level == null) return;


        MobEffectInstance effectInstance = MC.player.getEffect(ModEffects.DAMAGE_LOWER.get());
        if (effectInstance != null) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastTriggerTime >= GLITCH_INTERVAL) {
                lastTriggerTime = currentTime;
            }

            if (currentTime - lastTriggerTime < GLITCH_DURATION) {
                renderGlichOverlay(event.getGuiGraphics(), effectInstance.getAmplifier());
            }
        }
    }

    private static void renderGlichOverlay(GuiGraphics graphics, int amplifier) {
        int width = MC.getWindow().getGuiScaledWidth();
        int height = MC.getWindow().getGuiScaledHeight();

        int numberOfLines = 1 + amplifier * 3;

        for (int i = 0; i < numberOfLines; i++) {
            // Calcular posición y tamaño de la línea.
            int startX = (int) (width * (Math.random() * 1));;
            int endX = (int) (width * (0.3 + Math.random() * 0.7));
            int posY = (int) (Math.random() * height);
            int thickness = 2 + (int) (Math.random() * 4);

            // Dibujar la línea horizontal.
            graphics.fill(
                    startX,
                    posY,
                    endX,
                    posY + thickness,
                    (0xFF << 24) | (int) (Math.random() * 0xFFFFFF)
            );
        }
    }
}
