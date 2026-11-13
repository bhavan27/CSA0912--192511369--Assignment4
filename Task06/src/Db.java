import java.sql.*;
public final class Db {
    private Db() {}
    public static Connection connect() throws SQLException {
        String url=System.getenv().getOrDefault("CO4_DB_URL","jdbc:mysql://localhost:3306/campus_events");
        String user=System.getenv().getOrDefault("CO4_DB_USER","root");
        String password=System.getenv().getOrDefault("CO4_DB_PASSWORD","");
        return DriverManager.getConnection(url,user,password);
    }
}
