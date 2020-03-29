package caffeinateme;

import caffeinateme.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {
    Customer cathy = Customer.named("Cathy");
    CoffeeShop coffeeShop = new CoffeeShop();
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
        System.out.println("STATUS = " + expectedStatus);
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
}
