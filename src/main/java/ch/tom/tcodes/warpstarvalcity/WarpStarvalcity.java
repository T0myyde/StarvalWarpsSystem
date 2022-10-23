package ch.tom.tcodes.warpstarvalcity;

import ch.tom.tcodes.warpstarvalcity.commands.DelWarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.SetWarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.WarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.WarpsCommand;
import ch.tom.tcodes.warpstarvalcity.files.FileAPI;
import ch.tom.tcodes.warpstarvalcity.utils.LocationManager;
import ch.tom.tcodes.warpstarvalcity.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.tools.JavaFileManager;
import java.io.File;

public final class WarpStarvalcity extends JavaPlugin {

    private WarpStarvalcity instance;
    private File file;
    private FileConfiguration warpConfig;

    private FileAPI fileAPI;

    private Messages messages;

    private LocationManager locationManager;
    @Override
    public void onEnable() {
      instance = this;
      onInit(Bukkit.getPluginManager());
    }

    private void onInit(PluginManager pluginManager) {
        messages = new Messages(this);
        locationManager = new LocationManager(this);
        FileAPI.select(this,file,warpConfig).create("warpLocations.yml", false);

        file = new File(instance.getDataFolder(), "warpLocations.yml");
        warpConfig = YamlConfiguration.loadConfiguration(file);

        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("warps").setExecutor(new WarpsCommand(this));
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public FileConfiguration getWarpConfig() {
        return warpConfig;
    }

    @Override
    public void onDisable() {

    }

    public Messages getMessages() {
        return messages;
    }

    @NotNull
    @Override
    public File getFile() {
        return file;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public FileAPI getFileAPI() {
        return fileAPI;
    }
}
