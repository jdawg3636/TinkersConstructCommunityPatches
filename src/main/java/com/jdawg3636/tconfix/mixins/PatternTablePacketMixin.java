package com.jdawg3636.tconfix.mixins;

import mantle.blocks.abstracts.InventoryLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import tconstruct.tools.logic.StencilTableLogic;
import tconstruct.util.network.PatternTablePacket;

@Mixin(PatternTablePacket.class)
public class PatternTablePacketMixin {

    // Replace the "InventoryLogic" constant with "StencilTableLogic" in the instanceof check
    // Mitigates a nasty item spawning exploit that is exploitable via hacked clients
    @ModifyConstant(method = "handleServerSide", constant = @Constant(ordinal = 0, classValue = InventoryLogic.class), remap = false)
    private Class tconfix$DenyPatternTablePacketForOtherTileEntities(Object instance, Class originalClass) {
        return StencilTableLogic.class;
    }

}
