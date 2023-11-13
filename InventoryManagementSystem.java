import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
}


class Employee extends User {
    public Employee(String username, String password) {
        super(username, password);
    }

    // Save employee data to a text file
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt", true))) {
            writer.println(getUsername() + "," + getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public void removeFromDatabase() {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(getUsername() + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String categoryId; // New field for category
    private String categoryName; // New field for category name

    public Product(String productId, String productName, double price, int quantity, String categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("products.txt", true))) {
            writer.println(productId + "," + productName + "," + price + "," + quantity + "," + categoryId + "," + categoryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Category {
    private String categoryId;
    private String categoryName;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("categories.txt", true))) {
            writer.println(categoryId + "," + categoryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sells {
    private String productId;
    private int quantitySold;
    private double totalPrice;

    public Sells(String productId, int quantitySold, double totalPrice) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sells.txt", true))) {
            writer.println(serialize());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving sells data to file.");
        }
    }

    private String serialize() {
        return String.format("%s,%d,%.2f", productId, quantitySold, totalPrice);
    }

    public static Sells deserialize(String data) {
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(",");
        String productId = scanner.next();
        int quantitySold = scanner.nextInt();
        double totalPrice = scanner.nextDouble();
        scanner.close();
        return new Sells(productId, quantitySold, totalPrice);
    }
}

class InventoryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Product> products = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static List<Sells> sells = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Admin Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Exit");
            System.out.print("Enter you choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    employeeLogin();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            adminMenu();
        } else {
            System.out.println("Invalid credentials. Returning to the main menu.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Add Category");
            System.out.println("5. Remove Category");
            System.out.println("6. Add Sells");
            System.out.println("7. Generate Sells Report");
            System.out.println("8. Stock Level Management");
            System.out.println("9. Order New Stock");
            System.out.println("10. View Inventory Items");
            System.out.println("11. Inventory Search and Filtering");
            System.out.println("12. Export Data to CSV or Excel");
            System.out.println("13. Add Employee");
            System.out.println("14. Remove Employee");
            System.out.println("15. Logout");
            System.out.print("Enter you choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
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
                    removeCategory();
                    break;
                case 6:
                    addSells();
                    break;
                case 7:
                    generateSellsReport();
                    break;
                case 8:
                    stockLevelManagement();
                    break;
                case 9:
                    orderNewStock();
                    break;
                case 10:
                    viewInventoryItems();
                    break;
                case 11:
                    inventorySearchAndFiltering();
                    break;
                case 12:
                    exportDataToCSVOrExcel();
                    break;
                case 13:
                    addEmployee();
                    break;
                case 14:
                    removeEmployee();
                    break;
                case 15:
                    System.out.println("Logging out from admin account.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Enter product ID:");
        String productId = scanner.nextLine();
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
    
        Product newProduct = new Product(productId, productName, price, quantity, categoryId, categoryName);
        products.add(newProduct);
        newProduct.saveToFile();
        System.out.println("Product added successfully.");
    }

    private static void editProduct() {
        System.out.println("Enter product ID to edit:");
        String productId = scanner.nextLine();

        Product product = findProductById(productId);
        if (product != null) {
            System.out.println("Enter new product name:");
            String newName = scanner.nextLine();
            System.out.println("Enter new product price:");
            double newPrice = scanner.nextDouble();
            System.out.println("Enter new product quantity:");
            int newQuantity = scanner.nextInt();

            product.setProductName(newName);
            product.setPrice(newPrice);
            product.setQuantity(newQuantity);

            System.out.println("Product edited successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        System.out.println("Enter product ID to delete:");
        String productId = scanner.nextLine();

        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addCategory() {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();

        Category newCategory = new Category(categoryId, categoryName);
        categories.add(newCategory);
        newCategory.saveToFile();
        System.out.println("Category added successfully.");
    }

    private static void removeCategory() {
        System.out.println("Enter category ID to remove:");
        String categoryId = scanner.nextLine();

        Category category = findCategoryById(categoryId);
        if (category != null) {
            categories.remove(category);
            System.out.println("Category removed successfully.");
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void addSells() {
        System.out.println("Enter product ID for selling:");
        String productId = scanner.nextLine();

        Product product = findProductById(productId);
        if (product != null) {
            System.out.println("Enter quantity sold:");
            int quantitySold = scanner.nextInt();
            double totalPrice = quantitySold * product.getPrice();

            Sells newSells = new Sells(productId, quantitySold, totalPrice);
            sells.add(newSells);
            newSells.saveToFile();

            // Update product quantity
            product.setQuantity(product.getQuantity() - quantitySold);

            System.out.println("Sells recorded successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void generateSellsReport() {
        System.out.println("Generating Sells Report:");
    
        // Summarize sells data
        double totalRevenue = sells.stream().mapToDouble(Sells::getTotalPrice).sum();
        System.out.println("Total Revenue: $" + totalRevenue);
    
        // Display individual sells
        for (Sells sell : sells) {
            System.out.println("Product ID: " + sell.getProductId() +
                    " | Quantity Sold: " + sell.getQuantitySold() +
                    " | Total Price: $" + sell.getTotalPrice());
        }
    }
    
    private static void stockLevelManagement() {
        System.out.println("Stock Level Management:");
    
        // Display stock levels for each product
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductId() +
                    " | Product Name: " + product.getProductName() +
                    " | Stock Level: " + product.getQuantity());
        }
    }
    
    private static void orderNewStock() {
        System.out.println("Ordering New Stock:");
    
        // Simulate ordering new stock (increase quantity for each product)
        for (Product product : products) {
            int currentStock = product.getQuantity();
            int additionalStock = 10; // You can adjust this value as needed
            product.setQuantity(currentStock + additionalStock);
            System.out.println("Ordered " + additionalStock + " units of " +
                    product.getProductName() + " (Product ID: " + product.getProductId() + ")");
        }
    }
    
    private static void viewInventoryItems() {
        System.out.println("Inventory Items:");
    
        List<Product> products = readProductsFromFile("products.txt");
    
        if (products.isEmpty()) {
            System.out.println("No products found in the inventory.");
        } else {
            for (Product product : products) {
                System.out.println(product.getProductId() + " - " + product.getProductName() +
                        " - Quantity: " + product.getQuantity());
            }
        }
    }
    
    private static List<Product> readProductsFromFile(String filename) {
        List<Product> products = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Assuming 6 fields: productId, productName, price, quantity, categoryId, categoryName
                    Product product = new Product(parts[0], parts[1], Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]), parts[4], parts[5]);
                    products.add(product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error reading products from file.");
        }
    
        return products;
    }
    

    private static void inventorySearchAndFiltering() {
        System.out.println("Inventory Search and Filtering:");
        System.out.println("1. Search by Product ID");
        System.out.println("2. Search by Category");
        System.out.println("3. Back to Menu");
        System.out.print("Enter you choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                searchByProductId();
                break;
            case 2:
                searchByCategory();
                break;
            case 3:
                System.out.println("Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid choice. Returning to the main menu.");
        }
    }

    private static void searchByProductId() {
        System.out.println("Enter product ID to search:");
        String productId = scanner.nextLine();

        Product product = findProductById(productId);

        if (product != null) {
            System.out.println("Product found:");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void searchByCategory() {
        System.out.println("Enter category ID to search:");
        String categoryId = scanner.nextLine();

        // Find products in the specified category
        List<Product> productsInCategory = products.stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());

        if (!productsInCategory.isEmpty()) {
            System.out.println("Products in Category:");
            for (Product product : productsInCategory) {
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("No products found in the specified category.");
        }
    }


    
private static void exportDataToCSVOrExcel() {
    System.out.println("Export Data:");
    System.out.println("1. Export Inventory Data");
    System.out.println("2. Export Sells Data");
    System.out.println("3. Back to Menu");
    System.out.print("Enter you choice: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    switch (choice) {
        case 1:
            exportInventoryData();
            break;
        case 2:
            exportSellsData();
            break;
        case 3:
            System.out.println("Returning to the main menu.");
            break;
        default:
            System.out.println("Invalid choice. Returning to the main menu.");
    }
}

    private static void exportInventoryData() {
        exportData("inventory.csv", products);
        System.out.println("Inventory data exported to 'inventory.csv'");
    }

    private static void exportSellsData() {
        exportData("sells.csv", sells);
        System.out.println("Sells data exported to 'sells.csv'");
    }

    private static <T> void exportData(String filename, List<T> dataList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            if (!dataList.isEmpty()) {
                // Write header
                writer.println(dataList.get(0).toString());

                // Write data
                for (T data : dataList) {
                    writer.println(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error exporting data to file.");
        }
    }

    private static void addEmployee() {
        System.out.println("Enter employee username:");
        String username = scanner.nextLine();
        System.out.println("Enter employee password:");
        String password = scanner.nextLine();
    
        Employee newEmployee = new Employee(username, password);
        employees.add(newEmployee);
        newEmployee.saveToFile(); // Save employee data to file
        System.out.println("Employee added successfully.");
    }
    
private static List<Employee> readEmployeesFromFile(String filename) {
    List<Employee> employees = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) { // Assuming 2 fields: username, password
                Employee employee = new Employee(parts[0], parts[1]);
                employees.add(employee);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error reading employees from file.");
    }

    return employees;
}


    private static Employee findEmployeeByUsername(String username) {
        List<Employee> employees = readEmployeesFromFile("employees.txt");

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }

        return null;
    }

    private static void removeEmployee() {
        System.out.println("Enter employee username to remove:");
        String username = scanner.nextLine();
    
        Employee employee = findEmployeeByUsername(username);
        if (employee != null) {
            employees.remove(employee);
            employee.removeFromDatabase(); // Remove employee data from file
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void employeeLogin() {
        System.out.println("Enter employee username: ");
        String username = scanner.nextLine();
        System.out.println("Enter employee password: ");
        String password = scanner.nextLine();
    
        Employee employee = findEmployeeByUsername(username);
    
        if (employee != null && verifyEmployeePassword(employee, password)) {
            System.out.println("Employee login successful.");
            employeeMenu();  // Redirect to the employee menu
        } else {
            System.out.println("Invalid credentials. Returning to the main menu.");
        }
    }
    
    
    private static boolean verifyEmployeePassword(Employee employee, String enteredPassword) {
        // Implement a more secure password verification mechanism here
        // For simplicity, we compare passwords as plain text in this example
        return employee.getPassword().equals(enteredPassword);
    }
    
  
    
    private static void employeeMenu() {
        while (true) {
            System.out.println("Employee Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Inventory Items");
            System.out.println("5. Search and Filter Inventory");
            System.out.println("6. Place Orders");
            System.out.println("7. Add Sells");
            System.out.println("8. Logout");
            System.out.print("Enter you choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
    
            switch (choice) {
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
                    viewInventoryItems();
                    break;
                case 5:
                    inventorySearchAndFiltering();
                    break;
                case 6:
                    placeOrders();
                    break;
                case 7:
                    addSells();
                    break;
                case 8:
                    System.out.println("Logging out from employee account.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
    
    private static void placeOrders() {
        System.out.println("Place Orders:");
    
        while (true) {
            System.out.println("Enter the product ID to order (or type 'done' to finish):");
            String productId = scanner.nextLine();
    
            if (productId.equalsIgnoreCase("done")) {
                break;
            }
    
            Product product = findProductById(productId);
    
            if (product != null) {
                System.out.println("Enter the quantity to order:");
                int quantityToOrder = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
    
                if (quantityToOrder > 0 && quantityToOrder <= product.getQuantity()) {
                    // Update the inventory
                    product.setQuantity(product.getQuantity() - quantityToOrder);
    
                    // Add to the sells data
                    double totalPrice = quantityToOrder * product.getPrice();
                    Sells sell = new Sells(product.getProductId(), quantityToOrder, totalPrice);
                    sells.add(sell);
                    sell.saveToFile();
    
                    System.out.println("Order placed successfully.");
                } else {
                    System.out.println("Invalid quantity or insufficient stock. Please try again.");
                }
            } else {
                System.out.println("Product not found. Please enter a valid product ID.");
            }
        }
    }
    
    private static Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private static Category findCategoryById(String categoryId) {
        for (Category category : categories) {
            if (category.getCategoryId().equals(categoryId)) {
                return category;
            }
        }
        return null;
    }

    
}
