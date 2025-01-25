package klk.warloise.corruptedevents.events;

import klk.warloise.corruptedevents.registry.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.registries.Registries;
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
                float damage = xpToRemove * 0.5f;

                // Verificar que estamos en un nivel de servidor
                if (player.level() instanceof ServerLevel serverLevel) { // Coger de aqui para copiar y repetir ok
                    // Obtener el DamageType personalizado
                    Holder<DamageType> glitchDamageType = serverLevel.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("corruptedevents", "glitch")));

                    // Crear un DamageSource con el DamageType personalizado
                    DamageSource glitchDamageSource = new DamageSource(glitchDamageType, player);

                    // Aplicar el daño al jugador
                    player.hurt(glitchDamageSource, damage);

                    // Eliminar el orbe de experiencia
                    event.getOrb().remove(Entity.RemovalReason.DISCARDED);
                    // Cancelar el evento para evitar que el jugador gane XP
                    event.setCanceled(true);
                }
            }
        }
    }
}
