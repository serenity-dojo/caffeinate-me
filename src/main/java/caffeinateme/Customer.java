package caffeinateme;

import net.thucydides.core.annotations.Step;

public class Customer {
    private int distanceFromShop;

    @Step("Notify caffeinate me that the customer is {0} metres from the shop")
    public void notifyDistanceFromShop(int distanceFromShop) {
        this.distanceFromShop = distanceFromShop;
    }

    @Step
    public void placesOrderFor(String order) {

    }
}
