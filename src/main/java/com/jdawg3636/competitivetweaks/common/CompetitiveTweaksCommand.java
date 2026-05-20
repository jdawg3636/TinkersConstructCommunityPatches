package com.jdawg3636.competitivetweaks.common;

import com.jdawg3636.competitivetweaks.Tags;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class CompetitiveTweaksCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "competitivetweaks";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/competitivetweaks <reload>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                CompetitiveTweaksConfig.load();
                MinecraftForge.EVENT_BUS.post(
                    new ConfigChangedEvent.OnConfigChangedEvent(Tags.MODID, Tags.MODID, true, false)
                );
                sender.addChatMessage(new ChatComponentText("Config reloaded."));
                break;

            default:
                sender.addChatMessage(new ChatComponentText("Unknown subcommand. Usage: " + getCommandUsage(sender)));
                break;
        }
    }

    // Tab-complete the subcommands
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "reload");
        }
        return null;
    }

}
