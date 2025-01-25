package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import klk.warloise.corruptedevents.client.GlichEffectRenderer;

@Mod.EventBusSubscriber(modid = "corruptedevents", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MilkBucketEventHandler {

    @SubscribeEvent
    public static void onMilkDrink(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack consumedItem = event.getItemStack();

        if (consumedItem.getItem() == Items.MILK_BUCKET) {
            boolean hasCorruptedEffect = player.getActiveEffects().stream()
                    .anyMatch(effect -> effect.getEffect() == ModEffects.GLICHEFFECT.get() ||
                                        effect.getEffect() == ModEffects.REVERSE_XP_GLICH.get() ||
                                        effect.getEffect() == ModEffects.INVERTED_CONTROLS.get() ||
                                        effect.getEffect() == ModEffects.HURT_XP.get());

            if (hasCorruptedEffect) {
                event.setCanceled(true); 

                // Resta la mitad de la vida al jugador
                float damage = player.getHealth() / 2;
                player.hurt(player.damageSources().genericKill(), damage);

                player.sendSystemMessage(Component.literal("Glitch: Querías evadirme, ¿verdad? ¡Nunca desaparezco!"));
                //GlichEffectRenderer.renderGlichOverlay(Minecraft.getInstance().getGuiGraphics, 1);
                player.getCooldowns().addCooldown(consumedItem.getItem(), 1200);
            }
        }
    }
}

