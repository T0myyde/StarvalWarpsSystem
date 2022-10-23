package ch.tom.tcodes.warpstarvalcity;

import ch.tom.tcodes.warpstarvalcity.commands.DelWarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.SetWarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.WarpCommand;
import ch.tom.tcodes.warpstarvalcity.commands.WarpsCommand;
import ch.tom.tcodes.warpstarvalcity.files.FileAPI;
import ch.tom.tcodes.warpstarvalcity.mysql.MySQL;
import ch.tom.tcodes.warpstarvalcity.mysql.warps.WarpsService;
import ch.tom.tcodes.warpstarvalcity.tabcompleter.WarpCompleter;
import ch.tom.tcodes.warpstarvalcity.utils.LocationManager;
import ch.tom.tcodes.warpstarvalcity.utils.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class WarpStarvalcity extends JavaPlugin {

    private static WarpStarvalcity instance;
    private File warpFile;
    private FileConfiguration warpConfig;


    private List<String> completions;

    private Messages messages;

    private WarpsService warpsService;

    private LocationManager locationManager;

    private MySQL mySQL;

    @Override
    public void onEnable() {
        instance = this;
        mySQL = new MySQL();
        onInit();
    }

    private void onInit() {
        mySQL.createTable();
        messages = new Messages(this);
        locationManager = new LocationManager(this);
        warpsService = new WarpsService();
        FileAPI.select(this, warpFile, warpConfig).create("warpLocations.yml", false);

        warpFile = new File(instance.getDataFolder(), "warpLocations.yml");
        warpConfig = YamlConfiguration.loadConfiguration(warpFile);

        completions = new ArrayList<>();

        locationManager.getAllLocations();
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
        this.getCommand("delwarp").setTabCompleter(new WarpCompleter(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        this.getCommand("warp").setTabCompleter(new WarpCompleter(this));
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
        return warpFile;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public List<String> getCompletions() {
        return completions;
    }

    public WarpsService getWarpsService() {
        return warpsService;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public static WarpStarvalcity getInstance() {
        return instance;
    }
}
