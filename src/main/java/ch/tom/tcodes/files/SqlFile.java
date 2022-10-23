package ch.tom.tcodes.files;

import ch.tom.tcodes.WarpSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SqlFile {

    private WarpSystem plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    public SqlFile(WarpSystem plugin, String name) {
        file = new File(plugin.getDataFolder(), name);
        fileConfiguration = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                    fileConfiguration.createSection("mysql");
                    fileConfiguration.set("mysql.user", "root");
                    fileConfiguration.set("mysql.password", "password");
                    fileConfiguration.set("mysql.host", "localhost");
                    fileConfiguration.set("mysql.port", 3306);
                    fileConfiguration.set("mysql.database", "mc");
                    save();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            load();
        }
    }

    public void load() {
        try {
            fileConfiguration.load(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
