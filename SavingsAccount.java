public class SavingsAccount extends BankAccount {
    private double interestRate;
    private double minimumBalance;
    
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance, double interestRate) {
        super(accountNumber, accountHolderName, initialBalance, "Savings");
        this.interestRate = interestRate;
        this.minimumBalance = 1000.0; // Minimum balance for savings account
    }
    
    // Override withdraw method to check minimum balance
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount < minimumBalance) {
            throw new InsufficientFundsException(
                "Cannot withdraw. Minimum balance of ₹" + minimumBalance + " must be maintained."
            );
        }
        super.withdraw(amount);
    }
    
    // Method to calculate and add interest
    public void addInterest() {
        double interest = balance * (interestRate / 100);
        this.balance += interest;
        addTransaction("Interest Credit", interest, "CREDIT");
        System.out.println("Interest of ₹" + interest + " added to account.");
    }
    
    // Getters
    public double getInterestRate() { return interestRate; }
    public double getMinimumBalance() { return minimumBalance; }
}
