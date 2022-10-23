package ch.tom.tcodes.mysql;

import ch.tom.tcodes.WarpSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MySQL {

    public Connection connection;

    private WarpSystem plugin = WarpSystem.getInstance();
    public MySQL() {
        Properties properties = new Properties();
        try {
            properties.put("user", plugin.getSqlCfg().get("mysql.user"));
            properties.put("password", plugin.getSqlCfg().get("mysql.password"));
            properties.put("autoReconnect", "true");
            connection = DriverManager.getConnection("jdbc:mysql://"+plugin.getSqlCfg().get("mysql.host")+":"+plugin.getSqlCfg().get("mysql.port")+"/"+plugin.getSqlCfg().get("mysql.database")+"?characterEncoding=utf8", properties);
            System.out.println("WarpSystem | MySQL Connected");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MySQL createTable() {
        try {

            String sql = "CREATE TABLE IF NOT EXISTS warps (id int NOT NULL AUTO_INCREMENT, name VARCHAR(255), x DOUBLE, y DOUBLE, z DOUBLE, world VARCHAR(255), yaw DOUBLE, pitch DOUBLE, PRIMARY KEY (id));";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }
}
