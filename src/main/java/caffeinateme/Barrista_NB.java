package caffeinateme;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Barrista_NB {
    List<Order_NB> receivedOrders= new ArrayList<>() ;
    public void addToReceivedOrders(Order_NB order)
    {
        this.receivedOrders.add(order);
    }

    public List<Order_NB> getPendingOrders() {
        return receivedOrders;
    }

    public List<Order_NB> getUrgentOrders() {
        List<Order_NB> results = new ArrayList<>();
        for(int i=0;i<receivedOrders.size();i++) {
            if (receivedOrders.get(i).getOrderStatus()=="Urgent")
                results.add(receivedOrders.get(i));
        }
        return results;
    }


    public String getCathysOrderStatus()
    {
        int i;
        for( i=0;i<receivedOrders.size();i++) {
            if(receivedOrders.get(i).getOrderedCustomerName() .equals("cathy"))
            {
                break;
            }
        }
        return receivedOrders.get(i).getOrderStatus();
    }

}

