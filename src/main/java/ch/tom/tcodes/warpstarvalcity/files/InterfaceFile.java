package ch.tom.tcodes.warpstarvalcity.files;

public interface InterfaceFile {
    void create(String filename, boolean getResourceFromFile);
    void load();
    void save();
    void set(String path, Object s);
}
