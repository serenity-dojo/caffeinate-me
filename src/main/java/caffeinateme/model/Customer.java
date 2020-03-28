package caffeinateme.model;

import java.util.Objects;

public class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Customer named(String name) {
        return new Customer(name);
    }

    public void setDistanceFromShop(Integer distanceInMetres) {

    }

    public CustomerOrderBuilder placesAnOrderFor(Order order) {
        return new CustomerOrderBuilder(order);

    }

    public static class CustomerOrderBuilder {
        private Order order;

        public CustomerOrderBuilder(Order order) {
            this.order = order;
        }

        public void at(CoffeeShop coffeeShop) {
            coffeeShop.placeOrder(order);
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
