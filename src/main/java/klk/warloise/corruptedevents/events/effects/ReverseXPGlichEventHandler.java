package klk.warloise.corruptedevents.events.effects;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ReverseXPGlichEventHandler {
    @SubscribeEvent
    public static void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.hasEffect(ModEffects.REVERSE_XP_GLICH.get())) {
                int xpToRemove = event.getOrb().getValue();
                int amplifier = player.getEffect(ModEffects.REVERSE_XP_GLICH.get()).getAmplifier();
                xpToRemove *= (1 + amplifier); 

                // Cancelo evento, quito experiencia y elimino el orbe
                player.giveExperiencePoints(-xpToRemove);
                event.getOrb().remove(Entity.RemovalReason.DISCARDED); 
                event.setCanceled(true); 
            }
        }
    }
}
