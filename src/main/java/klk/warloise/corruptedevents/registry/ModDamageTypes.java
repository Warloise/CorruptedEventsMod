package klk.warloise.corruptedevents.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.resources.ResourceLocation;

public class ModDamageTypes {
    public static DamageType GLITCH;

    public static void registerDamageTypes(RegistryAccess registryAccess) {
        Registry<DamageType> registry = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        GLITCH = registry.get(new ResourceLocation("corruptedevents", "glitch"));
    }
}
