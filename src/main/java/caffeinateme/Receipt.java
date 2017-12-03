package caffeinateme;

public class Receipt {
    private double subtotal;
    private double serviceFee;
    private double total;

    public Receipt(double subtotal, double serviceFee, double total) {
        this.subtotal = subtotal;
        this.serviceFee = serviceFee;
        this.total = total;
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

    @Override
    public String toString() {
        return "Receipt{" +
                "subtotal=" + subtotal +
                ", serviceFee=" + serviceFee +
                ", total=" + total +
                '}';
    }
}