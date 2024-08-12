package service;

import model.BankAccount;
import util.CSVUtils;

import java.util.List;
import java.util.Scanner;

public class BankService {
    private static final String FILE_PATH = "src/data/bank_accounts.csv";
    private List<BankAccount> listBankAccount;

    public BankService(List<BankAccount> listBankAccount) {
        this.listBankAccount = CSVUtils.readListBankAccount(FILE_PATH);
    }

    public List<BankAccount> getListBankAccount() {
        return listBankAccount;
    }

    public void addAccount(BankAccount bankAccount) {
        listBankAccount.add(bankAccount);
        CSVUtils.writeListBankAccount(FILE_PATH, listBankAccount);
    }

    public boolean deleteAccount(String codeAccount, Scanner scanner) {
        BankAccount bankAccountDelete = searchAccount(codeAccount);
        if (bankAccountDelete != null) {
            System.out.print("Bạn có chắc chắn muốn xóa tài khoản với mã " + codeAccount + " không? (y/n): ");
            String comfirm = scanner.nextLine();
            if (comfirm.equalsIgnoreCase("y")) {
                listBankAccount.remove(bankAccountDelete);
                CSVUtils.writeListBankAccount(FILE_PATH, listBankAccount);
                return true;
            }
        } else {
            System.out.println("Không tìm thấy tài khoản với mã đã nhập.");
        }
        return false;
    }

    public BankAccount searchAccount(String codeAccount) {
        return listBankAccount.stream()
                .filter(bankAccount -> bankAccount.getCodeAccount().equals(codeAccount))
                .findFirst().orElse(null);
    }
}
