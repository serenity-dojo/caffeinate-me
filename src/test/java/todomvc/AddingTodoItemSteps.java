package todomvc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddingTodoItemSteps {

    WebDriver driver;

    @Before("@webtest")
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    @After("@webtest")
    public void closeDriver() {
        driver.quit();
    }

    @Given("Todd has an empty todo list")
    public void toddHasAnEmptyTodoList() {
        driver.get("https://todomvc.com/examples/angularjs/#/");
    }

    @When("he adds {string}")
    public void heAdds(String todoItem) {
        driver.findElement(By.cssSelector(".new-todo")).sendKeys(todoItem);
        driver.findElement(By.cssSelector(".new-todo")).sendKeys(Keys.ENTER);
    }

    @Then("he should have the following item in his list:")
    public void heShouldHaveTheFollowingItemInHisList(List<String> todoItems) {
        List<String> displayedItems =
                driver.findElements(By.cssSelector(".todo-list label"))
                .stream().map(WebElement::getText).toList();

        assertThat(displayedItems).containsExactlyElementsOf(todoItems);
    }

}
