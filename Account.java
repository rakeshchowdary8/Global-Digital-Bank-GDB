public class Account {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private  int accNumber;
    private String name;
    private int age;
    private double balance;
    private String accType;
    private String status;
    private Integer pin;

    public Account(int accNumber, String name, int age, double balance, String accType) throws IllegalArgumentException {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Age must be 18 or older to open an account.");
        }

        if (accType == null || (!accType.equalsIgnoreCase("Savings") && !accType.equalsIgnoreCase("Current"))) {
            throw new IllegalArgumentException("Invalid account type. Choose Savings or Current.");
        }

        this.accType = accType.equalsIgnoreCase("Savings") ? "Savings" : "Current";

        double requiredBalance = this.accType.equals("Savings") ? MIN_BALANCE_SAVINGS : MIN_BALANCE_CURRENT;
        if (balance < requiredBalance) {
            throw new IllegalArgumentException("Initial deposit too low. Minimum is " + requiredBalance);
        }

        this.accNumber = accNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.status = "Active";
        this.pin = null;
    }

    public Account(String accType,double balance){
        this.accType = accType;
        this.balance = balance;
    }

    public void deposit(double amt) throws InvalidAmountException, InactiveAccountException {
        checkActive();

        if (amt <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive: " + amt);
        }

        this.balance += amt;
    }

    public void withdraw(double amt, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException {

        checkActive();

        if (this.pin == null || this.pin != pin) {
            throw new InvalidPinException("Incorrect PIN or PIN is not set.");
        }

        if (amt <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than 0.");
        }

        if (amt > this.balance) {
            throw new InsufficientBalanceException("Not enough balance for withdrawal.");
        }

        double minBal = getMinBalance();
        if ((this.balance - amt) < minBal) {
            throw new MinimumBalanceViolationException("Cannot withdraw: balance would drop below minimum " + minBal);
        }

        this.balance -= amt;
    }

    public void closeAccount() throws IllegalStateException {
        if ("Inactive".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account is already closed.");
        }
        this.status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {
        if ("Active".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account is already active.");
        }
        this.status = "Active";
    }

    public void setPin(int pin) throws IllegalArgumentException {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number (1000 to 9999).");
        }
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin != null && this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    private void checkActive() throws InactiveAccountException {
        if (!"Active".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Cannot perform operation on an inactive account.");
        }
    }

    private double getMinBalance() {
        return "Savings".equalsIgnoreCase(this.accType) ? MIN_BALANCE_SAVINGS : MIN_BALANCE_CURRENT;
    }

    public int getAccountNumber() {
        return accNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accType;
    }

    public String getStatus() {
        return status;
    }
}

class SavingsAccount extends Account{
    private double minBalance = 1000;
    private double interestRate = 0.04;
   public SavingsAccount(double balance){
    super("Savings",balance);
   }
}