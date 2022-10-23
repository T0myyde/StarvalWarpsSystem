package ch.tom.tcodes.mysql.warps;

public class Warps {

    private int id;
    private String name;
    private double x;
    private double y;
    private double z;
    private String World;
    private double yaw;
    private double pitch;

    public Warps() {
    }

    public Warps(String name, double x, double y, double z, String world, double yaw, double pitch) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        World = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Warps(int id, String name, double x, double y, double z, String world, double yaw, double pitch) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        World = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getWorld() {
        return World;
    }

    public void setWorld(String world) {
        World = world;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return "Warps{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", World='" + World + '\'' +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                '}';
    }
}
