package me.dacubeking.quickreport;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.lang.reflect.Array;
import java.util.*;

public class QuickReportCommandHandler extends CommandBase {



    @Override
    public String getCommandName() {
        // TODO Auto-generated method stub
        return "qr";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        // TODO Auto-generated method stub
        return "/qr <playername>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        // TODO Auto-generated method stub
        if(args.length>0){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Reporting " + args[0]));
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/wdr " + args[0] + " killaura autoclicker reach speed antikb");
            Collection<NetworkPlayerInfo> playerInfoCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
            for (NetworkPlayerInfo networkPlayerInfo : playerInfoCollection) {
                if (networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(args[0])){
                    QuickReport.reportedplayers.add(networkPlayerInfo.getGameProfile().getId());
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Added " + networkPlayerInfo.getGameProfile().getName() + " to your reported players"));
                    return;
                }
            }
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Could not find " + args[0]));

        } else{
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("/qr <playername>"));
        }


    }

    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if(args.length == 1 ){
            ArrayList<String> completions = new ArrayList<String>();

            Collection<NetworkPlayerInfo> playerInfoCollection = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
            for (NetworkPlayerInfo networkPlayerInfo : playerInfoCollection) {
                String formattedPlayerName = Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(networkPlayerInfo);

                if (formattedPlayerName != null && !formattedPlayerName.contains(EnumChatFormatting.OBFUSCATED.toString())){
                    completions.add(networkPlayerInfo.getGameProfile().getName());
                }
            }

            return getListOfStringsMatchingLastWord(args, completions);
        }
        return null;

    }
}