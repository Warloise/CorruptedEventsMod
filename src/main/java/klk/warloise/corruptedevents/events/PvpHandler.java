package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class PvpHandler {

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        // Verificar que el atacante y la víctima sean jugadores
        if (!(event.getSource().getEntity() instanceof Player attacker) || !(event.getEntity() instanceof Player victim)) {
            return;
        }

        // Verificar si ambos jugadores están en condiciones de participar en PvP
        boolean attackerHasPvp = hasPvpEffect(attacker);
        boolean victimHasPvp = hasPvpEffect(victim);

        // Cancelar el evento si ambos jugadores no tienen el efecto PVP_ACTIVATE
        if (!attackerHasPvp && !victimHasPvp) {
            event.setCanceled(true);
        }
    }

    private static boolean hasPvpEffect(Player player) {
        return player.hasEffect(ModEffects.PVP_ACTIVATE.get());
    }
}
