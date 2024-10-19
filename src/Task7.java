import java.util.ArrayList;
import java.util.List;

class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public BankAccount[] getAccounts(Bank bank) {
        return bank.getAccountsByUser(this);
    }
}

class Bank {
    private final String name;
    private final String currency;
    private final List<BankAccount> accounts = new ArrayList<>();

    public Bank(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }


    public BankAccount[] getAccountsByUser(User user) {
        return accounts.stream()
                .filter(acc -> acc.getOwner().equals(user)).toArray(BankAccount[]::new);
    }

    public BankAccount getAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}

class BankAccount {
    private final String accountNumber;
    private final User owner;
    private final Bank bank;
    private double balance;

    public BankAccount(String accountNumber, User owner, Bank bank, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.bank = bank;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getOwner() {
        return owner;
    }

    public Bank getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void printBalance() {
        System.out.println("Баланс на рахунку " + accountNumber + ": " + balance + " " + bank.getCurrency());
    }
}

class TransferService {
    public static double getConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.9;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 1.1;
        }
        return 1;
    }

    public static void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount.withdraw(amount)) {
            double commission = calculateCommission(fromAccount, toAccount);
            double convertedAmount = amount - amount * commission;

            toAccount.deposit(convertedAmount);
            System.out.println("Переказ відбувся. Комісія: " + commission * 100 + "%");
            fromAccount.printBalance();
            toAccount.printBalance();
        } else {
            System.out.println("Недостатньо коштів.");
        }
    }

    private static double calculateCommission(BankAccount fromAccount, BankAccount toAccount) {
        if (fromAccount.getOwner().equals(toAccount.getOwner())) {
            if (fromAccount.getBank().equals(toAccount.getBank())) {
                return 0.0;
            } else {
                return 0.02;
            }
        } else {
            if (fromAccount.getBank().equals(toAccount.getBank())) {
                return 0.03;
            } else {
                return 0.06;
            }
        }
    }
}

public class Task7 {
    public static void main(String[] args) {

        Bank bank1 = new Bank("mono", "USD");
        Bank bank2 = new Bank("Private", "EUR");

        User user1 = new User("Petro");
        User user2 = new User("Yura");

        // рахунки
        BankAccount account1 = new BankAccount("1", user1, bank1, 1000);
        BankAccount account2 = new BankAccount("2", user1, bank2, 500);
        BankAccount account3 = new BankAccount("3", user2, bank1, 300);

        bank1.addAccount(account1);
        bank2.addAccount(account2);
        bank1.addAccount(account3);

        System.out.println("Переказ між власними рахунками в різних банках:");
        TransferService.transfer(account1, account2, 200);

        System.out.println("\nПереказ між різними користувачами в одному банку:");
        TransferService.transfer(account1, account3, 100);
    }
}
