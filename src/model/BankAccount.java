package model;

public abstract class BankAccount {
    private int idAccount;
    private String codeAccount;
    private String nameAccount;
    private String dateCreateAccount;

    public BankAccount(String codeAccount, String nameAccount, String dateCreateAccount) {
        this.idAccount = idAccount;
        this.codeAccount = codeAccount;
        this.nameAccount = nameAccount;
        this.dateCreateAccount = dateCreateAccount;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getCodeAccount() {
        return codeAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public String getDateCreateAccount() {
        return dateCreateAccount;
    }

    public abstract String toCSVString();

    @Override
    public String toString() {
        return "idAccount: " + idAccount +
                ", Mã tài khoản: " + codeAccount +
                ", Tên tài khoản: " + nameAccount +
                ", Ngày tạo: " + dateCreateAccount;
    }
}
