package lesson2;

public class Account {
    private String id;
    private String owner;
    private int balance;

    public Account(String id, String owner, int balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }
    public String getOwner() {
        return owner;
    }
    public int getBalance() {
        return balance;
    }

    // Add money and return updated balance
    // if < 0 --> say "negative"
    public int addMoney(int money) {
        if (money < 0)
            System.out.println("Negative amount");
        else
            balance += money;
        return balance;
    }

    // Subtracting money from account
    // if account will become < 0 --> display error message and don't subtract
    public int subtractMoney(int money) {
        if (balance < money)
            System.out.println("ERROR: Subtract amount is bigger then balance");
        else
            balance -= money;
        return balance;
    }

    // перевод денег со счета на счет
    // списываем деньги с одного счета и добавляем их на другой
    // возвращается баланс счета с которого деньги списались
    // при попытке снять больше баланс нужно выводить сообщения как вверху
    // account - счет куда переводим
    // amount - сумма которую переводим
    public int transfer(Account account, int amount) {
        // balance - деньги на счете до перевода
        // subtractMoney(amount) - деньги на счете после снятия (остаток)
        int amountToTransfer = (balance - subtractMoney(amount));
        account.addMoney(amountToTransfer);
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) {
        Account a1 = new Account("123", "Misha Semenov", 30);
        Account a2 = new Account("456", "Dima Petrov", 60);

        a1.transfer(a2, 15);
        System.out.println(a1);
        System.out.println(a2);

        a1.transfer(a2, 100);
        System.out.println(a1);
        System.out.println(a2);
    }
    



}
