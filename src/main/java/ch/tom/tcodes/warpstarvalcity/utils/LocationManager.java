package ch.tom.tcodes.warpstarvalcity.utils;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LocationManager {

    private WarpStarvalcity plugin;

    public LocationManager() {
    }

    public LocationManager(WarpStarvalcity plugin) {
        this.plugin = plugin;
    }

    public void addLocation(String name, Player player) {
        FileConfiguration cfg = plugin.getWarpConfig();

        Location location = player.getLocation();
        cfg.set("warps."+name + ".world", location.getWorld().getName());
        cfg.set("warps."+name + ".x", location.getX());
        cfg.set("warps."+name + ".y", location.getY());
        cfg.set("warps."+name + ".z", location.getZ());
        cfg.set("warps."+name + ".yaw", location.getYaw());
        cfg.set("warps."+name + ".pitch", location.getPitch());

        try {
            cfg.save(plugin.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getLocation(String name) {
        FileConfiguration cfg = plugin.getWarpConfig();
        World world = Bukkit.getWorld(cfg.getString("warps."+name + ".world"));
        double x = cfg.getDouble("warps."+name + ".x");
        double y = cfg.getDouble("warps."+name + ".y");
        double z = cfg.getDouble("warps."+name + ".z");
        Location location = new Location(world, x, y, z);
        location.setYaw(cfg.getInt("warps."+name + ".yaw"));
        location.setPitch(cfg.getInt("warps."+name + ".pitch"));

        return location;
    }

    public void listLocations(Player player) {
        File file = new File(plugin.getDataFolder(), "warpLocations.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (!cfg.getConfigurationSection("warps").getKeys(false).isEmpty()) {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.allWarps"));
            for (String locations : cfg.getConfigurationSection("warps").getKeys(false)) {
                player.sendMessage(plugin.getMessages().getConfigMessageNoPrefix("messages.prefixWarps")+locations);
            }
        } else {
            player.sendMessage(plugin.getMessages().getConfigMessage("messages.noWarps"));
        }



    }

    public void getAllLocations() {
        File file = new File(plugin.getDataFolder(), "warpLocations.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        for (String locations : cfg.getConfigurationSection("warps").getKeys(false)) {
            plugin.getCompletions().add(locations);
        }

    }

    private String removeYml(String string) {
        return string.replaceAll(".yml", "");
    }

}