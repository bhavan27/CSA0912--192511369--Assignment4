import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventRegistrationApp extends JFrame {
    private final JTextField studentId=new JTextField(12), name=new JTextField(14), email=new JTextField(16);
    private final JComboBox<String> event=new JComboBox<>(new String[]{"TechFest","Cultural Night","Sports Meet","Workshop"});
    private final JComboBox<String> filter=new JComboBox<>(new String[]{"All","TechFest","Cultural Night","Sports Meet","Workshop"});
    private final DefaultTableModel model=new DefaultTableModel(new Object[]{"ID","Student ID","Name","Event","Email"},0);
    private final JTable table=new JTable(model);
    private final JLabel status=new JLabel("Ready");
    private final RegistrationService service=new RegistrationService();
    private int offset=0; private static final int PAGE_SIZE=25;

    public EventRegistrationApp(){
        super("Campus Event Registration");
        setLayout(new BorderLayout(8,8));
        JMenuBar bar=new JMenuBar(); JMenu file=new JMenu("File"); JMenuItem export=new JMenuItem("Export current page");
        JMenuItem refresh=new JMenuItem("Refresh"); file.add(refresh);file.add(export);bar.add(file);setJMenuBar(bar);
        JPanel form=new JPanel(new GridLayout(4,2,6,6));form.setBorder(BorderFactory.createTitledBorder("Student registration"));
        form.add(new JLabel("Student ID"));form.add(studentId);form.add(new JLabel("Student name"));form.add(name);
        form.add(new JLabel("Event"));form.add(event);form.add(new JLabel("Email"));form.add(email);
        JButton register=new JButton("Register"); JPanel formWrap=new JPanel(new BorderLayout());formWrap.add(form,BorderLayout.CENTER);formWrap.add(register,BorderLayout.SOUTH);
        JPanel filterPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));filterPanel.add(new JLabel("Filter event:"));filterPanel.add(filter);
        JButton apply=new JButton("Apply"),prev=new JButton("Previous"),next=new JButton("Next");
        filterPanel.add(apply);filterPanel.add(prev);filterPanel.add(next);
        JPanel top=new JPanel(new BorderLayout());top.add(formWrap,BorderLayout.NORTH);top.add(filterPanel,BorderLayout.SOUTH);
        add(top,BorderLayout.NORTH);add(new JScrollPane(table),BorderLayout.CENTER);add(status,BorderLayout.SOUTH);
        register.addActionListener(e->registerAsync());
        apply.addActionListener(e->{offset=0;loadPage();});refresh.addActionListener(e->{offset=0;loadPage();});
        prev.addActionListener(e->{offset=Math.max(0,offset-PAGE_SIZE);loadPage();});
        next.addActionListener(e->{offset+=PAGE_SIZE;loadPage();});
        export.addActionListener(e->exportCsv());
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);setSize(900,620);setLocationRelativeTo(null);
        loadPage();
    }
    private void registerAsync(){
        String sid=studentId.getText(),nm=name.getText(),ev=(String)event.getSelectedItem(),mail=email.getText();
        status.setText("Registering...");
        new SwingWorker<Void,Void>(){
            protected Void doInBackground()throws Exception{service.register(sid,nm,ev,mail);return null;}
            protected void done(){try{get();status.setText("Registration successful.");offset=0;loadPage();}
                catch(Exception ex){String m=ex.getCause()==null?ex.getMessage():ex.getCause().getMessage();status.setText(m);JOptionPane.showMessageDialog(EventRegistrationApp.this,m,"Registration error",JOptionPane.ERROR_MESSAGE);}}
        }.execute();
    }
    private void loadPage(){
        String selected=(String)filter.getSelectedItem();status.setText("Loading registrations...");
        new SwingWorker<List<Registration>,Void>(){
            protected List<Registration> doInBackground()throws Exception{return new RegistrationDao().page(selected,PAGE_SIZE,offset);}
            protected void done(){try{List<Registration> rows=get();model.setRowCount(0);for(Registration r:rows)model.addRow(new Object[]{r.id,r.studentId,r.studentName,r.eventName,r.email});status.setText("Loaded "+rows.size()+" registrations (offset "+offset+").");}
                catch(Exception ex){String m=ex.getCause()==null?ex.getMessage():ex.getCause().getMessage();status.setText("Database error: "+m);}}
        }.execute();
    }
    private void exportCsv(){
        JFileChooser chooser=new JFileChooser();if(chooser.showSaveDialog(this)!=JFileChooser.APPROVE_OPTION)return;
        try(PrintWriter w=new PrintWriter(chooser.getSelectedFile())){
            for(int c=0;c<model.getColumnCount();c++){if(c>0)w.print(",");w.print(model.getColumnName(c));}w.println();
            for(int r=0;r<model.getRowCount();r++){for(int c=0;c<model.getColumnCount();c++){if(c>0)w.print(",");w.print("\""+String.valueOf(model.getValueAt(r,c)).replace("\"","\"\"")+"\"");}w.println();}
            status.setText("Current page exported.");
        }catch(IOException ex){status.setText("Export failed: "+ex.getMessage());}
    }
    public static void main(String[] args){SwingUtilities.invokeLater(()->new EventRegistrationApp().setVisible(true));}
}
