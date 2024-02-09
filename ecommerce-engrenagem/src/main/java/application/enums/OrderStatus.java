package application.enums;

public enum OrderStatus {
    WAITING_PAYMENT(0),
    PAID(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    private int value;

    private OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void printOrderStatus() {
        System.out.println("\n-------------------");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status.value + ": " + status.name());
        }
        System.out.println("-------------------\n");
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found");
    }
}
