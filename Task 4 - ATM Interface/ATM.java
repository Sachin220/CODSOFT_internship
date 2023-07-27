import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private double balance = 10000; 

    private JLabel balanceLabel;
    private JTextField amountField;

    public ATM() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("ATM Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Components
        JLabel titleLabel = new JLabel("Welcome to This ATM");
        balanceLabel = new JLabel("Balance: Rs." + balance);
        JLabel amountLabel = new JLabel("Enter amount:");

        amountField = new JTextField(10);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton viewBalanceButton = new JButton("View Balance");

        // Action listeners
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawAmount();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositAmount();
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBalance();
            }
        });

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(titleLabel);
        panel.add(balanceLabel);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(viewBalanceButton);

        add(panel);
    }

    private void withdrawAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0 || amount > balance) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            } else {
                balance -= amount;
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Rs." + amount + " withdrawn successfully.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void depositAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            } else {
                balance += amount;
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Rs." + amount + " deposited successfully.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this, "Your balance: Rs. " + balance);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: Rs." + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATM().setVisible(true);
            }
        });
    }
}
