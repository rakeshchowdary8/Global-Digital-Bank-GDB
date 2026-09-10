public class CurrentAccount extends Account {
    private double overdraftLimit = 25000.0;

    public CurrentAccount(double balance) {
        super("CURRENT", balance);
    }

    public CurrentAccount(double balance, double overdraftLimit) {
        super("CURRENT", balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}