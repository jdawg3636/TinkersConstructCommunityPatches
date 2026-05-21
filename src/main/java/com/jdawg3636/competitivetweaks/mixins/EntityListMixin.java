package com.jdawg3636.competitivetweaks.mixins;

import net.minecraft.entity.EntityList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(EntityList.class)
public interface EntityListMixin {

    @Accessor("stringToIDMapping")
    static Map<String, Integer> getStringToIDMapping() {
        return null;
    }

}
