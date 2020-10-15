package me.dacubeking.quickreport;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class QuickReportEventHandler {
    public static HashSet<UUID> checkedPlayer = new HashSet<UUID>();

    @SubscribeEvent
    public void joinworld(EntityJoinWorldEvent event){
        if(Minecraft.getMinecraft().thePlayer != null &&event.entity instanceof EntityPlayer )
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            if(!(checkedPlayer.contains(player.getGameProfile().getId()))){
                checkedPlayer.add(player.getGameProfile().getId());
                System.out.println(QuickReport.reportedplayers + " " + player.getGameProfile().getName() + " " + player.getGameProfile().getId());
                if(QuickReport.reportedplayers.contains(player.getGameProfile().getId())){
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You reported " + player.getGameProfile().getName() + " for cheating"));
                }
            }


        }
    }

    @SubscribeEvent
    public void worldChangeEvent(WorldEvent.Unload event){
        checkedPlayer.clear();
        System.out.println("reset checked players");
    }
}
