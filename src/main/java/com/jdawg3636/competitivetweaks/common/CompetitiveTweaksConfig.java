package com.jdawg3636.competitivetweaks.common;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class CompetitiveTweaksConfig {

    public static Configuration config;

    // _general
    public static boolean requireModToBeInstalledOnClient = false;
    // tinkers_construct
    public static boolean disableRapierPiercing = true;
    public static boolean limitMeteorSpawnRadius = true;
    // galacticraft
    public static int meteorSpawnRadius = 1000;
    // vanilla
    public static boolean silkTouchSpawners = true;
    public static boolean setSpawnerEntityUsingSpawnEgg = true;
    public static boolean setSpawnerEntityUsingSpawnEggInSurvival = false;
    public static boolean setSpawnerEntityUsingSpawnEggInSurvivalConsumesEgg = true;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        load();
    }

    public static void load() {
        config.load();

        requireModToBeInstalledOnClient = config.getBoolean(
            "requireModToBeInstalledOnClient",
            "_general",
            false,
            "If true, the server will refuse to allow clients to join if they don't also have this mod installed."
        );

        disableRapierPiercing = config.getBoolean(
            "disableRapierPiercing",
            "tinkers_construct",
            true,
            "If true, the Rapier from Tinkers' Construct will be patched to not bypass armor."
        );

        limitMeteorSpawnRadius = config.getBoolean(
            "limitMeteorSpawnRadius",
            "galacticraft",
            true,
            "If true, falling meteors in Galacticraft dimensions will be patched to only spawn within a " +
                "configurable radius around (0,0). This is useful preventing meteors from accumulating on the surface " +
                "near a hidden underground base."
        );

        meteorSpawnRadius = config.getInt(
            "meteorSpawnRadius",
            "galacticraft",
            1000,
            0,
            Integer.MAX_VALUE,
            "Falling Meteors spawn near players in Galacticraft dimensions. If limitMeteorSpawnRadius is enabled, "+
                "this value defines a radius, in blocks, beyond which a player will no longer trigger meteor spawns. Note that " +
                "meteors spawn high in the sky in a 10 block radius around the player and may have a high horizontal velocity, " +
                "meaning that they can still land outside of this radius if the player is standing within the radius close to " +
                "the border. If you wish to completely disable meteor spawns, the best solution is to set \"Meteor Spawn Modifier\" " +
                "to 0.0 in the normal Galacticraft config."
        );

        silkTouchSpawners = config.getBoolean(
            "silkTouchSpawners",
            "vanilla",
            true,
            "If true, mob spawners can be collected using Silk Touch. This is compatible with modded tools, such " +
                "as Tinkers' Construct pickaxes with the 'Silky' modifier (Silk Jewel) applied."
        );

        setSpawnerEntityUsingSpawnEgg = config.getBoolean(
            "setSpawnerEntityUsingSpawnEgg",
            "vanilla",
            true,
            "If true, right-clicking on a mob spawner while holding a spawn egg will change the entityId of the spawner " +
                "to that of the spawn egg. By default, this is only enabled in Creative Mode."
        );

        setSpawnerEntityUsingSpawnEggInSurvival = config.getBoolean(
            "setSpawnerEntityUsingSpawnEggInSurvival",
            "vanilla",
            false,
            "If true, the functionality enabled by setSpawnerEntityUsingSpawnEgg will apply outside of Creative Mode."
        );

        setSpawnerEntityUsingSpawnEggInSurvivalConsumesEgg = config.getBoolean(
            "setSpawnerEntityUsingSpawnEggInSurvivalConsumesEgg",
            "vanilla",
            true,
            "If true, the functionality enabled by setSpawnerEntityUsingSpawnEggInSurvival will consume the spawn egg when not in Creative Mode."
        );

        if (config.hasChanged()) {
            config.save();
        }
    }

}
