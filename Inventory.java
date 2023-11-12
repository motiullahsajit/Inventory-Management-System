import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


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