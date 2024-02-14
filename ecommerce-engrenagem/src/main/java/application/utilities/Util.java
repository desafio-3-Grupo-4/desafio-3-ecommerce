package application.utilities;

import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;
import application.managers.SimpleEntityManager;
import application.service.OrderItemService;
import application.service.OrderService;
import application.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    public static SimpleEntityManager simpleEntityManager = new SimpleEntityManager("ecommerce-jpa");
    public static ProductService productService = new ProductService(simpleEntityManager );
    public static OrderService orderService = new OrderService(simpleEntityManager);
    public  static OrderItemService orderItemService = new OrderItemService(simpleEntityManager);
    public static List<Product> listOfAllProducts = new ArrayList<>();
    public static List<Order> listOfAllOrders = new ArrayList<>();
    public static List<OrderItem> listOfOrderItem = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
}
