package caffeinateme;

import static caffeinateme.OrderPriority.Normal;

public class PriorityBy {
    public static OrderPriority eta(int etaInMinutes) {
        if (etaInMinutes < 5) return OrderPriority.Urgent;
        if (etaInMinutes <= 10) return OrderPriority.High;
        return Normal;
    }
}
