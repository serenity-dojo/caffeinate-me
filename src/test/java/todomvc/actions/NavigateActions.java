package todomvc.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class NavigateActions extends UIInteractionSteps {
    @Step("User opens the todo list application")
    public void toTheHomePage() {
        this.openUrl("https://todomvc.com/examples/angularjs/#/");
    }
}
