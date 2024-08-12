package validator;

import exception.DuplicateBankAccountException;
import exception.InvalidDataException;
import model.BankAccount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void checkDuplicateBankAccount(List<BankAccount> listBankAccount, String codeAccount) throws DuplicateBankAccountException {
        for (BankAccount bankAccount : listBankAccount) {
            if (bankAccount.getCodeAccount().equals(codeAccount)) {
                throw new DuplicateBankAccountException("Mã tài khoản đã có trong hệ thống");
            }
        }
    }

    public static void validateCodeAccount(String codeAccount) throws InvalidDataException {
        if (codeAccount.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        if (!Pattern.matches("00\\d{7}", codeAccount)) {
            throw new InvalidDataException("Mã tài khoản không hợp lệ. Định dạng đúng: 00XXXXXXX với X là các ký tự số.");
        }
    }

    public static void validateCardNumber(String cardNumber) throws InvalidDataException {
        if (cardNumber.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        if (!Pattern.matches("\\d{16}", cardNumber)) {
            throw new InvalidDataException("Số thẻ phải đủ 16 số");
        }
    }

    public static void validateNameAccount(String nameAccount) throws InvalidDataException {
        if (nameAccount.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        if (!Pattern.matches("[\\p{L} ]+", nameAccount)) {
            throw new InvalidDataException("Tên tài khoản không hợp lệ. Tên chỉ được chứa các ký tự chữ và khoảng trắng.");
        }
    }

    public static void validateDate(String date) throws InvalidDataException {
        if (date.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidDataException("Ngày không hợp lệ. Định dạng đúng: dd/MM/yyyy.");
        }
    }

    public static void validateMoney(String money) throws InvalidDataException {
        if (money.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        if (!Pattern.matches("\\d+", money)) {
            throw new InvalidDataException("Số tiền phải là một số dương.");
        }
    }

    public static void validateRate(String rate) throws InvalidDataException {
        if (rate.trim().isEmpty()) {
            throw new InvalidDataException("Không được để trống");
        }
        if (!Pattern.matches("\\d+", rate)) {
            throw new InvalidDataException("Lãi suất phải là một số dương.");
        }
    }
}
