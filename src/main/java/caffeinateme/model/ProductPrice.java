package caffeinateme.model;

public class ProductPrice {
    private final String product;
    private final double price;

    public ProductPrice(String product, double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product='" + product + '\'' +
                ", price=" + price +
                '}';
    }
}