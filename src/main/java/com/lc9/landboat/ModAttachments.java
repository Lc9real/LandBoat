package com.lc9.landboat;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, LandBoat.MODID);

    public static final Supplier<AttachmentType<Boolean>> HAS_LAND =
            ATTACHMENT_TYPES.register("has_land", () ->
                    AttachmentType.builder(() -> false)
                            .serialize(Codec.BOOL.fieldOf("has_land"))
                            .sync(ByteBufCodecs.BOOL)
                            .build()
            );

    public static void register(IEventBus eventBus)
    {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
