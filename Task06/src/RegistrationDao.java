import java.sql.*;
import java.util.*;
public class RegistrationDao {
    public void insert(String studentId,String name,String event,String email)throws SQLException{
        String sql="INSERT INTO registrations(student_id,student_name,event_name,email) VALUES(?,?,?,?)";
        try(Connection c=Db.connect();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,studentId);ps.setString(2,name);ps.setString(3,event);ps.setString(4,email);ps.executeUpdate();
        }
    }
    public List<Registration> page(String event,int limit,int offset)throws SQLException{
        String sql="SELECT id,student_id,student_name,event_name,email FROM registrations "
            +"WHERE (?='All' OR event_name=?) ORDER BY id LIMIT ? OFFSET ?";
        List<Registration> rows=new ArrayList<>();
        try(Connection c=Db.connect();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,event);ps.setString(2,event);ps.setInt(3,limit);ps.setInt(4,offset);
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()) rows.add(new Registration(rs.getInt("id"),rs.getString("student_id"),
                    rs.getString("student_name"),rs.getString("event_name"),rs.getString("email")));
            }
        }
        return rows;
    }
}
