package todomvc.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class AddItemActions extends UIInteractionSteps {

    @Step("User adds the '{0}' item to the list")
    public void called(String todoItem) {
        $(".new-todo").typeAndEnter(todoItem);
    }
}
