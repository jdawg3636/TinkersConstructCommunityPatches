package com.jdawg3636.competitivetweaks.mixins;

import com.jdawg3636.competitivetweaks.common.CompetitiveTweaksConfig;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = GCPlayerHandler.class, remap = false)
public class GalacticraftMeteorMixin {

    @Inject(method = "throwMeteors", at = @At("HEAD"), cancellable = true, remap = false)
    private void mixin$competitiveTweaks$LimitMeteorsToRadiusAroundOriginOfLevel(EntityPlayerMP player, CallbackInfo callback) {
        if(CompetitiveTweaksConfig.limitMeteorSpawnRadius) {
            final int meteorSpawnRadius = CompetitiveTweaksConfig.meteorSpawnRadius;
            if(Math.abs(player.posX) > meteorSpawnRadius || Math.abs(player.posZ) > meteorSpawnRadius) {
                // System.out.println("[JD3636 MIXIN DEBUG] Blocking Meteor Spawns for Player (outside radius)");
                callback.cancel();
            }
        }
    }

}
