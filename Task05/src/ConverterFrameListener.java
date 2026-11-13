import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ConverterFrameListener extends JFrame implements ActionListener {
    private final JTextField amount=new JTextField(10);
    private final JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});
    public ConverterFrameListener(){
        super("Converter – Frame Listener");setLayout(new FlowLayout());
        JButton button=new JButton("Convert");add(new JLabel("INR"));add(amount);add(target);add(button);
        button.addActionListener(this);amount.addActionListener(this);
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);pack();setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e){convert();}
    private void convert(){try{double v=Double.parseDouble(amount.getText().trim());showResult(v);}catch(Exception ex){JOptionPane.showMessageDialog(this,"Invalid amount");}}
    private void showResult(double v){double rate="USD".equals(target.getSelectedItem())?0.012:0.011;JOptionPane.showMessageDialog(this,String.format("%.2f %s",v*rate,target.getSelectedItem()));}
    public static void main(String[]x){SwingUtilities.invokeLater(()->new ConverterFrameListener().setVisible(true));}
}
