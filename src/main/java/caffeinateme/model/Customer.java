package caffeinateme.model;

import java.util.Objects;

public class Customer {
    private String name;
    private float distanceInMetres;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Customer named(String name) {
        return new Customer(name);
    }

    public void setDistanceFromShop(float distanceInMetres) {
        this.distanceInMetres = distanceInMetres;
    }

    public CustomerOrderBuilder placesAnOrderFor(Order order) {
        return new CustomerOrderBuilder(order, distanceInMetres);
    }

    public static class CustomerOrderBuilder {
        private Order order;
        private final float distanceInMetres;

        public CustomerOrderBuilder(Order order, float distanceInMetres) {
            this.order = order;
            this.distanceInMetres = distanceInMetres;
        }

        public void at(CoffeeShop coffeeShop) {
            coffeeShop.placeOrder(order, distanceInMetres);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
