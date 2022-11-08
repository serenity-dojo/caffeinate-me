package caffeinateme.steps;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Customer;
import caffeinateme.model.Order;
import caffeinateme.model.OrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {
    Customer customer = Customer.named("Cathy");
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;

    @Given("^(.*) is a CaffeinateMe customer")
    public void a_caffeinate_me_customer_named(String customerName){
        customer = coffeeShop.registerNewCustomer(customerName);
    }

    @Given("Cathy is {double} metres from the coffee shop")
    public void cathy_is_metres_from_the_coffee_shop(Integer distanceInMetres) {
        customer.setDistanceFromShop(distanceInMetres);
    }

    @When("Cathy orders a {string}")
    public void cathy_orders_a(String orderedProduct) {
        this.order = Order.of(1, orderedProduct).forCustomer(customer);
        customer.placesAnOrderFor(order).at(coffeeShop);
    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(coffeeShop.getPendingOrders()).contains(order);
    }

    @Then("Barry should know that the order is {}")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order cathysOrder = coffeeShop.getOrderFor(customer)
                .orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(cathysOrder.getStatus()).isEqualTo(expectedStatus);
    }
}
