package ch.tom.tcodes.warpstarvalcity.utils;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;

public class Messages {

    private WarpStarvalcity plugin;

    public Messages(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    public String getConfigMessage(String path) {
        String message;
        String configMessage = getPrefix() + plugin.getConfig().getString(path);
        message = configMessage.replace("&", "§");
        return message;
    }

    public String getConfigMessageNoPrefix(String path) {
        String message;
        String configMessage = plugin.getConfig().getString(path);
        message = configMessage.replace("&", "§");
        return message;
    }

    public String getPrefix() {
        String message;
        String configMessage = plugin.getConfig().getString("messages.prefix");
        message = configMessage.replace("&", "§");
        return message;
    }


}
