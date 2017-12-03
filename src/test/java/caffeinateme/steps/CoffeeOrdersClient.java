package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import caffeinateme.Receipt;
import caffeinateme.ReceiptLineItem;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoffeeOrdersClient {

    @Steps(shared = true)
    ProductCatalog productCatalog;

    List<Order> orders = new ArrayList<>();

    @Step("Place order for customer {0} for {1} x {2}")
    public OrderReceipt placeOrder(long customerId, int quantity, String product) {
        Order order = new Order(customerId, quantity, product);
        orders.add(order);
        return order.getReceipt();
    }

    @Step
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    @Step("Notify updated ETA for customer {0} who is {1} minutes away from the shop")
    public void updateCustomerEta(long customerId, int minutesAway) {
        orders.stream().filter(order -> order.getCustomerId() == customerId)
                .forEach(order -> order.updateETATo(minutesAway));
    }

    public Receipt getReceiptFor(long customerId) {
        double subTotal = orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .mapToDouble(this::subtotalFor)
                .sum();

        List<ReceiptLineItem> lineItems = orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .map(order -> new ReceiptLineItem(order.getProduct(), order.getQuantity(), subtotalFor(order)))
                .collect(Collectors.toList());


        double serviceFee = subTotal * 5 / 100;
        double total = subTotal + serviceFee;
        return new Receipt(roundedTo2DecimalPlaces(subTotal),
                roundedTo2DecimalPlaces(serviceFee),
                roundedTo2DecimalPlaces(total),
                lineItems);
    }

    private double roundedTo2DecimalPlaces(double value) {
        return new BigDecimal(Double.toString(value)).setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private double subtotalFor(Order order) {
        return productCatalog.priceOf(order.getProduct()) * order.getQuantity();
    }
}
