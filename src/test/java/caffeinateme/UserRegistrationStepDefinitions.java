package caffeinateme;

import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class UserRegistrationStepDefinitions {

    @Steps
    UserRegistrationClient userRegistrations;

    @Steps(shared = true)
    Customer customer;

    @Given("(.*) has a Caffeinate-Me account")
    public void userHasACaffeinateMeAccount(String userName) {
        userRegistrations.registerUser(customer);
        customer.isCalled(userName);
    }
}
