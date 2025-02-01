package klk.warloise.corruptedevents.effects.armorandtools;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;


//FIXME No quita armadura
public class HalfArmorEffect extends MobEffect {
    public HalfArmorEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Obtener el atributo de armadura base
            AttributeInstance armorAttribute = player.getAttribute(Attributes.ARMOR);
            if (armorAttribute != null) {
                // Obtener la armadura de los slots de armadura
                double armorFromItems = 0.0;
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        ItemStack itemStack = player.getItemBySlot(slot);
                        if (itemStack.getItem() instanceof ArmorItem) {
                            armorFromItems += ((ArmorItem) itemStack.getItem()).getDefense(); // Obtener la armadura del item en ese slot
                        }
                    }
                }

                // Obtener la armadura base (es decir, la armadura en el atributo) y la armadura total
                double currentArmorValue = armorAttribute.getBaseValue();
                double totalArmor = currentArmorValue + armorFromItems;

                // Reducir la armadura total a la mitad (o según el amplificador)
                double reducedArmorValue = totalArmor;

                // Aplicar el valor reducido de armadura (la reducción es sobre la armadura total)
                armorAttribute.setBaseValue(reducedArmorValue - armorFromItems);  // Reducimos solo la armadura base, sin tocar la armadura del equipo.
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity,
                                         AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (entity instanceof Player player) {
            // Restaurar la armadura a su valor original
            AttributeInstance armorAttribute = player.getAttribute(Attributes.ARMOR);
            if (armorAttribute != null) {
                // Obtener la armadura de los slots de armadura
                double armorFromItems = 0.0;
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        ItemStack itemStack = player.getItemBySlot(slot);
                        if (itemStack.getItem() instanceof ArmorItem) {
                            armorFromItems += ((ArmorItem) itemStack.getItem()).getDefense(); // Obtener la armadura del item en ese slot
                        }
                    }
                }
                // Restaurar el valor original de la armadura (sin la reducción)
                armorAttribute.setBaseValue(armorAttribute.getBaseValue() + armorFromItems);  // Restauramos la armadura base.
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Este efecto se aplica en cada tick mientras está activo.
    }


}
