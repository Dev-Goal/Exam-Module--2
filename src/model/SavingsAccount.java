package model;

public class SavingsAccount extends BankAccount{
    private double savingsMoney;
    private String dateSavings;
    private double interestRate;
    private String duration;

    public SavingsAccount(String codeAccount, String nameAccount, String dateCreateAccount,
                          double savingsMoney, String dateSavings, double interestRate, String duration) {
        super(codeAccount, nameAccount, dateCreateAccount);
        this.savingsMoney = savingsMoney;
        this.dateSavings = dateSavings;
        this.interestRate = interestRate;
        this.duration = duration;
    }

    public double getSavingsMoney() {
        return savingsMoney;
    }

    public String getDateSavings() {
        return dateSavings;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toCSVString() {
        return getIdAccount() + "," + getCodeAccount() + "," + getNameAccount() + "," + getDateCreateAccount()
                + "," + getSavingsMoney() + "," + getDateSavings() + "," + getInterestRate() + "," + getDuration();
    }

    @Override
    public String toString() {
        return super.toString() +", Số tiền gửi: " + getSavingsMoney() + ", Ngày gửi: " + getDateSavings()
                + ", Lãi suất: " + getInterestRate() + ", Kỳ hạn: " + getDuration();
    }
}
