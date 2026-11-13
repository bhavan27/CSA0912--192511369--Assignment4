import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeeWindow extends JFrame {
    private final JTextField regNo = new JTextField(16);
    private final JTextField amount = new JTextField(16);
    private final FeeService service = new FeeService();
    private final JLabel status = new JLabel("Enter fee details.");

    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        form.add(new JLabel("Registration number:")); form.add(regNo);
        form.add(new JLabel("Amount:")); form.add(amount);
        JPanel actions = new JPanel(new FlowLayout());
        JButton pay = new JButton("Pay"), clear = new JButton("Clear"), receipt = new JButton("Receipt");
        actions.add(pay); actions.add(clear); actions.add(receipt);
        status.setBorder(BorderFactory.createEmptyBorder(5, 15, 12, 15));
        add(form, BorderLayout.CENTER); add(actions, BorderLayout.NORTH); add(status, BorderLayout.SOUTH);

        pay.addActionListener(e -> processPayment());
        clear.addActionListener(e -> { regNo.setText(""); amount.setText(""); status.setText("Fields cleared."); });
        receipt.addActionListener(e -> JOptionPane.showMessageDialog(this, service.getReceipt(), "Receipt", JOptionPane.INFORMATION_MESSAGE));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        pack(); setLocationRelativeTo(null);
    }

    private void processPayment() {
        try {
            String message = service.pay(regNo.getText(), amount.getText());
            status.setText("Payment successful.");
            JOptionPane.showMessageDialog(this, message, "Payment", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            status.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeeWindow().setVisible(true));
    }
}
