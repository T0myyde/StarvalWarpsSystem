package ch.tom.tcodes.commands;

import ch.tom.tcodes.WarpSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DelWarpCommand implements CommandExecutor {


    private WarpSystem plugin;

    public DelWarpCommand(WarpSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;


        if (player.hasPermission("warps.del") || player.hasPermission("warps.*")) {
            if (args.length == 1) {
                if (plugin.getConfig().getBoolean("saveOption.mysql") && plugin.getConfig().getBoolean("saveOption.config")) {
                    if (plugin.getWarpConfig().get("warps."+args[0]) != null) {
                        try {
                            plugin.getWarpConfig().set("warps." + args[0], null);
                            plugin.getWarpConfig().save(plugin.getFile());
                            plugin.getWarpsService().delete(plugin.getWarpsService().get(args[0]));
                            plugin.getCompletions().remove(args[0]);
                            player.sendMessage(plugin.getMessages().getPrefix() + "§aYou have successfully completed the warp §e" + args[0] + " §adeleted");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (plugin.getWarpsService().get(args[0]).getName() != null) {
                        if (plugin.getWarpsService().get(args[0]).getName() != null) {
                            plugin.getWarpsService().delete(plugin.getWarpsService().get(args[0]));
                            player.sendMessage(plugin.getMessages().getPrefix() + "§aYou have successfully completed the warp §e" + args[0] + " §adeleted");
                            plugin.getCompletions().remove(args[0]);
                        } else {
                            player.sendMessage(plugin.getMessages().getPrefix() + "§cThe warp with the name §e" + args[0] + " §cdoes not exist!");
                        }
                    } else {
                        player.sendMessage(plugin.getMessages().getPrefix() + "§cThe warp with the name §e" + args[0] + " §cdoes not exist!");
                    }
                } else if (plugin.getConfig().getBoolean("saveOption.mysql")) {
                    if (plugin.getWarpsService().get(args[0]).getName() != null) {
                        plugin.getWarpsService().delete(plugin.getWarpsService().get(args[0]));
                        player.sendMessage(plugin.getMessages().getPrefix() + "§aYou have successfully completed the warp §e" + args[0] + " §adeleted");
                        plugin.getCompletions().remove(args[0]);
                    } else {
                        player.sendMessage(plugin.getMessages().getPrefix() + "§cThe warp with the name §e" + args[0] + " §cdoes not exist!");
                    }
                } else if (plugin.getConfig().getBoolean("saveOption.config")) {
                    if (plugin.getWarpConfig().get("warps."+args[0]) != null) {
                        try {
                            plugin.getWarpConfig().set("warps." + args[0], null);
                            plugin.getWarpConfig().save(plugin.getFile());
                            plugin.getCompletions().remove(args[0]);
                            player.sendMessage(plugin.getMessages().getPrefix() + "§aYou have successfully completed the warp §e" + args[0] + " §adeleted");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        player.sendMessage(plugin.getMessages().getPrefix() + "§cThe warp with the name §e" + args[0] + " §cdoes not exist!");
                    }
                }
            } else {
                player.sendMessage(plugin.getMessages().getPrefix() + "§cPlease use /delwarp <warpname>");
            }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.noPermissionMessage"));
        }


        return false;
    }
}
