package caffeinateme;
public class Customer_NB {

        private String name;
        private int distanceFromShop;

        public Customer_NB (String customerName)
        {
            this.name=customerName;
        }

        public void setDistanceFromShop(int distanceFromShop) {
            this.distanceFromShop = distanceFromShop;
        }

        public Integer getDistanceFromShop()
        {
            return distanceFromShop;
        }

        public Order_NB placeOrderFor(String orderedProduct)
        {
            Order_NB order = new Order_NB(this,orderedProduct);
            return order;
        }

        public String getCustomerName()
        {
            return this.name;
        }
}

