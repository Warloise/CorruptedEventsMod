package klk.warloise.corruptedevents.client.shaders;

import com.google.gson.JsonSyntaxException;
import klk.warloise.corruptedevents.Corruptedevents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import klk.warloise.corruptedevents.registry.ModEffects;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlackAndWhiteModeRenderer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceLocation ACHROMATOPSIA_SHADER = new ResourceLocation(Corruptedevents.MODID, "shaders/post/acromatico.json");

    private static PostChain achromatopsiaShader;

    private static int lastWidth = 0;
    private static int lastHeight = 0;
    private static PostChain lastShader = null;

    private static PostChain createShaderGroup(ResourceLocation location) {
        try {
            Minecraft mc = Minecraft.getInstance();
            return new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), location);
        } catch (IOException ioexception) {
            LOGGER.warn("Failed to load shader: {}", location, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
            LOGGER.warn("Failed to parse shader: {}", location, jsonsyntaxexception);
        }
        return null;
    }

    private static void makeColorShaders() {
        if (achromatopsiaShader == null) {
            achromatopsiaShader = createShaderGroup(ACHROMATOPSIA_SHADER);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent event) {
        if (event == null || event.phase != TickEvent.Phase.END) {
            return;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            makeColorShaders();

            PostChain activeShader = null;
            if (player.hasEffect(ModEffects.BLACK_AND_WHITE_MODE.get())) {
                activeShader = achromatopsiaShader;
            }

            if (activeShader != null) {
                if (lastShader != activeShader) {
                    lastShader = activeShader;
                    lastWidth = 0;
                    lastHeight = 0;
                }
                updateShaderGroupSize(activeShader);
                activeShader.process(event.renderTickTime);
                Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
            }
        }
    }

    public static void updateShaderGroupSize(PostChain shaderGroup) {
        if (shaderGroup != null) {
            Minecraft mc = Minecraft.getInstance();
            int width = mc.getWindow().getWidth();
            int height = mc.getWindow().getHeight();
            if (width != lastWidth || height != lastHeight) {
                lastWidth = width;
                lastHeight = height;
                shaderGroup.resize(width, height);
            }
        }
    }
}
