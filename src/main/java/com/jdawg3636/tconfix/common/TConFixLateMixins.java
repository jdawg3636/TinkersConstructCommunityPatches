package com.jdawg3636.tconfix.common;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class TConFixLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.tconfix.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> set) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("RapierMixin");
        return toReturn;
    }

}
