package caffeinateme;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;


public class OrderCoffeeSteps_NB {
        Customer_NB customer = new Customer_NB("cathy");
        Barrista_NB barry = new Barrista_NB();
        Order_NB order;


        @Given("^Cathy is (\\d+) meters from the coffee shop$")
        public void cathy_is_n_meters_from_the_coffee_shop(int distanceInMeters) throws Throwable {
            customer.setDistanceFromShop(distanceInMeters);
            assertThat(customer.getDistanceFromShop()).isEqualTo(distanceInMeters);
        }

        @When("^Cathy orders a (.*)$")
        public void cathy_orders_a(String orderedProduct) throws Throwable {
            order= customer.placeOrderFor(orderedProduct);
            assertThat(order.getOrderedCustomerName()).isEqualTo("cathy");
        }

        @Then("^Barry should receive the order$")
        public void barry_should_receive_the_order() throws Throwable {
            order= customer.placeOrderFor("Large Cappuccino");
            barry.addToReceivedOrders(order);
            assertThat(barry.getPendingOrders(),hasItem(order));
        }

        @Then("^Barry should know that the coffee is (.*)$")
        public void barry_should_know_that_the_coffee_is_Urgent(String orderStatus) throws Throwable {
            order= customer.placeOrderFor("Large Cappuccino");
            assertThat(barry.getCathysOrderStatus()).isEqualTo(orderStatus);
        }

    }


