package View;

import Model.Product;
import Service.ProductService;
import Service.SortPriceASC;
import Ultitils.AppUtils;
import jdk.jshell.Snippet;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    public static List<Product> products;
    private static ProductService productService = new ProductService();
    private static final Scanner input = new Scanner(System.in);

    public static void addProduct() {
        boolean flag = true;
        do {
            System.out.println("---- Thêm sản phẩm ----");
            Long productID = System.currentTimeMillis() / 1000;
            String name = inputName(Status.ADD);
            Double price = inputPrice(Status.ADD);
            double quantity = inputQuantity(Status.ADD);
            String description = inputDescription(Status.ADD);
            Product product = new Product(productID, name, price , quantity, description);
            productService.addProduct(product);
            System.out.println("Đã thêm thành công");
            showProductListShow();
            Menu.view();
        } while (!flag);
    }

    public static Long inputIDProduct(Status status) {
        Long idProduct;
        switch (status) {
            case EDIT:
            case REMOVE:
                System.out.println("Nhập ID sản phẩm cần xóa: ");
                break;
        }
        System.out.print("==>");
        boolean flag = true;
        do {
            idProduct = AppUtils.retryParseLong();
            boolean exist = productService.existId(idProduct);
            switch (status) {
                case EDIT:
                case REMOVE:
                    if (!exist) {
                        System.out.println("Không tìm thấy ID, Vui lòng nhập lại");
                        System.out.print("==> ");
                    }
                    flag = !exist;
                    break;
            }
        } while (flag);
        return idProduct;
    }

    private static double inputQuantity(Status status) {
        double quantity;
        switch (status) {
            case ADD:
                System.out.println("Nhập số lượng sản phẩm: ");
                break;
            case EDIT:
                System.out.println("Nhập số lượng sản phẩm cần sửa: ");
                break;
        }
        System.out.print("==> ");
        boolean flag = true;
        do {
            quantity = AppUtils.retryParseDouble();
            boolean exist = (quantity > 0);
            switch (status) {
                case ADD:
                case EDIT:
                case REMOVE:
                    if (!exist) {
                        System.out.println("Bạn nhập sai định dạng (Số lượng phải lớn hơn 1 )");
                        System.out.print("==> ");
                    }
                    flag = !exist;
                    break;
            }
        } while (flag);
        return quantity;
    }

    public static String inputName(Status status) {
        String name;
        switch (status) {
            case ADD:
                System.out.println("Nhập tên sản phẩm: ");
                break;
            case EDIT:
                System.out.println("Nhập tên sản phẩm muốn sửa: ");
                break;
        }
        System.out.print("==> ");
        boolean flag = true;
        do {
            name = input.nextLine().trim();
            boolean exist = (!name.isEmpty());
            switch (status) {
                case ADD:
                case EDIT:
                    if (!exist) {
                        System.out.println("Bạn nhập sai định dạng, vui lòng nhập lại: ");
                        System.out.print("==> ");
                    }
                    flag = !exist;
                    break;
            }
        } while (flag);
        return name;
    }

    public static String inputDescription(Status status) {
        String description;
        switch (status) {
            case ADD:
                System.out.println("Nhập mô tả sản phẩm: ");
                break;
            case EDIT:
                System.out.println("Nhập mô tả sản phẩm muốn sửa: ");
                break;
        }
        System.out.println("==> ");
        boolean flag = true;
        do {
            description = input.nextLine().trim();
            boolean exist = (!description.isEmpty());
            switch (status) {
                case ADD:
                case EDIT:
                    if (!exist) {
                        System.out.println("Bạn nhập sai định dạng, vui lòng nhạp lại: ");
                        System.out.print("==> ");
                    }
                    flag = !exist;
                    break;
            }
        } while (flag);
        return description;
    }

    public static double inputPrice(Status status) {
        double price;
        switch (status) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case EDIT:
                System.out.println("Nhập giá sản phẩm cần sửa: ");
                break;
        }
        System.out.print("==> ");
        boolean flag = true;
        do {
            price = AppUtils.retryParseDouble();
            boolean exist = (price >= 1000); //sản phẩm phai có giá lớn hơn 1000VND
            switch (status) {
                case ADD:
                case EDIT:
                case REMOVE:
                    if (!exist) {
                        System.out.println("Nhập sai định dạng (Số tiền phải lớn hơn 1000đ)");
                        System.out.print("==> ");
                    }
                    flag = !exist;
                    break;
            }
        } while (flag);
        return price;
    }

    public static void editProduct() {
        try {
            showProductList();
            System.out.println("Nhập ID sản phẩm: ");
            System.out.print("==> ");
            Long id = AppUtils.retryParseLong();
            if (productService.existId(id)) {
                boolean flag = true;
                System.out.println();
                System.out.println("╔═════════════════════════════════════════╗");
                System.out.println("║             ► Sửa Sản Phẩm ◄            ║");
                System.out.println("╠═════════════════════════════════════════╣");
                System.out.println("║       1.     Sửa tên sản phẩm           ║");
                System.out.println("║       2.     Sửa giá sản phẩm           ║");
                System.out.println("║       3.     Sửa số lượng sản phẩm      ║");
                System.out.println("║       4.     Sửa mô tả sản phẩm         ║");
                System.out.println("║       0.     Quay lại quản lý sản phẩm  ║");
                System.out.println("╚═════════════════════════════════════════╝");
                System.out.println("Chọn chức năng: ");
                System.out.print("=> ");
                Product newProduct = new Product();
                newProduct.setIdProduct(id);
                do {
                    String choice = input.nextLine();
                    switch (choice) {
                        case "1":
                            editName(newProduct);
                            break;
                        case "2":
                            editPrice(newProduct);
                            break;
                        case "3":
                            editQuantity(newProduct);
                            break;
                        case "4":
                            editDescription(newProduct);
                        case "0":
                            Menu.view();
                            break;
                        default:
                            System.out.println("Nhập sai vui lòng nhập lại: ");
                            System.out.print("==> ");
                            flag = false;
                    }
                } while (!flag);
            } else {
                System.out.println("Không tìm thaay ID sản phẩm");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editName(Product newProduct) {
        String name = inputName(Status.EDIT);
        newProduct.setName(name);
        productService.update(newProduct);
        System.out.println("Tên sản phẩm đã được cập nhập");
        showProductList();
    }

    public static void editPrice(Product newProduct) {
        Double price =  inputPrice(Status.EDIT);
        newProduct.setPrice(price);
        productService.update(newProduct);
        System.out.println("Giá sản phẩm đã được cập nhập");
        showProductList();

    }

    public static void editQuantity(Product newProduct) {
        double quantity = inputQuantity(Status.EDIT);
        newProduct.setQuantity(quantity);
        productService.update(newProduct);
        System.out.println("Số lượng sẩn phẩm đã được cập nhập");
        showProductList();
    }

    public static void editDescription(Product newProduct) {
        String description = inputDescription(Status.EDIT);
        newProduct.setDescription(description);
        productService.update(newProduct);
        System.out.println("Mô tả sẩn phẩm đã được cập nhập");
        showProductList();
    }

    public static void removeProduct() {
        try {
            boolean flag = true;
            showProductList();
            Long id = inputIDProduct(Status.REMOVE);
            System.out.println();
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║        ► Xóa Sản Phẩm ◄        ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║       1.     Đồng ý            ║");
            System.out.println("║       2.     Quay lại          ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("=> ");
            do {
                String choice = input.nextLine();
                switch (choice) {
                    case "1":
                        productService.remove(id);
                        System.out.println("Sản phẩm đã được xóa");
                        showProductListShow();
                        Menu.view();
                        break;
                    case "2":
                        Menu.view();
                        break;
                    default:
                        System.out.println("Nhập sai, vui lòng nhập lại");
                        System.out.print("==> ");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void showProductList() {
        System.out.println();
        System.out.println("══════════════════════════════════════ Danh Sách Sản Phẩm ═════════════════════════════════════════");
        System.out.printf("%-25s %-20s %-20s %-20s %-20s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng", "Mô tả");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
        for (Product product : productService.findAll()) {
            System.out.printf("%-25s %-20s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getDescription());
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void showProductListShow() {
        showProductList();
        int choice;
        do {
            System.out.println("Nhận 0 để quay lại quản lí sản phẩm");
            System.out.print("==>");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
    }

    private static void sortPriceASC() {
        List<Product> products = productService.findAll();
        SortPriceASC sortByPriceASC = new SortPriceASC();
        products.sort(sortByPriceASC);
        showSortList(products);
    }

    private static void showSortList(List<Product> products) {
        System.out.println();
        System.out.println("══════════════════════════════════════ Danh Sách Sản Phẩm ═════════════════════════════════════════");
        System.out.printf("%-25s %-20s %-20s %-20s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
        for (Product product : products) {
            System.out.printf("%-25s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity());
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
        int choice;
        do {
            System.out.println("Nhấn 0 để quay lại SortMenu");
            System.out.print("==> ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
    }

    public static void sortProductByPrice() {
        try {
            boolean flag = true;
            String choice;
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                  ► Sắp Xếp Theo Giá ◄                  ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║       1.     Giá sản phẩm tăng dần                     ║");
            System.out.println("║       0.     Quay lại Menu chính                       ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("=> ");
            do {
                choice = input.nextLine();
                switch (choice) {
                    case "1":
                        sortPriceASC();
                        break;
                    case "0":
                        Menu.view();
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                        System.out.print("==>");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
