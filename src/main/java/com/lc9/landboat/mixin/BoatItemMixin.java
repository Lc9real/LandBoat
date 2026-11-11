package com.lc9.landboat.mixin;

import com.lc9.landboat.ModAttachments;
import com.lc9.landboat.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatItem.class)
public class BoatItemMixin
{
    @Inject(method = "getBoat", at = @At("RETURN"))
    private void landboat$transferEnchantment(
            Level level,
            HitResult hitResult,
            ItemStack itemStack,
            Player player,
            CallbackInfoReturnable<AbstractBoat> cir
    )
    {
        AbstractBoat boat = cir.getReturnValue();
        if (boat == null)
        {
            return;
        }

        if (level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .get(ModEnchantments.LAND)
                .map(holder -> itemStack.getEnchantments().getLevel(holder))
                .orElse(0) > 0)
        {
            boat.setData(ModAttachments.HAS_LAND.get(), true);
        }
    }
}
