package caffeinateme;

import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class UserRegistrationStepDefinitions {

    @Steps
    UserRegistrationClient userRegistrations;

    @Steps(shared = true)
    Customer customer;

    @Given("^(.*) (?:has|is) a (?:Caffeinate-Me account|registered customer)")
    public void userHasACaffeinateMeAccount(String userName) {
        userRegistrations.registerUser(customer);
        customer.isCalled(userName);
    }
}
