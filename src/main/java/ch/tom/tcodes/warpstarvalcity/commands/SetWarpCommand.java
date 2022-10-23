package ch.tom.tcodes.warpstarvalcity.commands;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import ch.tom.tcodes.warpstarvalcity.mysql.warps.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarpCommand implements CommandExecutor {

    private WarpStarvalcity plugin;

    public SetWarpCommand(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (player.hasPermission("warps.set") || player.hasPermission("warps.*")) {
          if (args.length == 1) {
              if (plugin.getConfig().getBoolean("saveOption.mysql") && plugin.getConfig().getBoolean("saveOption.config")) {
                  if (plugin.getWarpsService().get(args[0]).getName() == null) {
                      //add Warp to Config
                      plugin.getLocationManager().addLocation(args[0], player);

                      //add Warp to DB
                      plugin.getWarpsService().create(new Warps(args[0], player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),player.getLocation().getWorld().getName(), player.getLocation().getYaw(), player.getLocation().getPitch()));

                      player.sendMessage(plugin.getMessages().getPrefix() + "§5Du hast erfolgreich einen Warp erstellt. \n §7Name§8: " + args[0]);

                      //remove warp from Arraylist for Tab Completer
                      plugin.getCompletions().add(args[0]);
                  } else if (plugin.getWarpConfig().get("warps."+args[0]) == null){
                      plugin.getLocationManager().addLocation(args[0], player);
                      plugin.getCompletions().add(args[0]);
                      player.sendMessage(plugin.getMessages().getPrefix()+"§cDie Daten wurden in der Config gespeichert.");
                  } else {
                      player.sendMessage(plugin.getMessages().getPrefix() + "§cDiesen Warp gibt es bereits");
                  }
              } else if (plugin.getConfig().getBoolean("saveOption.mysql")) {
                  if (plugin.getWarpsService().get(args[0]).getName() == null) {
                      //add Warp to DB
                      plugin.getWarpsService().create(new Warps(args[0], player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),player.getLocation().getWorld().getName(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                      player.sendMessage(plugin.getMessages().getPrefix() + "§5Du hast erfolgreich einen Warp erstellt. \n §7Name§8: " + args[0]);
                      //remove warp from Arraylist for Tab Completer
                      plugin.getCompletions().add(args[0]);
                  } else {
                      player.sendMessage(plugin.getMessages().getPrefix() + "§cDiesen Warp gibt es bereits");
                  }
              } else if (plugin.getConfig().getBoolean("saveOption.config")) {
                  if (plugin.getWarpConfig().get("warps."+args[0]) == null) {

                      //add Warp to Config
                      plugin.getLocationManager().addLocation(args[0], player);

                      player.sendMessage(plugin.getMessages().getPrefix() + "§5Du hast erfolgreich einen Warp erstellt. \n §7Name§8: " + args[0]);
                      //remove warp from Arraylist for Tab Completer
                      plugin.getCompletions().add(args[0]);
                  } else {
                      player.sendMessage(plugin.getMessages().getPrefix() + "§cDiesen Warp gibt es bereits");
                  }
              }

          } else {
              player.sendMessage(plugin.getMessages().getPrefix()+"§cBitte benutze /setwarp <name>");
          }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.noPermissionMessage"));
        }
        return false;
    }
}
