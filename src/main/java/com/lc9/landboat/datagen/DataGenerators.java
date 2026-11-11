package com.lc9.landboat.datagen;

import com.lc9.landboat.LandBoat;
import com.lc9.landboat.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

@EventBusSubscriber(modid = LandBoat.MODID)
public class DataGenerators
{

    @SubscribeEvent
    public static void gatherRegistries(GatherDataRegistryEntriesEvent event)
    {
        event.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
    }


    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event)
    {
        event.createProvider(ModGlobalLootModifierProvider::new);
    }
}