import java.io.*;
import java.util.*;

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