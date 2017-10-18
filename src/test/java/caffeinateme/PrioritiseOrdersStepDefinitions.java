package caffeinateme;

import caffeinateme.steps.Customer;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class PrioritiseOrdersStepDefinitions {

    @Steps(shared=true)
    Customer sarah;

    @Given("Sarah is (\\d+) minutes away from the shop")
    public void notifyETA(int minutesAway) {
        sarah.updatesHerETATo(minutesAway);
    }
}
