package todomvc.actions;

import net.serenitybdd.core.pages.PageComponent;

import java.util.List;

public class DisplayedItemList extends PageComponent {
    public List<String> getDisplayedItems() {
        return $$(".todo-list label").texts();
    }
}
