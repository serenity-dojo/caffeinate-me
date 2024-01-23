package caffeinateme.steps;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Customer;
import caffeinateme.model.Order;
import caffeinateme.model.OrderStatus;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {

    Customer cathy = Customer.named("Cathy");
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;

    @ParameterType("\"[^\"]*\"")
    public Order order(String orderedProduct) {
        return Order.of(1, orderedProduct).forCustomer(cathy);
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
}
