package util;

import model.BankAccount;
import model.PayAccount;
import model.SavingsAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<BankAccount> readListBankAccount(String filePath) {
        List<BankAccount> listBankAccount = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8) {
                    BankAccount bankAccount = new SavingsAccount(
                            values[1], values[2], values[3], Double.parseDouble(values[4]), values[5],
                            Double.parseDouble(values[6]), values[7]
                    );
                    bankAccount.setIdAccount(Integer.parseInt(values[0]));
                    listBankAccount.add(bankAccount);
                } else if (values.length == 6) {
                    BankAccount bankAccount = new PayAccount(
                            values[1], values[2], values[3], values[4], values[5]
                    );
                    bankAccount.setIdAccount(Integer.parseInt(values[0]));
                    listBankAccount.add(bankAccount);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file " + e.getMessage());
        }
        return listBankAccount;
    }

    public static void writeListBankAccount(String filePath, List<BankAccount> listBankAccount) {
        try (BufferedWriter bufferedWriter =  new BufferedWriter(new FileWriter(filePath))){
            bufferedWriter.write("Id tài khoản,Mã tài khoản,Tên tài khoản,Ngày tạo,(Số tiền gửi, Ngày gửi, Lãi suất, Kỳ hạn, Số thẻ, Số tiền)");
            bufferedWriter.newLine();
            for (BankAccount bankAccount : listBankAccount) {
                bufferedWriter.write(bankAccount.toCSVString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file " + e.getMessage());
        }
    }
}
