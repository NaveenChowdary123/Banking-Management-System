public class CurrentAccount extends BankAccount {
    private double overdraftLimit;
    
    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialBalance, "Current");
        this.overdraftLimit = overdraftLimit;
    }
    
    // Override withdraw method to allow overdraft
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > (balance + overdraftLimit)) {
            throw new InsufficientFundsException(
                "Exceeds overdraft limit. Available: ₹" + (balance + overdraftLimit)
            );
        }
        this.balance -= amount;
        addTransaction("Withdrawal", amount, "DEBIT");
        System.out.println("Successfully withdrawn: ₹" + amount);
    }
    
    // Get available balance including overdraft
    public double getAvailableBalance() {
        return balance + overdraftLimit;
    }
    
    // Getter
    public double getOverdraftLimit() { return overdraftLimit; }
}
