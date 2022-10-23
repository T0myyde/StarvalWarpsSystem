package ch.tom.tcodes.mysql.warps;

import java.util.ArrayList;

public class WarpsService implements WarpsRepository {

    private WarpsRepositorympl warpsRepositorympl = new WarpsRepositorympl();

    @Override
    public void create(Warps warps) {
        this.warpsRepositorympl.create(warps);
    }

    @Override
    public void delete(Warps warps) {
        this.warpsRepositorympl.delete(warps);
    }

    @Override
    public Warps get(int id) {
        return this.warpsRepositorympl.get(id);
    }

    @Override
    public Warps get(String name) {
        return this.warpsRepositorympl.get(name);
    }

    @Override
    public ArrayList<Warps> getList() {
        return this.warpsRepositorympl.getList();
    }
}
