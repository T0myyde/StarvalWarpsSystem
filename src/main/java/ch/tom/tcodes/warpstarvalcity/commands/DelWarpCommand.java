package ch.tom.tcodes.warpstarvalcity.commands;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DelWarpCommand implements CommandExecutor {


    private WarpStarvalcity plugin;

    public DelWarpCommand(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;


        if (player.hasPermission("warps.del") || player.hasPermission("warps.*")) {
            if (args.length == 1) {
                if (plugin.getWarpConfig().get("warps."+args[0]) != null) {
                    try {
                        plugin.getWarpConfig().set("warps."+args[0], null);
                        plugin.getWarpConfig().save(plugin.getFile());
                        player.sendMessage(plugin.getMessages().getPrefix() + "§aDu hast erfolgreich den Warp §e" + args[0] + " gelöscht");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    player.sendMessage(plugin.getMessages().getPrefix() + "§cDer Warp mit dem Namen §e" + args[0] + " §cgibt es nicht!");
                }
            } else {
                player.sendMessage(plugin.getMessages().getPrefix() + "§cBitte benutze /delwarp <warpname>");
            }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.noPermissionMessage"));
        }



        return false;
    }
}
