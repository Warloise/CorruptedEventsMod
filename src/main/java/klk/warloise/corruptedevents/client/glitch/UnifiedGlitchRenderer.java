package klk.warloise.corruptedevents.client.glitch;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnifiedGlitchRenderer {
    private static final Minecraft MC = Minecraft.getInstance();
    private static final Random RANDOM = new Random();
    private static long lastTriggerTime = 0;
    private static final int GLITCH_INTERVAL = 15 * 1000; // 15 segundos
    private static final int GLITCH_DURATION = 500; // 0.5 segundos

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (MC.player == null || MC.level == null) return;

        long currentTime = System.currentTimeMillis();
        boolean shouldTriggerNewGlitch = currentTime - lastTriggerTime >= GLITCH_INTERVAL;
        boolean isInGlitchDuration = currentTime - lastTriggerTime < GLITCH_DURATION;

        // Verificación individual de cada efecto
        checkAndRenderEffect(event, ModEffects.GLICHEFFECT.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.REVERSE_XP_GLICH.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.HURT_XP.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.INVERTED_CONTROLS.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.BLIND_MOVEMENT.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.DAMAGE_LOWER.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.HALF_ARMOR_EFFECT.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.HALF_LIFE_GLICH.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.HALF_HUNGER_GLICH.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.HIDE_LIFE_AND_HUNGER_GLICH.get(), shouldTriggerNewGlitch, isInGlitchDuration);
        checkAndRenderEffect(event, ModEffects.DURABILITY_DRAIN_EFFECT_GLICH.get(), shouldTriggerNewGlitch, isInGlitchDuration);
    }

    private static void checkAndRenderEffect(RenderGuiOverlayEvent.Post event, MobEffect effect, boolean shouldTrigger, boolean isInDuration) {
        MobEffectInstance effectInstance = MC.player.getEffect(effect);
        if (effectInstance != null) {
            if (shouldTrigger) {
                lastTriggerTime = System.currentTimeMillis();
            }

            if (isInDuration) {
                renderGlitchEffect(event.getGuiGraphics(), effectInstance.getAmplifier(), effect);
            }
        }
    }

    private static void renderGlitchEffect(GuiGraphics graphics, int amplifier, MobEffect effect) {
        int width = MC.getWindow().getGuiScaledWidth();
        int height = MC.getWindow().getGuiScaledHeight();
        int numberOfLines = 1 + amplifier * 3;

        for (int i = 0; i < numberOfLines; i++) {
            renderGlitchLine(graphics, width, height, effect);
        }
    }

    private static void renderGlitchLine(GuiGraphics graphics, int width, int height, MobEffect effect) {
        int startX = (int) (width * RANDOM.nextDouble());
        int endX = (int) (width * (0.3 + RANDOM.nextDouble() * 0.7));
        int posY = (int) (height * RANDOM.nextDouble());
        int thickness = 2 + RANDOM.nextInt(4);

        int color = getEffectColor(effect);
        
        graphics.fill(
            startX,
            posY,
            endX,
            posY + thickness,
            color
        );
    }

    private static int getEffectColor(MobEffect effect) {
        int baseColor = effect.getColor();
        return 0xFF000000 | baseColor;
    }
}