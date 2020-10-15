package me.dacubeking.quickreport;

public class ReportInterceptor extends WDRInterceptor{
    /**
     * Gets the name of the command
     */
    @Override
    public String getCommandName() {
        return "report";
    }
}
