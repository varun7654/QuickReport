package me.dacubeking.quickreport;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod(modid = QuickReport.MODID, version = QuickReport.VERSION)
public class QuickReport
{


    public static final String MODID = "quickreport";
    public static final String VERSION = "1.1";
    public static final Set<UUID> reportedplayers = new HashSet<UUID>();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        QuickReportEventHandler eventHandler = new QuickReportEventHandler();
        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);
        ClientCommandHandler.instance.registerCommand(new QuickReportCommandHandler());
        ClientCommandHandler.instance.registerCommand(new WDRInterceptor());
        ClientCommandHandler.instance.registerCommand(new WatchDogReportInterceptor());
        ClientCommandHandler.instance.registerCommand(new ReportInterceptor());
    }

}
