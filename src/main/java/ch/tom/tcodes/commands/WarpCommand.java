package ch.tom.tcodes.commands;

import ch.tom.tcodes.WarpSystem;
import ch.tom.tcodes.mysql.warps.Warps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private WarpSystem plugin;

    public WarpCommand(WarpSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            if (plugin.getConfig().getBoolean("saveOption.mysql") && plugin.getConfig().getBoolean("saveOption.config")) {
                if (plugin.getWarpsService().get(args[0]).getName() != null) {
                    Warps warps = plugin.getWarpsService().get(args[0]);
                    Location location = new Location(Bukkit.getWorld(warps.getWorld()), warps.getX(), warps.getY(), warps.getZ(), (float) warps.getYaw(), (float) warps.getPitch());
                    player.teleport(location);
                    player.sendMessage(plugin.getMessages().getConfigMessage("messages.playerTeleport").replace("<warpName>", args[0]));
                } else if (plugin.getWarpConfig().get("warps." + args[0]) != null) {
                    player.teleport(plugin.getLocationManager().getLocation(args[0]));
                    player.sendMessage(plugin.getMessages().getConfigMessage("messages.playerTeleport").replace("<warpName>", args[0]));
                } else {
                    player.sendMessage(plugin.getMessages().getConfigMessage("messages.warpDoesNotExists"));
                }

        } else if (plugin.getConfig().getBoolean("saveOption.mysql")) {
            if (plugin.getWarpsService().get(args[0]).getName() != null) {
                Warps warps = plugin.getWarpsService().get(args[0]);
                Location location = new Location(Bukkit.getWorld(warps.getWorld()), warps.getX(), warps.getY(), warps.getZ(), (float) warps.getYaw(), (float) warps.getPitch());
                player.teleport(location);
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.playerTeleport").replace("<warpName>", args[0]));
            } else {
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.warpDoesNotExists"));
            }
        } else if (plugin.getConfig().getBoolean("saveOption.config")) {
            if (plugin.getWarpConfig().get("warps." + args[0]) != null) {
                player.teleport(plugin.getLocationManager().getLocation(args[0]));
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.playerTeleport").replace("<warpName>", args[0]));
            } else {
                player.sendMessage(plugin.getMessages().getConfigMessage("messages.warpDoesNotExists"));
            }
        }

    } else

    {
        player.sendMessage(plugin.getMessages().getConfigMessage("messages.wrongUseWarp"));
    }

        return false;
}

}
