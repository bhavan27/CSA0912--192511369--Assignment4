import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ConverterNamedInner extends JFrame {
    private final JTextField amount=new JTextField(10);
    private final JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});
    public ConverterNamedInner(){
        super("Converter – Named Inner Class");setLayout(new FlowLayout());
        JButton button=new JButton("Convert");add(new JLabel("INR"));add(amount);add(target);add(button);
        ConvertHandler handler=new ConvertHandler();button.addActionListener(handler);amount.addActionListener(handler);
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);pack();setLocationRelativeTo(null);
    }
    private class ConvertHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){convert();}
    }
    private void convert(){try{double v=Double.parseDouble(amount.getText().trim());double rate="USD".equals(target.getSelectedItem())?0.012:0.011;JOptionPane.showMessageDialog(this,String.format("%.2f %s",v*rate,target.getSelectedItem()));}catch(Exception ex){JOptionPane.showMessageDialog(this,"Invalid amount");}}
    public static void main(String[]x){SwingUtilities.invokeLater(()->new ConverterNamedInner().setVisible(true));}
}
