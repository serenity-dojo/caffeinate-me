package caffeinateme;

import caffeinateme.model.*;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {
    Customer cathy = Customer.named("Cathy");
    ProductCatalog productCatalog = new ProductCatalog();
    CoffeeShop coffeeShop = new CoffeeShop(productCatalog);

    Order order;

    @Given("Cathy is {float} metre(s) from the coffee shop")
    public void cathy_is_metres_from_the_coffee_shop(Float distanceInMetres) {
        cathy.setDistanceFromShop(distanceInMetres);
    }

    @When("^Cathy (?:orders|has ordered) an? (.*)")
    public void cathy_orders_a(String orderedProduct) {
        this.order = Order.of(1,orderedProduct).forCustomer(cathy);
        cathy.placesAnOrderFor(order).at(coffeeShop);
    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(coffeeShop.getPendingOrders()).contains(order);
    }

    @ParameterType(name = "order-status", value="(Normal|High|Urgent)")
    public OrderStatus orderStatus(String statusValue) {
        return OrderStatus.valueOf(statusValue);
    }

    @Then("Barry should know that the order is {order-status}")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order cathysOrder = coffeeShop.getOrderFor(cathy)
                                      .orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(cathysOrder.getStatus()).isEqualTo(expectedStatus);
    }

    @When("^(?:his|her|their) order is ready")
    public void orderIsReady() {
    }

    @Then("he/she/they should receive a message {string}")
    public void shouldReceiveAMessage(String message) {
    }

    @And("Cathy is {int} minutes away")
    public void customerIsMinutesAway(int etaInMinutes) {
        coffeeShop.setCustomerETA(cathy, etaInMinutes);
    }

    @When("Barry reviews his pending orders")
    public void barryReviewsHisPendingOrders() {

    }

    //
    // ASKING FOR A RECEIPT
    //
    @DataTableType
    public OrderItem mapRowToOrderItem(Map<String, String> entry) {
        return new OrderItem(entry.get("Product"),
                             Integer.parseInt(entry.get("Quantity")));
    }

    @Given("^Cathy has ordered:$")
    public void sarahHasOrdered(List<OrderItem> orders) {
        for(OrderItem item : orders) {
            Order order = Order.of(item.getQuantity(),item.getProduct()).forCustomer(cathy);
            cathy.placesAnOrderFor(order).at(coffeeShop);
        }
    }

    /*
    This method converts rows from tables like this to ProductPrice objects
      | Product            | Price |
      | regular cappuccino | 1.90  |
      | large cappuccino   | 2.25  |
     */
    @DataTableType
    public ProductPrice mapRowToProductPrice(Map<String, String> entry) {
        return new ProductPrice(entry.get("Product"), Double.parseDouble(entry.get("Price")));
    }

    @And("^the following prices:$")
    public void theFollowingPrices(List<ProductPrice> productPrices) throws Throwable {
        productCatalog.addProductsWithPrices(productPrices);
    }

    Receipt receipt;

    @When("^she asks for a receipt$")
    public void sheAsksForAReceipt() {
        receipt = coffeeShop.getReceiptFor(cathy);
    }


    @Then("^she should receive a receipt totalling:$")
    public void sheShouldReceiveAReceiptTotalling(List<Map<String, String>> receiptTotals) {
        Double serviceFee = Double.parseDouble(receiptTotals.get(0).get("Service Fee"));
        Double subtotal = Double.parseDouble(receiptTotals.get(0).get("Subtotal"));
        Double total = Double.parseDouble(receiptTotals.get(0).get("Total"));

        assertThat(receipt.getServiceFee()).isEqualTo(serviceFee);
        assertThat(receipt.getSubtotal()).isEqualTo(subtotal);
        assertThat(receipt.getTotal()).isEqualTo(total);
    }

    /*
    This method converts rows from tables like this to ProductPrice objects
      | Product          | Quantity | Price |
      | large cappuccino | 1        | 2.25  |
      | muffin           | 2        | 2.50  |
    */
    @DataTableType
    public ReceiptLineItem mapRowToReceiptLineItem(Map<String, String> entry) {
        return new ReceiptLineItem(entry.get("Product"),
                                   Integer.parseInt(entry.get("Quantity")),
                                   Double.parseDouble(entry.get("Price")));
    }

    @And("^the receipt should contain the line items:$")
    public void theReceiptShouldContainTheLineItems(List<ReceiptLineItem> expectedLineItems) throws Throwable {
        assertThat(receipt.getLineItems()).containsExactlyElementsOf(expectedLineItems);
    }

}
