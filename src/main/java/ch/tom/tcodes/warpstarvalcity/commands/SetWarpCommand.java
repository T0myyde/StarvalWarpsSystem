package ch.tom.tcodes.warpstarvalcity.commands;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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
              if (plugin.getWarpConfig().get("warps."+args[0]) == null) {
                  plugin.getLocationManager().addLocation(args[0], player);
                  player.sendMessage(plugin.getMessages().getPrefix() + "§5Du hast erfolgreich einen Warp erstellt. \n §7Name§8: " + args[0]);
                  plugin.getCompletions().add(args[0]);
              } else {
                  player.sendMessage(plugin.getMessages().getPrefix() + "§cDiesen Warp gibt es bereits");
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
