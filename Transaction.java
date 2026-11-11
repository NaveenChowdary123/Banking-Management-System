import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private String description;
    private double amount;
    private String type; // CREDIT or DEBIT
    private double balanceAfterTransaction;
    private String timestamp;
    
    public Transaction(String description, double amount, String type, double balanceAfterTransaction) {
        this.transactionId = generateTransactionId();
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s | %s: ₹%.2f | Balance: ₹%.2f", 
                           timestamp, transactionId, description, amount, balanceAfterTransaction);
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
    public double getBalanceAfterTransaction() { return balanceAfterTransaction; }
    public String getTimestamp() { return timestamp; }
}
