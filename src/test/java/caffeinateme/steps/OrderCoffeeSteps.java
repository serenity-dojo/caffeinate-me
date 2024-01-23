package caffeinateme.steps;

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
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;

    @ParameterType("\"[^\"]*\"")
    public Order order(String orderedProduct) {
        return Order.of(1, orderedProduct).forCustomer(cathy);
    }

    @DataTableType
    public OrderItem orderItem(Map<String, String> row) {
        return new OrderItem(row.get("Product"), Integer.parseInt(row.get("Quantity")));
    }

    @Given("{} is a CaffeinateMe customer")
    public void a_caffeinate_me_customer_named(String customerName) {
        cathy = coffeeShop.registerNewCustomer(customerName);
    }
    @Given("Cathy is {int} metres from the coffee shop")
    public void cathyIsMetresFromTheCoffeeShop(Integer arg0) {
        cathy.setDistanceFromShop(arg0);
    }

    @When("Cathy orders a {order}")
    public void cathy_orders_a(Order order) {
        this.order = order;
        cathy.placesAnOrderFor(order).at(coffeeShop);
    }

    @When("Cathy orders a {order} with a comment {string}")
    public void cathy_orders_with_comment(Order order, String comment) {
        this.order = order.withComment(comment);
        cathy.placesAnOrderFor(this.order).at(coffeeShop);
    }

    @Then("Barry should receive the order")
    public void barryShouldReceiveTheOrder() {
        assertThat(coffeeShop.getPendingOrders()).contains(order);
    }

    @Then("the order should have the comment {string}")
    public void order_should_have_comment(String comment) {
        Order order = coffeeShop.getOrderFor(cathy).get();
        assertThat(order.getComment()).isEqualTo(comment);
    }
    @And("^Barry should know that the order is (.*)")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order cathysOrder = coffeeShop.getOrderFor(cathy)
                .orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(cathysOrder.getStatus()).isEqualTo(expectedStatus);
    }

    @When("Cathy places an order for the following items:")
    public void cathyPlacesAnOrderForTheFollowingItems(List<OrderItem> theList) {

        this.order = new Order(theList, cathy);
        coffeeShop.placeOrder(this.order);
    }

    @And("the order should contain {int} line items")
    public void theOrderShouldContainLineItems(int arg0) {
    }

    @And("the order should contain the following products:")
    public void theOrderShouldContain(List<String> expectedProducts) {
        Order order = coffeeShop.getOrderFor(cathy).get();
        List<String> productItems = order.getItems().stream().map(OrderItem::getProduct).toList();
        assertThat(productItems).hasSameElementsAs(expectedProducts);
    }


}
