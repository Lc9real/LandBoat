
package com.lc9.landboat.loot;

import com.lc9.landboat.LandBoat;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;

public class ModLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>>
            GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    LandBoat.MODID
            );

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(
                    "add_item",
                    () -> AddItemModifier.CODEC
            );

    public static void register(net.neoforged.bus.api.IEventBus modEventBus) {
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
    }
}
