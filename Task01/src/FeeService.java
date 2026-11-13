public class FeeService {
    private String lastReceipt = "";

    public String pay(String regNo, String amountText) {
        if (regNo == null || !regNo.trim().matches("[A-Za-z0-9-]{3,20}")) {
            throw new IllegalArgumentException("Enter a valid registration number.");
        }
        final double amount;
        try {
            amount = Double.parseDouble(amountText.trim());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Enter a numeric amount.");
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount > 100000) {
            throw new IllegalArgumentException("Amount exceeds the permitted limit.");
        }
        // Demonstration payment operation; replaceable by a JDBC/payment gateway adapter.
        lastReceipt = "Receipt: " + regNo.trim() + " | Amount: " + String.format("%.2f", amount);
        return "Payment successful.\n" + lastReceipt;
    }

    public String getReceipt() {
        if (lastReceipt.isEmpty()) return "No successful payment is available.";
        return lastReceipt;
    }
}
