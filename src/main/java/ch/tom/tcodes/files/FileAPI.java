package ch.tom.tcodes.files;

import ch.tom.tcodes.WarpSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileAPI {
    private interface InterfaceFile {
        void create(String filename, boolean saveResource);
        void load();
        void save();
        void set(String path, Object s);
    }

    public static class UtilFile implements InterfaceFile {
        private WarpSystem plugin;
        private File file;
        private FileConfiguration fileConfiguration;

        public UtilFile(WarpSystem plugin, File file, FileConfiguration fc) {
            this.plugin = plugin;
            this.file = file;
            this.fileConfiguration = fc;
        }

        @Override
        public void create(String filename, boolean saveResource) {
            file = new File(plugin.getDataFolder(), filename);
            fileConfiguration = new YamlConfiguration();

            if (!file.exists()) {
                file.getParentFile().mkdirs();

                if (saveResource == false) {
                    try {
                        file.createNewFile();
                        fileConfiguration.createSection("warps");
                        save();
                    } catch (IOException ex) {
                        ex.printStackTrace();
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
            } catch (Exception ex) {
                ex.printStackTrace();
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

    private static UtilFile UF;

    public static UtilFile select(WarpSystem plugin, File f, FileConfiguration fc) {
        UF = new UtilFile(plugin, f, fc);
        return UF;
    }
}
