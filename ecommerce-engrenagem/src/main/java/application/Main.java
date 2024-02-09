package application;

import application.DAO.ProductDAO;
import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;
import application.managers.SimpleEntityManager;
import application.service.OrderService;
import application.service.OrderItemService;
import application.service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static SimpleEntityManager simpleEntityManager = new SimpleEntityManager("ecommerce-jpa");
    static ProductService productService = new ProductService(simpleEntityManager );
    static OrderService orderService = new OrderService(simpleEntityManager);
    static OrderItemService orderItemService = new OrderItemService(simpleEntityManager);
    static List<Product> listOfAllProducts = new ArrayList<>();
    static List<Order> listOfAllOrders = new ArrayList<>();

    static List<OrderItem> listOfOrderItem = new ArrayList<>();


    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        Product p1 = new Product( null, "rocketleague","cars with rockets", 2.4);
        Product p2 = new Product( null, "wolfstein", "Kill Nazis", 20.0);
        Product p3 = new Product( null, "resident evil 4 remake", "kill las plaguas", 50.0);
        Product p4 = new Product( null, "resident evil 3 remake", "kill las plaguas", 50.0);
        Product p5 = new Product( null, "resident evil 2 remake", "kill las plaguas", 50.0);


        productService.save(p1);
        productService.save(p2);
        productService.save(p3);
        productService.save(p4);
        productService.save(p5);

        Order order1 = new Order();

        order1.addProduct(productService.findById(2L));
        order1.addProduct(productService.findById(5L));

        productService.findById(2L).addOrder(order1);
        productService.findById(5L).addOrder(order1);

        orderService.save(order1);

        printProducts();

        printOrders();



    }

    public static void printProducts(){
        // update the list of products
        updateListOfAllProducts();


        System.out.println("=====================================");
        // for each product
        for(Product p : listOfAllProducts){
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfAllProducts.clear();
    }

    public static void updateListOfAllProducts(){
        // bring all the products from the database and put in the list
        listOfAllProducts = productService.findAll();
    }



    public static void printOrders(){
        // update the list of orders
        updateListOfAllOrders();


        System.out.println("=====================================");
        // for each order
        for(Order p : listOfAllOrders){
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfAllOrders.clear();
    }

    public static void updateListOfAllOrders(){
        // bring all the orders from the database and put in the list
        listOfAllOrders = orderService.findAll();
    }

    public static void printOrderItems(){
        // update the list of items in the order
        updateListOfOrderItem();


        System.out.println("=====================================");
        // for each order
        for(OrderItem p : listOfOrderItem){
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfOrderItem.clear();
    }

    public static void updateListOfOrderItem(){
        // bring all the orders from the database and put in the list
        listOfOrderItem = orderItemService.findAll();
    }

}