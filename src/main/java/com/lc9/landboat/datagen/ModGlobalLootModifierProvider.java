package com.lc9.landboat.datagen;

import com.lc9.landboat.LandBoat;
import com.lc9.landboat.loot.AddItemModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider
{
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, LandBoat.MODID);
    }

    @Override
    protected void start()
    {

        this.add("shipwreck_treasure_book",
                new AddItemModifier(
                        Optional.of(Holder.direct(
                                LootTableIdCondition.builder(
                                        Identifier.withDefaultNamespace("chests/shipwreck_treasure")
                                ).and(
                                        LootItemRandomChanceCondition.randomChance(0.1f)
                                ).build()
                        )),
                        0
                )
        );

        this.add("buried_treasure_book",
                new AddItemModifier(
                        Optional.of(Holder.direct(
                                LootTableIdCondition.builder(
                                        Identifier.withDefaultNamespace("chests/buried_treasure")
                                ).and(
                                        LootItemRandomChanceCondition.randomChance(0.1f)
                                ).build()
                        )),
                        0
                )
        );

        this.add("big_ruins_book",
                new AddItemModifier(
                        Optional.of(Holder.direct(
                                LootTableIdCondition.builder(
                                        Identifier.withDefaultNamespace("chests/underwater_ruin_big")
                                ).and(
                                        LootItemRandomChanceCondition.randomChance(0.1f)
                                ).build()
                        )),
                        0
                )
        );

        this.add("small_ruins_book",
                new AddItemModifier(
                        Optional.of(Holder.direct(
                                LootTableIdCondition.builder(
                                        Identifier.withDefaultNamespace("chests/underwater_ruin_small")
                                ).and(
                                        LootItemRandomChanceCondition.randomChance(0.1f)
                                ).build()
                        )),
                        0
                )
        );
    }
}