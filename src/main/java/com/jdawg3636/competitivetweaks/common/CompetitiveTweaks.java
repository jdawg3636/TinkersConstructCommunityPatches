package com.jdawg3636.competitivetweaks.common;

import com.jdawg3636.competitivetweaks.Tags;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]", dependencies = "required-after:unimixins@[0.1.5];after:TConstruct;after:GalacticraftCore")
public class CompetitiveTweaks {

    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOG.info("Initializing " + Tags.MODNAME + "!");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CompetitiveTweaksConfig.init(event.getSuggestedConfigurationFile());
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Tags.MODID)) {
            CompetitiveTweaksConfig.load();
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CompetitiveTweaksCommand());
    }

}
