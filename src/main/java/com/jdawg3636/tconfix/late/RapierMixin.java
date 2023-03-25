package com.jdawg3636.tconfix.late;

import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tconstruct.items.tools.Rapier;
import tconstruct.library.tools.Weapon;

import java.util.List;

@Mixin(value = Rapier.class, remap = false)
public class RapierMixin extends Weapon {

    @Inject(method = "pierceArmor", at = @At("HEAD"), cancellable = true, remap = false)
    private void tconfix$DisableRapierPiercing(CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(false);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean gameSettingsAdvancedItemTooltips) {
        list.add(EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + I18n.format("tconfix.rapier_nerf_warning"));
        super.addInformation(stack, player, list, gameSettingsAdvancedItemTooltips);
    }

    //
    // Mixin boilerplate below this line - plz ignore <3
    //

    public RapierMixin(int baseDamage) {
        super(baseDamage);
    }

    @Shadow
    public String getIconSuffix(int i) { return null; }

    @Shadow
    public String getEffectSuffix() {
        return null;
    }

    @Shadow
    public String getDefaultFolder() {
        return null;
    }

    @Shadow
    public Item getHeadItem() {
        return null;
    }

    @Shadow
    public Item getAccessoryItem() {
        return null;
    }

}
