package model;

public class PayAccount extends BankAccount{
    private String cardNumber;
    private String moneyInAccount;

    public PayAccount(String codeAccount, String nameAccount,
                      String dateCreateAccount, String cardNumber, String moneyInAccount) {
        super(codeAccount, nameAccount, dateCreateAccount);
        this.cardNumber = cardNumber;
        this.moneyInAccount = moneyInAccount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getMoneyInAccount() {
        return moneyInAccount;
    }

    @Override
    public String toCSVString() {
        return getIdAccount() + "," + getCodeAccount() + "," + getNameAccount() + "," + getDateCreateAccount()
                + "," + getCardNumber() + "," + getMoneyInAccount();
    }

    @Override
    public String toString() {
        return super.toString() + ", Số thẻ: " + getCardNumber() + ", Số tiền trong tài khoản: " + getMoneyInAccount();
    }
}
