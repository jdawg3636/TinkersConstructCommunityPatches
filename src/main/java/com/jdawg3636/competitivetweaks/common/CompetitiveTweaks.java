package com.jdawg3636.competitivetweaks.common;

import com.jdawg3636.competitivetweaks.Tags;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]", acceptableRemoteVersions = "*", dependencies = "required-after:unimixins@[0.1.5];after:TConstruct;after:GalacticraftCore")
public class CompetitiveTweaks {

    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @NetworkCheckHandler
    public static boolean shouldAllowConnection(Map<String,String> modidsToVersions, Side remoteSide) {
        if(remoteSide.isServer()) {
            return true;
        }
        if(!CompetitiveTweaksConfig.requireModToBeInstalledOnClient) {
            return true;
        }
        return modidsToVersions.containsKey(Tags.MODID);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG.info("Pre-Initializing " + Tags.MODNAME + "!");
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
