package klk.warloise.corruptedevents.events.effects;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class PvpHandler {

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player attacker) || !(event.getEntity() instanceof Player victim)) {
            return;
        }

        boolean attackerHasPvp = hasPvpEffect(attacker);
        boolean victimHasPvp = hasPvpEffect(victim);
        if (!attackerHasPvp && !victimHasPvp) {
            event.setCanceled(true);
        }
    }

    private static boolean hasPvpEffect(Player player) {
        return player.hasEffect(ModEffects.PVP_ACTIVATE.get());
    }
}
