import java.io.*;

class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String categoryId; 
    private String categoryName; 

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