import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts;
    private String bankName;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new HashMap<>();
        initializeSampleData();
    }
    
    // Create new account
    public void createAccount(String accountType, String accountNumber, String accountHolderName, double initialBalance, double specialFeature) {
        BankAccount account;
        
        switch (accountType.toLowerCase()) {
            case "savings":
                account = new SavingsAccount(accountNumber, accountHolderName, initialBalance, specialFeature);
                break;
            case "current":
                account = new CurrentAccount(accountNumber, accountHolderName, initialBalance, specialFeature);
                break;
            default:
                account = new BankAccount(accountNumber, accountHolderName, initialBalance, "Basic");
                break;
        }
        
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully!");
        account.displayAccountInfo();
    }
    
    // Get account by account number
    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }
    
    // Transfer money between accounts
    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
        try {
            BankAccount fromAccount = getAccount(fromAccountNumber);
            BankAccount toAccount = getAccount(toAccountNumber);
            
            // Withdraw from source account
            fromAccount.withdraw(amount);
            
            // Deposit to target account
            toAccount.deposit(amount);
            
            // Add transfer descriptions
            fromAccount.addTransaction("Transfer to " + toAccountNumber, amount, "DEBIT");
            toAccount.addTransaction("Transfer from " + fromAccountNumber, amount, "CREDIT");
            
            System.out.println("Transfer successful!");
            System.out.println("Transferred ₹" + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
            
        } catch (InsufficientFundsException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
    
    // Display all accounts
    public void displayAllAccounts() {
        System.out.println("\n=== All Accounts in " + bankName + " ===");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount account : accounts.values()) {
                account.displayAccountInfo();
                System.out.println("------------------------");
            }
        }
    }
    
    // Initialize with sample data
    private void initializeSampleData() {
        createAccount("savings", "SA001", "Alice Johnson", 5000.0, 4.5);
        createAccount("current", "CA001", "Bob Smith", 10000.0, 5000.0);
        createAccount("savings", "SA002", "Carol Davis", 3000.0, 3.5);
    }
}
