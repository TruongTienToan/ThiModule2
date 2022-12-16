package View;

import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static List<Product> productList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void view() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục) : ");
            System.out.println("1. Xem danh sách ");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            System.out.println("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ProductView.showProductList();
                    break;
                case 2:
                    ProductView.addProduct();
                    break;
                case 3:
                    ProductView.editProduct();
                    break;
                case 4:
                    ProductView.removeProduct();
                    break;
                case 5:
                    ProductView.sortProductByPrice();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }
        } while (choice != 9);
    }
}
