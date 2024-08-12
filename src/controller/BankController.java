package controller;

import exception.DuplicateBankAccountException;
import exception.InvalidDataException;
import model.BankAccount;
import model.PayAccount;
import model.SavingsAccount;
import service.BankService;
import util.CSVUtils;
import validator.Validator;

import java.util.List;
import java.util.Scanner;

public class BankController {
    private BankService bankService;
    private static final String FILE_PATH = "src/data/bank_accounts.csv";

    public BankController() {
        List<BankAccount> listBankAccount = CSVUtils.readListBankAccount(FILE_PATH);
        bankService = new BankService(listBankAccount);
    }

    public void addAccount(Scanner scanner) {
        String codeAccount;
        String nameAccount;
        String dateCreateAccount;

        while (true) {
            try {
                System.out.println("Mã tài khoản: ");
                codeAccount = scanner.nextLine();
                Validator.validateCodeAccount(codeAccount);
                Validator.checkDuplicateBankAccount(bankService.getListBankAccount(), codeAccount);
                break;
            } catch (DuplicateBankAccountException | InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Nhập tên bệnh nhân: ");
                nameAccount = scanner.nextLine();
                Validator.validateNameAccount(nameAccount);
                break;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Ngày tạo tài khoản (dd/MM/yyyy): ");
                dateCreateAccount = scanner.nextLine();
                Validator.validateDate(dateCreateAccount);
                break;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        BankAccount bankAccount = null;
        while (bankAccount == null) {
            try {
                System.out.println("Loại tài khoản");
                System.out.println("1. Tài khoản tiết kiệm");
                System.out.println("2. Tài khoản thanh toán");
                System.out.println("Chọn: ");
                int typeBankAccount = Integer.parseInt(scanner.nextLine());
                if (typeBankAccount == 1) {
                    String savingsMoney;
                    String dateSavings;
                    String interestRate;
                    String duration;
                    while (true) {
                        try {
                            System.out.println("Số tiền tiết kiệm: ");
                            savingsMoney = scanner.nextLine();
                            Validator.validateMoney(savingsMoney);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Ngày gửi (dd/MM/yyyy): ");
                            dateSavings = scanner.nextLine();
                            Validator.validateDate(dateSavings);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.println("Lãi suất: ");
                            interestRate = scanner.nextLine();
                            Validator.validateMoney(interestRate);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Kỳ hạn (dd/MM/yyyy): ");
                            duration = scanner.nextLine();
                            Validator.validateDate(duration);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bankAccount = new SavingsAccount(codeAccount, nameAccount, dateCreateAccount,
                            Double.parseDouble(savingsMoney), dateSavings, Double.parseDouble(interestRate), duration);
                } else if (typeBankAccount == 2) {
                    String cardNumber;
                    String moneyInAccount;
                    while (true) {
                        try {
                            System.out.println("Số thẻ: ");
                            cardNumber = scanner.nextLine();
                            Validator.validateCardNumber(cardNumber);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.println("Số tiền trong tài khoản: ");
                            moneyInAccount = scanner.nextLine();
                            Validator.validateMoney(moneyInAccount);
                            break;
                        } catch (InvalidDataException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bankAccount = new PayAccount(codeAccount, nameAccount, dateCreateAccount, cardNumber, moneyInAccount);
                } else {
                    System.out.println("Không có lựa chọn này");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng chọn số hợp lệ");
            }
        }
        int idNewAccount = bankService.getListBankAccount().isEmpty() ? 1 :bankService.getListBankAccount().get(bankService.getListBankAccount().size() - 1).getIdAccount() + 1;
        bankAccount.setIdAccount(idNewAccount);
        bankService.addAccount(bankAccount);
        System.out.println("Thêm tài khoản thành công");
    }

    public void showAccount() {
        List<BankAccount> listBankAccount = bankService.getListBankAccount();
        if (listBankAccount.isEmpty()) {
            System.out.println("Chưa có tài khoản nào được tạo");
        } else {
            for (BankAccount bankAccount : listBankAccount) {
                System.out.println(bankAccount);
            }
        }
    }

    public void deleteAccount(Scanner scanner) {
        showAccount();
        while (true) {
            try {
                System.out.println("Mã tài khoản: ");
                String codeAccount = scanner.nextLine();
                Validator.validateCodeAccount(codeAccount);
                boolean deleteSuccess = bankService.deleteAccount(codeAccount, scanner);
                if (deleteSuccess) {
                    System.out.println("Xóa tài khoản thành công");
                    showAccount();
                    break;
                } else {
                    System.out.println("Hủy bỏ việc xóa");
                    break;
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void searchAccount(Scanner scanner) {
        System.out.print("Nhập mã tài khoản để tìm kiếm: ");
        String codeAccount = scanner.nextLine();
        BankAccount bankAccount = bankService.searchAccount(codeAccount);
        if (bankAccount != null) {
            System.out.println(bankAccount);
        } else {
            System.out.println("Không tìm thấy tài khoản với mã đã nhập.");
        }
    }
}
