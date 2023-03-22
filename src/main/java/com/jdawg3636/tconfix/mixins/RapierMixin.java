package com.jdawg3636.tconfix.mixins;

import com.jdawg3636.tconfix.common.TConFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tconstruct.items.tools.Rapier;

@Mixin(Rapier.class)
public class RapierMixin {

    @Inject(method = "pierceArmor", at = @At("HEAD"), cancellable = true, remap = false)
    private void tconfix$DisableRapierPiercing(CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(false);
    }

}
