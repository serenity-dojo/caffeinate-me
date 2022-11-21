package todomvc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import todomvc.actions.AddItemActions;
import todomvc.actions.DisplayedItemList;
import todomvc.actions.NavigateActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddingTodoItemSteps {

    @Steps
    NavigateActions navigate;

    @Steps
    AddItemActions addItem;

    DisplayedItemList displayedItemList;

    @Given("Todd has an empty todo list")
    public void toddHasAnEmptyTodoList() {
        navigate.toTheHomePage();
    }

    @When("he adds {string}")
    public void heAdds(String todoItem) {
        addItem.called(todoItem);
    }

    @Then("he should have the following item in his list:")
    public void heShouldHaveTheFollowingItemInHisList(List<String> todoItems) {
        List<String> displayedItems = displayedItemList.getDisplayedItems();
        assertThat(displayedItems).containsExactlyElementsOf(todoItems);
    }

}
