package ch.tom.tcodes.warpstarvalcity.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MySQL {

    public Connection connection;
    public MySQL() {
        Properties properties = new Properties();
        try {
            properties.put("user", "tcodesmc");
            properties.put("password", "CM8#xmjP2eUxRxS");
            properties.put("autoReconnect", "true");
            connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/tcodesmc?characterEncoding=utf8", properties);
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
