package ch.tom.tcodes.warpstarvalcity.commands;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import ch.tom.tcodes.warpstarvalcity.mysql.warps.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpsCommand implements CommandExecutor {

    private WarpStarvalcity plugin;

    public WarpsCommand(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (plugin.getConfig().getBoolean("saveOption.mysql") && plugin.getConfig().getBoolean("saveOption.config")) {
                plugin.getLocationManager().listLocations(player);
            } else if (plugin.getConfig().getBoolean("saveOption.mysql")) {
                if (!plugin.getWarpsService().getList().isEmpty()) {
                    for (Warps warps : plugin.getWarpsService().getList()) {
                        player.sendMessage(plugin.getMessages().getConfigMessage("messages.allWarps"));
                        player.sendMessage(plugin.getMessages().getConfigMessageNoPrefix("messages.prefixWarps")+warps.getName());
                    }
                } else {
                    player.sendMessage(plugin.getMessages().getConfigMessage("messages.noWarps"));
                }

            } else if (plugin.getConfig().getBoolean("saveOption.config")) {
                plugin.getLocationManager().listLocations(player);
            }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.wrongUseWarps"));
        }

        return false;
    }
}
