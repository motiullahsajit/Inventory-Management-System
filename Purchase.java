import java.util.*;

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
