package ch.tom.tcodes.warpstarvalcity.files;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class UtilFile implements InterfaceFile{

    private WarpStarvalcity plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    public UtilFile(WarpStarvalcity plugin, File file, FileConfiguration fileConfiguration) {
        this.plugin = plugin;
        this.file = file;
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public void create(String filename, boolean saveResource) {
        file = new File(plugin.getDataFolder(), filename);
        fileConfiguration = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (!saveResource) {
                try {
                    file.createNewFile();
                    fileConfiguration.createSection("warps");
                    save();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (plugin.getResource(filename) == null) {
                    plugin.saveResource(filename, true);
                } else {
                    plugin.saveResource(filename, false);
                }
            }
            load();
        }
    }

    @Override
    public void load() {
        try {
            fileConfiguration.load(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void set(String path, Object s) {
        fileConfiguration.set(path, s);
        load();
    }
}
