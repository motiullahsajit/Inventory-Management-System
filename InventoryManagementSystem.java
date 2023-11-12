import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}

class Purchase {
    private Product product;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;

    public Purchase(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchaseDate = new Date();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}

class Inventory {
    private List<Product> products;
    private Set<String> categories;
    private List<Purchase> purchases;

    public Inventory() {
        this.products = new ArrayList<>();
        this.categories = new HashSet<>();
        this.purchases = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        categories.add(product.getCategory());
    }

    public void editProduct(String productName, double newPrice, int newQuantity, String newCategory) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setPrice(newPrice);
                product.setQuantity(newQuantity);
                categories.remove(product.getCategory());
                product.setCategory(newCategory);
                categories.add(newCategory);
                break;
            }
        }
    }

    public void deleteProduct(String productName) {
        products.removeIf(product -> product.getName().equals(productName));
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public void generateReports() {
        System.out.println("Generating Reports...");
        System.out.println("Sales Report:");
        for (Product product : products) {
            System.out.println(product.getName() + ": " + product.getQuantity() + " units sold");
        }
    }

    public void inventorySearchAndFilter(String category) {
        System.out.println("Inventory Search and Filtering for Category: " + category);

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                System.out.println(product);
            }
        }
    }

    public void exportDataToCSVOrExcel(List<Product> productList, String fileName) {
        System.out.println("Exporting Data to " + fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Name,Price,Quantity,Category\n");

            for (Product product : productList) {
                writer.append(product.getName())
                        .append(",")
                        .append(String.valueOf(product.getPrice()))
                        .append(",")
                        .append(String.valueOf(product.getQuantity()))
                        .append(",")
                        .append(product.getCategory())
                        .append("\n");
            }

            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    public void exportPurchaseDataToCSVOrExcel(List<Purchase> purchaseList, String fileName) {
        System.out.println("Exporting Purchase Data to " + fileName);

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("Product,Quantity,TotalPrice,PurchaseDate\n");

            for (Purchase purchase : purchaseList) {
                writer.append(purchase.getProduct().getName())
                        .append(",")
                        .append(String.valueOf(purchase.getQuantity()))
                        .append(",")
                        .append(String.valueOf(purchase.getTotalPrice()))
                        .append(",")
                        .append(purchase.getPurchaseDate().toString())
                        .append("\n");
            }

            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    public void stockLevelManagement() {
        System.out.println("Stock Level Management Menu");

        System.out.println("Products with Low Stock:");
        for (Product product : products) {
            if (product.getQuantity() < 5) {
                System.out.println(product.getName() + ": Low stock (" + product.getQuantity() + " units)");
            }
        }
    }

    public void orderAndPurchaseManagement() {
        System.out.println("Order and Purchase Management Menu");

        for (Product product : products) {
            if (product.getQuantity() < 5) {
                int orderQuantity = 10;  
                double totalPrice = orderQuantity * product.getPrice();

                Purchase purchase = new Purchase(product, orderQuantity, totalPrice);
                purchases.add(purchase);  
                product.setQuantity(product.getQuantity() + orderQuantity);

                System.out.println("Purchase Order placed: " + purchase);
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}

class EmployeeManagement {
    private Map<String, String> employees;

    public EmployeeManagement() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(String employeeName, String username, String password) {
        employees.put(employeeName, username + ":" + password);
    }

    public void removeEmployee(String employeeName) {
        employees.remove(employeeName);
    }

    public Map<String, String> getEmployees() {
        return employees;
    }

    public String getEmployeeCredentials(String employeeName) {
        return employees.get(employeeName);
    }
    
}



public class InventoryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory();
    private static EmployeeManagement employeeManagement = new EmployeeManagement();
    private static Map<String, String> userCredentials = new HashMap<>(); 

    public static void main(String[] args) {
        initializeData();
        int mainChoice;

        do {
            clearScreen(); 
            System.out.println("Main Menu");
            System.out.println("1. Admin Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    adminLoginMenu();
                    break;
                case 2:
                    employeeLoginMenu();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (mainChoice != 3);
    }

    private static void adminLoginMenu() {
        System.out.print("Enter Admin username: ");
        String username = scanner.next();
        System.out.print("Enter Admin password: ");
        String password = scanner.next();

        if (authenticateUser(username, password, "admin")) {
            adminMenu();
        } else {
            System.out.println("Authentication failed. Returning to the main menu.");
        }
    }

    private static void employeeLoginMenu() {
        System.out.print("Enter Employee username: ");
        String username = scanner.next();
        System.out.print("Enter Employee password: ");
        String password = scanner.next();

        if (authenticateUser(username, password, "employee")) {
            employeeMenu();
        } else {
            System.out.println("Authentication failed. Returning to the main menu.");
        }
    }
    
    private static boolean authenticateUser(String username, String password, String userType) {
        if (userType.equals("admin") && username.equals("admin") && password.equals("admin12345")) {
            return true;
        } else if (userType.equals("employee")) {
            for (Map.Entry<String, String> entry : employeeManagement.getEmployees().entrySet()) {
                String[] credentials = entry.getValue().split(":");
                if (entry.getKey().equals(username) && credentials.length == 2 && credentials[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void adminMenu() {
        int adminChoice;

        do {
            clearScreen(); 
            System.out.println("Admin Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Add Category");
            System.out.println("5. Generate Reports");
            System.out.println("6. Stock Level Management");
            System.out.println("7. Order and Purchase Management");
            System.out.println("8. Inventory Search and Filtering");
            System.out.println("9. Export Data to CSV or Excel");
            System.out.println("10. Export Purchase Data to CSV or Excel");  // New option
            System.out.println("11. Add Employee");
            System.out.println("12. Remove Employee");
            System.out.println("13. Logout");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    addCategory();
                    break;
                case 5:
                    inventory.generateReports();
                    break;
                case 6:
                    stockLevelManagement();
                    break;
                case 7:
                    orderAndPurchaseManagement();
                    break;
                case 8:
                    inventorySearchAndFilter();
                    break;
                case 9:
                    exportData();
                    break;
                case 10:
                    exportPurchaseData();  
                    break;
                case 11:
                    addEmployee();
                    break;
                case 12:
                    removeEmployee();
                    break;
                case 13:
                    System.out.println("Logging out of Admin account.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (adminChoice != 13);
    }

    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error while clearing the screen: " + e.getMessage());
        }
    }

    private static void addProduct() {
        System.out.println("Enter product details:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Category: ");
        String category = scanner.next();

        inventory.addProduct(new Product(name, price, quantity, category));
        System.out.println("Product added successfully.");
    }

    private static void editProduct() {
        System.out.print("Enter the name of the product to edit: ");
        String productName = scanner.next();

        Product product = findProductByName(productName);
        if (product != null) {
            System.out.println("Enter new details for the product:");
            System.out.print("New Price: ");
            double newPrice = scanner.nextDouble();
            System.out.print("New Quantity: ");
            int newQuantity = scanner.nextInt();
            System.out.print("New Category: ");
            String newCategory = scanner.next();

            inventory.editProduct(productName, newPrice, newQuantity, newCategory);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter the name of the product to delete: ");
        String productName = scanner.next();

        Product product = findProductByName(productName);
        if (product != null) {
            inventory.deleteProduct(productName);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static Product findProductByName(String productName) {
        for (Product product : inventory.getProducts()) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    private static void addCategory() {
        System.out.print("Enter the name of the new category: ");
        String newCategory = scanner.next();

        inventory.addCategory(newCategory);
        System.out.println("Category added successfully.");
    }


    private static void stockLevelManagement() {
        inventory.stockLevelManagement();
    }

    private static void orderAndPurchaseManagement() {
        System.out.println("Order and Purchase Management Menu");

        for (Product product : inventory.getProducts()) {
            if (product.getQuantity() < 5) {
                int orderQuantity = 10; 
                double totalPrice = orderQuantity * product.getPrice();

                Purchase purchase = new Purchase(product, orderQuantity, totalPrice);
                inventory.getPurchases().add(purchase);  
                product.setQuantity(product.getQuantity() + orderQuantity);

                System.out.println("Purchase Order placed: " + purchase);
            }
        }
    }

    private static void inventorySearchAndFilter() {
        System.out.print("Enter the category for inventory search: ");
        String category = scanner.next();

        inventory.inventorySearchAndFilter(category);
    }

    private static void exportData() {
        clearScreen(); 
        System.out.println("Export Data Menu");
        System.out.println("1. Export Data Sells to CSV or Excel");
        System.out.println("2. Export Data Product to CSV or Excel");
        System.out.println("3. Export Data Purchase to CSV or Excel");
        System.out.print("Enter your choice: ");
        int exportChoice = scanner.nextInt();

        List<Product> productList = inventory.getProducts();
        List<Purchase> purchaseList = inventory.getPurchases();

        switch (exportChoice) {
            case 1:
                inventory.exportDataToCSVOrExcel(productList, "SellsData.csv");
                break;
            case 2:
                inventory.exportDataToCSVOrExcel(productList, "ProductData.csv");
                break;
            case 3:
                inventory.exportPurchaseDataToCSVOrExcel(purchaseList, "PurchaseData.csv");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private static void exportPurchaseData() {
        System.out.println("Export Purchase Data Menu");
        System.out.print("Enter the filename for the export: ");
        String fileName = scanner.next();

        List<Purchase> purchaseList = inventory.getPurchases();
        inventory.exportPurchaseDataToCSVOrExcel(purchaseList, fileName);
    }

    private static void addEmployee() {
        System.out.print("Enter the name of the new employee: ");
        String newEmployee = scanner.next();
        System.out.print("Enter the username for the new employee: ");
        String username = scanner.next();
        System.out.print("Enter the password for the new employee: ");
        String password = scanner.next();

        employeeManagement.addEmployee(newEmployee, username, password);
        System.out.println("Employee added successfully.");
    }

    private static void removeEmployee() {
        System.out.print("Enter the name of the employee to remove: ");
        String employeeToRemove = scanner.next();
    
        if (employeeManagement.getEmployees().containsKey(employeeToRemove)) {
            employeeManagement.removeEmployee(employeeToRemove);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
    

    private static void employeeMenu() {
        int employeeChoice;

        do {
            clearScreen(); 
            System.out.println("Employee Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Inventory Items");
            System.out.println("5. Search and Filter Inventory");
            System.out.println("6. Place Orders");
            System.out.println("7. View Purchases");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            employeeChoice = scanner.nextInt();

            switch (employeeChoice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    inventorySearchAndFilter();
                    break;
                case 6:
                    placeOrders();
                    break;
                case 7:
                    viewPurchases();
                    break;
                case 8:
                    System.out.println("Logging out of Employee account.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (employeeChoice != 8);
    }

    private static void viewInventory() {
        List<Product> products = inventory.getProducts();
        System.out.println("Current Inventory:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void viewPurchases() {
        List<Purchase> purchases = inventory.getPurchases();
        System.out.println("Purchase History:");
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static void placeOrders() {
        System.out.print("Enter the name of the product to order: ");
        String productName = scanner.next();

        Product product = findProductByName(productName);
        if (product != null) {
            System.out.print("Enter the quantity to order: ");
            int orderQuantity = scanner.nextInt();
            double totalPrice = orderQuantity * product.getPrice();

            Purchase purchase = new Purchase(product, orderQuantity, totalPrice);
            inventory.getPurchases().add(purchase); 
            product.setQuantity(product.getQuantity() + orderQuantity);

            System.out.println("Order placed successfully: " + purchase);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void initializeData() {
        Product product1 = new Product("Laptop", 999.99, 20, "Electronics");
        Product product2 = new Product("Smartphone", 499.99, 30, "Electronics");
        Product product3 = new Product("Chair", 49.99, 50, "Furniture");
        Product product4 = new Product("Desk", 129.99, 25, "Furniture");

        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
        inventory.addProduct(product4);

        inventory.addCategory("Electronics");
        inventory.addCategory("Furniture");

        employeeManagement.addEmployee("JohnDoe","jon","Jon1234");

        userCredentials.put("admin", "admin12345");
        userCredentials.put("JohnDoe", "employee123");
        userCredentials.put("JaneSmith", "employee123");
    }
}

