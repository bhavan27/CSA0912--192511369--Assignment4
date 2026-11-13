import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ConverterAnonymous extends JFrame {
    private final JTextField amount=new JTextField(10);
    private final JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});
    public ConverterAnonymous(){
        super("Converter – Anonymous Inner Class");setLayout(new FlowLayout());
        JButton button=new JButton("Convert");add(new JLabel("INR"));add(amount);add(target);add(button);
        ActionListener handler=new ActionListener(){public void actionPerformed(ActionEvent e){convert();}};
        button.addActionListener(handler);amount.addActionListener(handler);
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);pack();setLocationRelativeTo(null);
    }
    private void convert(){try{double v=Double.parseDouble(amount.getText().trim());double rate="USD".equals(target.getSelectedItem())?0.012:0.011;JOptionPane.showMessageDialog(this,String.format("%.2f %s",v*rate,target.getSelectedItem()));}catch(Exception ex){JOptionPane.showMessageDialog(this,"Invalid amount");}}
    public static void main(String[]x){SwingUtilities.invokeLater(()->new ConverterAnonymous().setVisible(true));}
}
