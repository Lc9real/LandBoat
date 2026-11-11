package com.lc9.landboat.enchantment;

import com.lc9.landboat.LandBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

public class ModEnchantments
{
    public static final ResourceKey<Enchantment> LAND = ResourceKey.create(Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(LandBoat.MODID, "land"));

    public static void bootstrap(BootstrapContext<Enchantment> context)
    {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, LAND, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.BOATS),
                items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                30,
                1,
                Enchantment.dynamicCost(10, 5),
                Enchantment.dynamicCost(15, 5),
                5,
                EquipmentSlotGroup.ANY))
        );
    }

    public static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                Enchantment.Builder builder)
    {
        registry.register(key, builder.build(key.identifier()));
    }
}
