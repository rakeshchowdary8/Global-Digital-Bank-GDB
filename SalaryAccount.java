public class SalaryAccount extends Account {
    private String employerName;
    private int inactiveMonths;

    public SalaryAccount(double balance, String employerName) {
        super("SALARY", balance);
        this.employerName = employerName;
        this.inactiveMonths = 0;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public int getInactiveMonths() {
        return inactiveMonths;
    }

    public void setInactiveMonths(int inactiveMonths) {
        this.inactiveMonths = inactiveMonths;
    }
}
