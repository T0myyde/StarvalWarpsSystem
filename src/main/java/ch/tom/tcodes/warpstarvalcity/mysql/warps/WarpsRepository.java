package ch.tom.tcodes.warpstarvalcity.mysql.warps;

import java.util.ArrayList;

public interface WarpsRepository {

    void create(Warps warps);
    void delete (Warps warps);
    Warps get(int id);
    Warps get(String name);
    ArrayList<Warps> getList();
}
