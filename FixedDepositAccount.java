public class FixedDepositAccount extends Account {
    private int tenureMonths = 12;
    private double interestRate = 6.5;

    public FixedDepositAccount(double balance) {
        super("FIXED_DEPOSIT", balance);
    }

    public FixedDepositAccount(double balance, int tenureMonths, double interestRate) {
        super("FIXED_DEPOSIT", balance);
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
    }

    public double calculateMaturityAmount() {
        double timeInYears = tenureMonths / 12.0;
        return getBalance() * Math.pow(1.0 + (interestRate / 100.0), timeInYears);
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}