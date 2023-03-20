package caffeinateme.steps;

import caffeinateme.model.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.var;
import org.hamcrest.Matchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderCoffeeSteps {

    Customer cathy = Customer.named("Cathy");
    Customer customer;
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;


    @Given("{} is a CaffeinateMe customer")
    public void a_caffeinate_me_customer_named(String customerName){
        customer = coffeeShop.registerNewCustomer(customerName);
    }

    @And("Cathy is {int} metres from the coffee shop")
    public void cathy_is_metres_from_the_coffee_shop(Integer distanceInMetres) {
        cathy.setDistanceFromShop(distanceInMetres);
    }

    @ParameterType("\"[^\"]*\"")
    public Order order(String orderedProduct) {
        return new Order.OrderBuilder()
                .setQuantity(1)
                .setProduct(orderedProduct)
                .setCustomer(customer).build();
    }


    @When("Cathy orders a {order}")
    public void cathy_orders_a(Order order) {
        this.order = order;
        cathy.placesAnOrderFor(order).at(coffeeShop);
    }
    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(coffeeShop.getPendingOrders(), hasItem(order));
    }

    @Then("^Barry should know that the order is (.*)")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order cathysOrder = coffeeShop.getOrderFor(cathy)
                .orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(cathysOrder.getStatus(), equalTo(expectedStatus));
    }



    @When("Cathy orders a {order} with a comment {string}")
    public void cathy_orders_with_comment(Order order, String comment) {
        this.order = new Order.OrderBuilder(order).setComment(comment).build();
        customer.placesAnOrderFor(order).at(coffeeShop);
    }

    @Then("the order should have the comment {string}")
    public void order_should_have_comment(String comment) {
        Order order = coffeeShop.getOrderFor(customer).get();
        assertThat(order.getComment(),equalTo(comment));
}

    @When("Cathy places an order for the following items:")
    public void cathyPlacesAnOrderForTheFollowingItems(DataTable orderItemValues) {
        var items = orderItemValues.asMaps();
        List<OrderItem> orderItems
                = items.stream()
                      .map(row -> new OrderItem(row.get("Product"),
                              Integer.parseInt(row.get("Quantity"))))
                .collect(Collectors.toList());
        Order order = new Order.OrderBuilder().setOrderItems(orderItems).build();
        order.withCustomer(customer);
        this.order = order;
    }

    @And("the order should contain {int} line items")
    public void theOrderShouldContainLineItems(int expectedNumberOfLineItems) {
        Order order = coffeeShop.getOrderFor(customer).get();
        assertThat(order.getItems(),hasSize(expectedNumberOfLineItems));
    }

}
