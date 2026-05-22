package com.jdawg3636.competitivetweaks.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(EntityList.class)
public interface EntityListMixin {

    @Accessor("classToIDMapping")
    static Map<Class<Entity>, Integer> getClassToIDMapping() {
        return null;
    }

    @Accessor("stringToIDMapping")
    static Map<String, Integer> getStringToIDMapping() {
        return null;
    }

}
