package com.jdawg3636.tconfix.common;

import com.jdawg3636.tconfix.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class TConFix {

    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOG.info("Initializing " + Tags.MODNAME + "!");
    }

}
