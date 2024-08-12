package view;

import controller.BankController;

import java.util.Scanner;

public class BankView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankController bankController = new BankController();

        while (true) {
            System.out.println("====CHƯƠNG TRÌNH QUẢN LÝ TÀI KHOẢN====");
            System.out.println("Chọn chức năng theo số");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách các tài khoản");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            System.out.println("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bankController.addAccount(scanner);
                    break;
                case 2:
                    bankController.deleteAccount(scanner);
                    break;
                case 3:
                    bankController.showAccount();
                    break;
                case 4:
                    bankController.searchAccount(scanner);
                    break;
                case 5:
                    System.out.println("Cảm ơn đã sử dụng hệ thống!");
                    return;
                default:
                    System.out.println("Không có lựa chọn này");
                    break;
            }
        }
    }
}
