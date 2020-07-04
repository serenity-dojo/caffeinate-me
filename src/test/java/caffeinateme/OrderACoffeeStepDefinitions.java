package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.ProductCatalog;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @Steps(shared = true)
    private ProductCatalog productCatalog;

    @When("^(?:.*) (?:orders|has ordered) an? (.*)$")
    public void sheOrdersA(String order) throws Throwable {
        orderReceipt = customer.placesAnOrderFor(1, order);

        Serenity.setSessionVariable("orderReceipt").to(orderReceipt);
    }

    @Then("^Barry should receive the order$")
    public void barryShouldReceiveTheOrder() throws Throwable {
        assertThat(barry.pendingOrders()).contains(Order.matching(orderReceipt));
    }

    @DataTableType
    public OrderItem convertToOrderItem(Map<String, String> orderItem) {
        return new OrderItem(orderItem.get("Product"), Integer.parseInt(orderItem.get("Quantity")));
    }

    @Given("^Sarah has ordered:$")
    public void sarahHasOrdered(List<OrderItem> orders) throws Throwable {
        orders.forEach(
                order -> {
                    customer.placesAnOrderFor(order.getQuantity(),
                            order.getProduct());
                }
        );
    }

    Receipt receipt;

    @When("^she asks for a receipt$")
    public void sheAsksForAReceipt() throws Throwable {
        receipt = customer.requestsAReceipt();
    }

    @DataTableType
    public Receipt convertToReceipt(Map<String, String> receipt) {
        double subtotal = Double.parseDouble(receipt.get("Subtotal"));
        double serviceFee = Double.parseDouble(receipt.get("Service Fee"));
        double total = Double.parseDouble(receipt.get("Total"));
        return new Receipt(subtotal, serviceFee,total, null);
    }

    @Then("^she should receive a receipt totalling:$")
    public void sheShouldReceiveAReceiptTotalling(List<Receipt> expectedReceipts) throws Throwable {
        Receipt expectedReceipt = expectedReceipts.get(0);
        assertThat(receipt).isEqualToIgnoringNullFields(expectedReceipt);
    }

    @DataTableType
    public ProductPrice convertToProductPrice(Map<String, String> productPrice) {
        return new ProductPrice(productPrice.get("Product"), Double.parseDouble(productPrice.get("Price")));
    }

    @And("^the following prices:$")
    public void theFollowingPrices(List<ProductPrice> productPrices) throws Throwable {
        productCatalog.addProductsWithPrices(productPrices);
    }

    @DataTableType
    public ReceiptLineItem convertToReceiptLineItem(Map<String, String> receiptLineItem) {
        return new ReceiptLineItem(receiptLineItem.get("Product"),
                Integer.parseInt(receiptLineItem.get("Quantity")),
                Double.parseDouble(receiptLineItem.get("Price")));
    }

    @And("^the receipt should contain the line items:$")
    public void theReceiptShouldContainTheLineItems(List<ReceiptLineItem> expectedLineItems) throws Throwable {
        assertThat(receipt.getLineItems()).containsExactlyElementsOf(expectedLineItems);
    }
}
