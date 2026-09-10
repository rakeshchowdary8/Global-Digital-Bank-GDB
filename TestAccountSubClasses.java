public class TestAccountSubClasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 7: Account Subclasses Test ===");

        SavingsAccount sa = new SavingsAccount(10000.0);
        System.out.println("Savings Account Created: Balance Rs " + sa.getBalance() + " | Min Balance: Rs " + sa.getMinimumBalance());

        CurrentAccount ca = new CurrentAccount(5000.0);
        System.out.println("Current Account Created: Overdraft Limit Rs " + ca.getOverdraftLimit());

        FixedDepositAccount fda = new FixedDepositAccount(50000.0);
        System.out.println("Fixed Deposit Created: Tenure " + fda.getTenureMonths() + " months | Interest: " + fda.getInterestRate() + "%");

        SalaryAccount sla = new SalaryAccount(20000.0, "Infosys");
        System.out.println("Salary Account Created: Employer " + sla.getEmployerName());

        System.out.println("All subclasses instantiated successfully!");
    }
}