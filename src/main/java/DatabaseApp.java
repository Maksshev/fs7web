import database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseApp {
    public static void main(String[] args) throws SQLException {
        Connection conn = new DbConnection().connection();
        String sql = "INSERT INTO authors (\"firstName\")  VALUES (?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, "DENIS");
        stm.execute();
    }
}
