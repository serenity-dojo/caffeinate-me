package caffeinateme;

public class Order_NB {
    private final Customer_NB customer;
    private String orderedItem ;
    private String orderStatus;
    public Order_NB (Customer_NB customer, String orderedProduct)
    {
        this.customer=customer;
        this.orderedItem=orderedProduct;
        this.orderStatus=setOrderStatus();
    }

    private String setOrderStatus() {
        String status;
        if(this.customer.getDistanceFromShop() < 101)
        {
            status= "Urgent";
        }
        else
        {
            status ="Normal";
        }
        return status;
    }

    public String getOrderStatus()
    {
        return this.orderStatus;
    }

    public String getOrderedCustomerName()
    {
        return this.customer.getCustomerName();
    }


}


