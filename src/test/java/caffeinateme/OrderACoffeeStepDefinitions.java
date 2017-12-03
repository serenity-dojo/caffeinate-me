package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.ProductCatalog;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @Steps(shared=true)
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

    @Given("^Sarah has ordered:$")
    public void sarahHasOrdered(List<OrderItem> orders) throws Throwable {
        System.out.println("Orders: " + orders);
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

    @Then("^she should receive a receipt totalling:$")
    public void sheShouldReceiveAReceiptTotalling(List<Receipt> expectedReceipts) throws Throwable {
        Receipt expectedReceipt = expectedReceipts.get(0);

        assertThat(receipt).isEqualToIgnoringNullFields(expectedReceipt);
    }

    @And("^the following prices:$")
    public void theFollowingPrices(List<ProductPrice> productPrices) throws Throwable {
        productCatalog.addProductsWithPrices(productPrices);
    }
}
