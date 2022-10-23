package ch.tom.tcodes.warpstarvalcity.mysql.warps;

import ch.tom.tcodes.warpstarvalcity.WarpStarvalcity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarpsRepositorympl implements WarpsRepository {

    private WarpStarvalcity plugin = WarpStarvalcity.getInstance();

    @Override
    public void create(Warps warps) {
        try {
            Connection connection = plugin.getMySQL().getConnection();
            String sql = "INSERT INTO `warps` (name, x,y,z,yaw,pitch, world) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, warps.getName());
            preparedStatement.setDouble(2, warps.getX());
            preparedStatement.setDouble(3, warps.getY());
            preparedStatement.setDouble(4, warps.getZ());
            preparedStatement.setDouble(5, warps.getYaw());
            preparedStatement.setDouble(6, warps.getPitch());
            preparedStatement.setString(7, warps.getWorld());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Warps warps) {
        try {
            Connection connection = plugin.getMySQL().getConnection();
            String sql = "DELETE from `warps` WHERE id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, warps.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Warps get(int id) {
        Warps warps = new Warps();
        try {
            Connection connection = plugin.getMySQL().getConnection();
            String sql = "SELECT * FROM `warps` WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                warps.setId(rs.getInt("id"));
                warps.setName(rs.getString("name"));
                warps.setWorld(rs.getString("world"));
                warps.setX(rs.getDouble("x"));
                warps.setY(rs.getDouble("y"));
                warps.setZ(rs.getDouble("z"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warps;
    }

    @Override
    public Warps get(String name) {
        Warps warps = new Warps();
        try {
            Connection connection = plugin.getMySQL().getConnection();
            String sql = "SELECT * FROM `warps` WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                warps.setId(rs.getInt("id"));
                warps.setName(rs.getString("name"));
                warps.setWorld(rs.getString("world"));
                warps.setX(rs.getDouble("x"));
                warps.setY(rs.getDouble("y"));
                warps.setZ(rs.getDouble("z"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warps;
    }

    @Override
    public ArrayList<Warps> getList() {
        ArrayList<Warps> list = new ArrayList<>();
        try {
            Connection connection = plugin.getMySQL().getConnection();
            String sql = "SELECT * FROM `warps`";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Warps warps = new Warps();
                warps.setId(rs.getInt("id"));
                warps.setName(rs.getString("name"));
                warps.setWorld(rs.getString("world"));
                warps.setX(rs.getDouble("x"));
                warps.setY(rs.getDouble("y"));
                warps.setZ(rs.getDouble("z"));
                list.add(warps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
