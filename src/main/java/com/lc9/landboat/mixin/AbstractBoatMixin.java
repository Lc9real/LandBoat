package com.lc9.landboat.mixin;

import com.lc9.landboat.ModAttachments;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin
{

    @Shadow
    private float landFriction;

    @Redirect(
            method = "floatBoat",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;landFriction:F",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private float landboat$modifyLandFriction(AbstractBoat boat)
    {
        if (boat.getData(ModAttachments.HAS_LAND.get()) && this.landFriction < 0.9f)
        {
            return 0.9F;
        }

        return this.landFriction;
    }

}