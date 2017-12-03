package caffeinateme;

public class Customer {
    private int distanceFromShop;

    String actor;

    @Step("Notify caffeinate me that the customer is {0} metres from the shop")
    public void notifyDistanceFromShop(int distanceFromShop) {
        this.distanceFromShop = distanceFromShop;
    }

    @Step("#actor places an order for {0}")
    public void placesOrderFor(String order) {

    }
}
