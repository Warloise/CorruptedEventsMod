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
            () -> new GlichEffect(MobEffectCategory.BENEFICIAL, 0x98D982)); 
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
    public static final RegistryObject<MobEffect> HALF_ARMOR_EFFECT = MOB_EFFECTS.register("half_armor",
            () -> new HalfArmorEffect(MobEffectCategory.HARMFUL, 0xFF0000)); // Color base del efecto rojo.
    public static final RegistryObject<MobEffect> INVERTED_CONTROLS = MOB_EFFECTS.register("inverted_controls",
            () -> new InvertedControlsEffect(MobEffectCategory.HARMFUL, 0xFF3450));
    public static final RegistryObject<MobEffect> HURT_XP = MOB_EFFECTS.register("hurt_xp",
            () -> new HurtXpEffect(MobEffectCategory.HARMFUL, 0xFF4500));
    public static final RegistryObject<MobEffect> BLIND_MOVEMENT = MOB_EFFECTS.register("blind_movement",
            () -> new BlindMovementEffect(MobEffectCategory.HARMFUL, 0x000000)); 
    public static final RegistryObject<MobEffect> DAMAGE_LOWER = MOB_EFFECTS.register("damage_lower",
            () -> new DamageLowerEffect(MobEffectCategory.HARMFUL, 0xFF0000));
    public static final RegistryObject<MobEffect> BLACK_AND_WHITE_MODE = MOB_EFFECTS.register("black_and_white",
            () -> new BlackAndWhiteModeEffect(MobEffectCategory.HARMFUL, 0x808080));
    public static final RegistryObject<MobEffect> PIXELATE = MOB_EFFECTS.register("pixelate",
            () -> new PixelateEffect(MobEffectCategory.HARMFUL, 0x808080)); 
    public static final RegistryObject<MobEffect> CRT = MOB_EFFECTS.register("crt",
            () -> new CRTEffect(MobEffectCategory.HARMFUL, 0x808080)); 
    public static final RegistryObject<MobEffect> PVP_ACTIVATE = MOB_EFFECTS.register("pvp_activate",
            () -> new PvpActivateEffect(MobEffectCategory.BENEFICIAL, 0xFF0000)); 
    public static final RegistryObject<MobEffect> MINI = MOB_EFFECTS.register("mini",
            () -> new MiniEffect(MobEffectCategory.HARMFUL, 0x00FF00)); // Color verde
    public static final RegistryObject<MobEffect> HALF_MINING_EFFECT = MOB_EFFECTS.register("halfmining",
            () -> new HalfMiningEffect(MobEffectCategory.HARMFUL, 0x00FF00)); // Color verde
    public static final RegistryObject<MobEffect> REACH_BLOCK_REDUCE = MOB_EFFECTS.register("reachblochreduce",
            () -> new ReachBlockReduceEffect(MobEffectCategory.HARMFUL, 0x00FF00)); // Color verde

}
