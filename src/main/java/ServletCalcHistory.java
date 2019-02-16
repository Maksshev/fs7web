import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class ServletCalcHistory extends HttpServlet {
    private final Connection conn;

    public ServletCalcHistory(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT * FROM history1";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();
            while (rSet.next()) {
                int a = rSet.getInt("a");
                int b = rSet.getInt("b");
                int r = rSet.getInt("r");
                String op = rSet.getString("op");
                Timestamp at = rSet.getTimestamp("at");
                sb.append(a).append(op).append(b).append("=").append(r)
                        .append(" at:").append(at).append("\n");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("smth went wrong", e);
        }
        resp.getWriter().println(sb);
    }
}
