package caffeinateme;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private double subtotal;
    private double serviceFee;
    private double total;
    private List<ReceiptLineItem> lineItems;

    public Receipt(double subtotal, double serviceFee, double total, List<ReceiptLineItem> lineItems) {
        this.subtotal = subtotal;
        this.serviceFee = serviceFee;
        this.total = total;
        this.lineItems = lineItems;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public double getTotal() {
        return total;
    }

    public List<ReceiptLineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "subtotal=" + subtotal +
                ", serviceFee=" + serviceFee +
                ", total=" + total +
                '}';
    }
}