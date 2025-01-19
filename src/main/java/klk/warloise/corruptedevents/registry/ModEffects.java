package klk.warloise.corruptedevents.registry;

import klk.warloise.corruptedevents.Corruptedevents;
import klk.warloise.corruptedevents.effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Corruptedevents.MODID);

    public static final RegistryObject<MobEffect> GLICHEFFECT = MOB_EFFECTS.register("glich",
            () -> new GlichEffect(MobEffectCategory.BENEFICIAL, 0x98D982)); // Color del efecto.
    public static final RegistryObject<MobEffect> REVERSE_XP_GLICH = MOB_EFFECTS.register("reverse_xp_glitch",
            () -> new ReverseXPGlichEffect(MobEffectCategory.HARMFUL, 0xFF4500)); // Color naranja.
    public static final RegistryObject<MobEffect> INVERTED_CONTROLS = MOB_EFFECTS.register("inverted_controls",
            () -> new ReverseXPGlichEffect(MobEffectCategory.HARMFUL, 0xFF3450));
    public static final RegistryObject<MobEffect> HURT_XP = MOB_EFFECTS.register("hurt_xp",
            () -> new HurtXpEffect(MobEffectCategory.HARMFUL, 0xFF4500));
    public static final RegistryObject<MobEffect> BLIND_MOVEMENT = MOB_EFFECTS.register("blind_movement",
            () -> new BlindMovementEffect(MobEffectCategory.HARMFUL, 0x000000)); // Color negro.
    public static final RegistryObject<MobEffect> DAMAGE_LOWER = MOB_EFFECTS.register("damage_lower",
            () -> new DamageLowerEffect(MobEffectCategory.HARMFUL, 0xFF0000)); // Color rojo.
}
