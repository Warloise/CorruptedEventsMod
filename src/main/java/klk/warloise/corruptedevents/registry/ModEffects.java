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
    public static final RegistryObject<MobEffect> RED_SCREEN_EFFECT = MOB_EFFECTS.register("red_screen",
            () -> new RedScreenEffect(MobEffectCategory.NEUTRAL, 0xFF0000)); // Color base del efecto rojo.
    public static final RegistryObject<MobEffect> HALF_LIFE_GLICH = MOB_EFFECTS.register("half_life",
            () -> new HalfLifeEffect(MobEffectCategory.HARMFUL, 0xFF0000)); // Color base del efecto rojo.
    public static final RegistryObject<MobEffect> HALF_HUNGER_GLICH = MOB_EFFECTS.register("half_hunger",
            () -> new HalfHungerEffect(MobEffectCategory.HARMFUL, 0xFF0000)); // Color base del efecto rojo.
    public static final RegistryObject<MobEffect> HIDE_LIFE_AND_HUNGER_GLICH = MOB_EFFECTS.register("hide_life_and_hunger",
            () -> new HideLifeAndHungerEffect(MobEffectCategory.NEUTRAL, 0xFF0000)); // Color base del efecto rojo.
    public static final RegistryObject<MobEffect> DURABILITY_DRAIN_EFFECT_GLICH = MOB_EFFECTS.register("durability_drain",
            () -> new DurabilityDrainEffect(MobEffectCategory.NEUTRAL, 0xFF0000)); // Color base del efecto rojo.


}
