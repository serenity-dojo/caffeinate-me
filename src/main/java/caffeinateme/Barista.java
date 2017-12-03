package caffeinateme;

import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Barista {

    public List<String> getPendingOrders() {
        List<String> results = new ArrayList<>();
        results.add("large cappuccino");
        return results;
    }

    public List<String> getUrgentOrders() {
        List<String> results = new ArrayList<>();
        results.add("large cappuccino");
        return results;
    }

    @Step
    public void shouldHaveAPendingOrderFor(String someOrder) {
        assertThat(getPendingOrders()).contains(someOrder);
    }
}
