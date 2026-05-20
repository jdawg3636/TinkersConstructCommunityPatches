package com.jdawg3636.competitivetweaks.common;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class CompetitiveTweaksLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.competitivetweaks.late.json";
    }

    @Nonnull
    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        ArrayList<String> toReturn = new ArrayList<>();
        if(loadedMods.contains("GalacticraftCore")) {
            toReturn.add("GalacticraftMeteorMixin");
        }
        if(loadedMods.contains("TConstruct")) {
            toReturn.add("RapierMixin");
        }
        return toReturn;
    }

}
