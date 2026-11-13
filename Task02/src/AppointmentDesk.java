import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class AppointmentDesk extends JFrame {
    private final JTextField patientId = new JTextField(14);
    private final JComboBox<String> department = new JComboBox<>(new String[]{"Cardiology","Dermatology","Neurology"});
    private final JComboBox<String> doctor = new JComboBox<>();
    private final JComboBox<String> slot = new JComboBox<>(new String[]{"09:00","10:00","11:00","14:00","15:00"});
    private final JLabel status = new JLabel("Select a department and appointment details.");
    private final Map<String,List<String>> doctors = new LinkedHashMap<>();
    private boolean bookingInProgress = false;

    public AppointmentDesk() {
        super("Hospital Outpatient Appointment Desk");
        doctors.put("Cardiology", Arrays.asList("Dr. Anand","Dr. Meera"));
        doctors.put("Dermatology", Arrays.asList("Dr. Kiran","Dr. Nisha"));
        doctors.put("Neurology", Arrays.asList("Dr. Ravi","Dr. Leela"));
        JPanel form = new JPanel(new GridLayout(5,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(15,15,10,15));
        form.add(new JLabel("Patient ID")); form.add(patientId);
        form.add(new JLabel("Department")); form.add(department);
        form.add(new JLabel("Doctor")); form.add(doctor);
        form.add(new JLabel("Time slot")); form.add(slot);
        JButton book = new JButton("Book appointment");
        form.add(new JLabel("")); form.add(book);
        add(form,BorderLayout.CENTER); add(status,BorderLayout.SOUTH);
        department.addActionListener(e -> refreshDoctors());
        book.addActionListener(e -> bookAppointment());
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                if (bookingInProgress) {
                    int choice=JOptionPane.showConfirmDialog(AppointmentDesk.this,
                        "A booking is in progress. Close anyway?","Confirm close",JOptionPane.YES_NO_OPTION);
                    if(choice==JOptionPane.YES_OPTION) dispose();
                } else dispose();
            }
        });
        refreshDoctors(); setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    private void refreshDoctors() {
        doctor.removeAllItems();
        List<String> list=doctors.get((String)department.getSelectedItem());
        if(list!=null) for(String name:list) doctor.addItem(name);
    }
    private void bookAppointment() {
        String id=patientId.getText().trim();
        if(!id.matches("[A-Za-z0-9-]{3,20}")) { showError("Enter a valid patient ID."); return; }
        if(doctor.getSelectedItem()==null || slot.getSelectedItem()==null) { showError("Select a doctor and time slot."); return; }
        bookingInProgress=true;
        try {
            String msg="Appointment confirmed for "+id+"\n"+department.getSelectedItem()
                +" – "+doctor.getSelectedItem()+" at "+slot.getSelectedItem();
            JOptionPane.showMessageDialog(this,msg,"Booking confirmation",JOptionPane.INFORMATION_MESSAGE);
            status.setText("Appointment booked for patient "+id+".");
        } finally { bookingInProgress=false; }
    }
    private void showError(String message) {
        status.setText(message); JOptionPane.showMessageDialog(this,message,"Validation error",JOptionPane.ERROR_MESSAGE);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(()->new AppointmentDesk().setVisible(true)); }
}
