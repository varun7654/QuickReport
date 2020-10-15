package me.dacubeking.quickreport;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WDRInterceptor extends CommandBase {
    /**
     * Gets the name of the command
     */
    @Override
    public String getCommandName() {
        return "wdr";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/wdr players";
    }

    /**
     * Callback when the command is invoked
     *
     * @param sender The command sender that executed the command
     * @param args   The arguments that were passed
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        String argument = "";
        for (String arg : args) {
            argument = argument.concat(arg + " ");
        }
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/wdr " + argument);

        if(args.length>0) {
            Collection<NetworkPlayerInfo> playerInfoCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
            for (NetworkPlayerInfo networkPlayerInfo : playerInfoCollection) {
                if (networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(args[0])){
                    QuickReport.reportedplayers.add(networkPlayerInfo.getGameProfile().getId());
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Added " + networkPlayerInfo.getGameProfile().getName() + " to your reported players"));
                    return;
                }
            }
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Could not find " + args[0]));

        }
    }

    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }
}
