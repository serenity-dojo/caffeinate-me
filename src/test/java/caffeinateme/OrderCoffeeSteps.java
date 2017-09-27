package caffeinateme;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class OrderCoffeeSteps {

    Customer cathy = new Customer();
    Barrista barry = new Barrista();

    String cathysOrder;

    @Given("(.*) is (\\d+) meters? from the coffee shop")
    public void cathy_is_n_meters_from_the_coffee_shop(String name, int distanceInMeters) throws Throwable {
        System.out.println("Meters = " + distanceInMeters);
        cathy.setDistanceFromShop(distanceInMeters);
    }

    @When("Cathy (?:orders|has ordered) a (.*)$")
    public void cathy_orders_a(String order) throws Throwable {
        cathysOrder = order;
        cathy.placesOrderFor(cathysOrder);
    }

    @Then("Barry should receive the order$")
    public void barry_should_receive_the_order() throws Throwable {
        assertThat(barry.getPendingOrders(), hasItem(cathysOrder));
    }

    @Then("^Barry should know that the coffee is Urgent$")
    public void barry_should_know_that_the_coffee_is_Urgent() throws Throwable {
        assertThat(barry.getUrgentOrders(), hasItem(cathysOrder));
    }

}
