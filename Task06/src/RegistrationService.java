import java.sql.*;
public class RegistrationService {
    private final RegistrationDao dao=new RegistrationDao();
    public void register(String studentId,String name,String event,String email)throws Exception{
        if(studentId==null||!studentId.trim().matches("[A-Za-z0-9-]{3,20}"))throw new IllegalArgumentException("Enter a valid student ID.");
        if(name==null||name.trim().length()<2)throw new IllegalArgumentException("Enter the student's name.");
        if(event==null||event.trim().isEmpty())throw new IllegalArgumentException("Select an event.");
        if(email==null||!email.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))throw new IllegalArgumentException("Enter a valid email address.");
        try{dao.insert(studentId.trim(),name.trim(),event.trim(),email.trim());}
        catch(SQLException ex){
            if("23000".equals(ex.getSQLState()))throw new IllegalArgumentException("This student is already registered for this event.");
            throw ex;
        }
    }
}
