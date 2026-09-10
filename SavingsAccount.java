public class SavingsAccount extends Account {
    private double minBalance = 1000.0;
    private double interestRate = 4.0;

    public SavingsAccount(double balance) {
        super("SAVINGS", balance);
    }

    public SavingsAccount(double balance, double minBalance, double interestRate) {
        super("SAVINGS", balance);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        try {
            deposit(interest);
        } catch (InvalidAmountException | InactiveAccountException e) {
            System.err.println(e.getMessage());
        }
    }

    public double getMinimumBalance() {
        return minBalance;
    }

    public void setMinimumBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}