
package com.lc9.landboat.loot;

import com.lc9.landboat.LandBoat;
import com.lc9.landboat.enchantment.ModEnchantments;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class AddItemModifier extends LootModifier {

    public static final MapCodec<AddItemModifier> CODEC =
            RecordCodecBuilder.mapCodec(inst ->
                    LootModifier.codecStart(inst).apply(inst, AddItemModifier::new)
            );

    public AddItemModifier(
            Optional<Holder<LootItemCondition>> condition,
            int priority
    ) {
        super(condition, priority);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        Holder<net.minecraft.world.item.enchantment.Enchantment> enchantment =
                context.getLevel().registryAccess()
                        .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.LAND);

        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

        ItemEnchantments.Mutable enchantments =
                new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

        enchantments.set(enchantment, 1);

        book.set(DataComponents.STORED_ENCHANTMENTS, enchantments.toImmutable());

        generatedLoot.add(book);

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return ModLootModifiers.ADD_ITEM.get();
    }
}
