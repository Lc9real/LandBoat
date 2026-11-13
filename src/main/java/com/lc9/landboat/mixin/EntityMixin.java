package com.lc9.landboat.mixin;

import com.lc9.landboat.ModAttachments;
import com.lc9.landboat.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @ModifyVariable(
            method = "spawnAtLocation(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/entity/item/ItemEntity;",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private ItemStack landboat$preserveEnchantment(ItemStack stack)
    {
        Entity entity = (Entity) (Object) this;

        if (!(entity instanceof AbstractBoat boat))
        {
            return stack;
        }

        if (!boat.getData(ModAttachments.HAS_LAND.get()))
        {
            return stack;
        }

        Holder<Enchantment> enchantment = boat.level()
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(ModEnchantments.LAND);

        stack.enchant(enchantment, 1);

        return stack;
    }

    @Inject(method="maxUpStep", at = @At("HEAD"), cancellable = true)
    public void landboat$changeMaxUpStep(CallbackInfoReturnable<Float> cir)
    {
        Entity entity = (Entity) (Object) this;

        if (entity instanceof AbstractBoat boat && boat.getData(ModAttachments.HAS_LAND.get()))
        {
            cir.setReturnValue(1.0f);
        }
    }
}