package caffeinateme.steps;

import net.thucydides.core.annotations.Step;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomerRegistrationClient {

    AtomicInteger customerIdCounter = new AtomicInteger(1);

    @Step("Register a user called {0}")
    public void registerCustomer(Customer newCustomer) {
        newCustomer.hasACustomerIdOf(customerIdCounter.getAndIncrement());
    }
}
