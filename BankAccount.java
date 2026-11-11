import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    // Instance variables
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected String accountType;
    protected List<Transaction> transactionHistory;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.transactionHistory = new ArrayList<>();
        
        // Record initial deposit
        if (initialBalance > 0) {
            addTransaction("Initial Deposit", initialBalance, "CREDIT");
        }
    }
    
    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        addTransaction("Deposit", amount, "CREDIT");
        System.out.println("Successfully deposited: ₹" + amount);
    }
    
    // Withdraw method
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: ₹" + balance);
        }
        this.balance -= amount;
        addTransaction("Withdrawal", amount, "DEBIT");
        System.out.println("Successfully withdrawn: ₹" + amount);
    }
    
    // Get balance
    public double getBalance() {
        return balance;
    }
    
    // Add transaction to history
    protected void addTransaction(String description, double amount, String type) {
        Transaction transaction = new Transaction(description, amount, type, this.balance);
        transactionHistory.add(transaction);
    }
    
    // Display account details
    public void displayAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: ₹" + balance);
    }
    
    // Display transaction history
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for Account: " + accountNumber + " ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public String getAccountType() { return accountType; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
}
