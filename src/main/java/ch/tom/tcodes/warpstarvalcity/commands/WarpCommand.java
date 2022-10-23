package ch.tom.tcodes.warpstarvalcity.commands;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private WarpStarvalcity plugin;

    public WarpCommand(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            if (plugin.getWarpConfig().get("warps."+args[0]) != null) {
                player.teleport(plugin.getLocationManager().getLocation(args[0]));
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.playerTeleport").replace("<warpName>", args[0]));
            } else {
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.warpDoesNotExists"));
            }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.wrongUseWarp"));
        }

        return false;
    }

}
