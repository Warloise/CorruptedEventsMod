package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HurtXpHandler {

    // Registrar el evento en Forge.
    public HurtXpHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        // Asegurarnos de que el jugador es válido y está en el servidor.
        if (event.getEntity() instanceof ServerPlayer player) {
            // Verificar si el jugador tiene el efecto ReverseXPGlitchEffect activo.
            if (player.hasEffect(ModEffects.HURT_XP.get())) {
                // Calcular cuánta experiencia quitar.
                int xpToRemove = event.getOrb().getValue();
                System.out.println("Xp funcionando");
                float damage = xpToRemove *0.5f;
                player.hurt(player.damageSources().magic(), damage);
                event.getOrb().remove(Entity.RemovalReason.DISCARDED); // Eliminar el orbe de experiencia.
                event.setCanceled(true); // Cancelar el evento para evitar que el jugador gane XP.
            }
        }
    }
}
