package todomvc;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import todomvc.actions.DirectoryClient;
import todomvc.actions.User;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearchSteps {

    @Steps
    DirectoryClient directory;

    @Given("Dirk is a Directory Administrator")
    public void dirkIsADirectoryAdministrator() {
    }

    @When("he asks for the available users using the default search configuration")
    public void heAsksForTheAvailableUsersUsingTheDefaultSearchConfiguration() {
        SerenityRest.get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }

    @Then("he should be presented with the following results:")
    public void heShouldBePresentedWith(Map<String, Integer> resultSummary) {
        SoftAssertions.assertSoftly(softly -> {
            for(String field : resultSummary.keySet()) {
                int expected = resultSummary.get(field);
                int actual = SerenityRest.lastResponse().jsonPath().getInt(field);
                softly.assertThat(actual).isEqualTo(expected);
            }
        });
    }

    @DataTableType
    public User userFrom(Map<String, String> userFields) {
        return new User(Integer.parseInt(userFields.get("id")),
                        userFields.get("first_name"),
                        userFields.get("last_name"),
                        userFields.get("email"),
                        userFields.get("avatar"));
    }
    @Then("the matching users should include the following:")
    public void theMatchingUsersShouldIncludeTheFollowing(List<User> expectedUsers) {
        List<User> actualUsers = SerenityRest.lastResponse().jsonPath().getList("data", User.class);
        assertThat(actualUsers).containsAll(expectedUsers);
    }
}
