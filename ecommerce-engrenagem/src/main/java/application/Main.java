package application;

import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;
import application.enums.OrderStatus;
import application.service.OrderService;

import java.sql.SQLException;
import java.util.*;

import static application.utilities.Util.*;

public class Main {

    public static void main(String[] args) throws SQLException {
//        populateWithGamest();
        Locale.setDefault(Locale.US);
        int op = 1;

        while (op != 0) {
            try {
                op = mainMenu();
            } catch (NumberFormatException e){
                System.out.println("Enter a number in the correct format: '0.00'");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
        System.out.println("End of application");
    }
    public static int mainMenu() {
//        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------Main-------------");
            System.out.println("1- Access as customer");
            System.out.println("2- Access as adm");
            System.out.println("0- Exit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 2);

        switch (op) {
            case 1:
                costumerDecision(menuCostumer());
                return 1;
            case 2:
                admDecision(menuAdm());
                return 1;
            default:
                return op;
        }
    }
    public static int menuAdm() {
//        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------MenuAdm-------------");
            System.out.println("1- Create product");
            System.out.println("2- Update product");
            System.out.println("3- Find product by ID");
            System.out.println("4- Delete products");
            System.out.println("5- List products");
            System.out.println("0- Exit");
            System.out.println("---------------------------------");
            System.out.print("Choose an option: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 5);

        return op;
    }

    public static int menuCostumer() {
//        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------MenuCostumer-----------");
            System.out.println("1- New order");
            System.out.println("2- Modify order items");
            System.out.println("3- Modify order status");
            System.out.println("4- Delete order");
            System.out.println("5- Show order in detail");
            System.out.println("6- Show orders");
            System.out.println("0- Exit");
            System.out.println("------------------------------------");
            System.out.print("Choose an option: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 6);

        return op;
    }
    public static void costumerDecision(int op) {
//        Scanner scanner = new Scanner(System.in);
        Order o1 = new Order();
        Long id;
        Integer quantity;
        String keep = "y";
        int idStatus;

        switch (op) {
            //"1- New order";
            case 1:

                while (!keep.equalsIgnoreCase("n")) {
                    printProducts();
                    System.out.print("Enter the product id: ");
                    id = scanner.nextLong();
                    System.out.print("Enter the quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // consume line buffer
                    Product p1 = productService.findById(id);

                    o1.addProduct(p1, quantity);
                    System.out.println("Do you want to add more products? (y/n)");
                    keep = scanner.next();
                }
                o1.setOrderStatus(OrderStatus.WAITING_PAYMENT);
                orderService.save(o1);
                break;
            // "2- Modify order items"
            case 2:
                printOrders();
                System.out.print("Enter the order id: ");
                long orderId = scanner.nextLong();
                printOrderItems(orderId);

                updateOrderitems(orderId);
                break;

            // "3- Modify order status";
            case 3:
                printOrders();
                System.out.print("Enter the order id: ");
                id = scanner.nextLong();

                o1 = orderService.findById(id);

                System.out.print("Which status do you want to change the order to: ");
                OrderStatus.printOrderStatus();
                idStatus = scanner.nextInt();

                o1.setOrderStatus(OrderStatus.fromValue(idStatus));
                orderService.update(o1);
                break;

            // "4- Delete order";
            case 4:
                printOrders();
                System.out.print("Enter the order id: ");
                orderId = scanner.nextLong();
                printOrderItems(orderId);

                Order orderToDelete = orderService.findById(orderId);

                orderService.delete(orderToDelete);
                break;

            // "5- Show order in detail";
            // TODO show details
            case 5:
                printOrders();
                System.out.print("Enter the order id: ");
                break;

            // "6- Show orders";
            case 6:
                printOrders();
                break;
        }
//        scanner.close();
    }
    private static void admDecision(int op) {
//        Scanner scanner = new Scanner(System.in);
        Product p1;
        Long id;
        String name;
        String description;
        String value;

        switch (op) {

            case 1:
//          "1- Create product";
                System.out.print("Enter the product name: ");
                verifyLineBufferScanner();
                name = scanner.nextLine();
                System.out.print("Enter the product description: ");
                description = scanner.nextLine();
                System.out.print("Enter the product value: ");
                value = scanner.next().replace(",",".");;

                p1 = new Product(null, name, description, Double.parseDouble(value));

                productService.save(p1);
                break;
            case 2:
//          "2- Update product";
                printProducts();
                System.out.print("Enter the product id: ");
                id = scanner.nextLong();
//                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);

                System.out.println(p1);
                System.out.print("Enter the new product name: ");
                verifyLineBufferScanner();
                name = scanner.nextLine();
                p1.setName(name);

                System.out.print("Enter the new product description: ");
                description = scanner.nextLine();
                p1.setDescription(description);

                System.out.print("Enter the new product value: ");
                value = scanner.next().replace(",",".");
                p1.setValue(Double.parseDouble(value));

                productService.update(p1);
                break;
            case 3:
//          "3- Find product by ID";
                //TODO show more details
                System.out.print("Enter the product id: ");
                id = scanner.nextLong();
//                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);


                System.out.println(p1 == null ? "Product Not Found!" : p1);
                break;
            case 4:
//          "4- Delete products";
                printProducts();
                System.out.print("Enter the product id: ");
                id = scanner.nextLong();
//                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);

                productService.delete(p1);
                break;
            case 5:
//          "5- List products";
                printProducts();
                break;
        }
//        scanner.close();
    }

    public static void updateOrderitems(long orderId) {
//        Scanner scanner = new Scanner(System.in);
        int op = 0;

        List<OrderItem> items;
        long itemId;
        int quant = 0;

        System.out.println("------------------------------");
        System.out.println("1 - Remove item");
        System.out.println("2 - Change quantity");
        System.out.println("3 - Add item to the order");
        System.out.println("------------------------------");
        System.out.print("Choose an option: ");
        do {
            op = scanner.nextInt();
            switch (op) {
                // delete an item from order
                case 1:
                    System.out.print("Enter the item id: ");
                    itemId = scanner.nextLong();
                    printOrderItem(orderId, itemId);

                    Order updatedOrder = orderService.findById(orderId);
                    items = List.copyOf(updatedOrder.getProducts());

                    for (OrderItem item : items) {
                        if (item.getOrder().getId().equals(orderId) && item.getProduct().getId().equals(itemId)) {
                            orderItemService.delete(item);
                            updatedOrder.getProducts().remove(item);
                            orderService.update(updatedOrder);

                            Product updatedProduct = productService.findById(item.getProduct().getId());
                            updatedProduct.getOrders().remove(item);
                            productService.update(updatedProduct);
                        }
                    }
                    break;
                // update an item quantity
                case 2:
                    System.out.print("Enter the item id: ");
                    itemId = scanner.nextLong();
                    printOrderItem(orderId, itemId);

                    System.out.print("Enter the new quantity of the item: ");
//                    scanner.nextLine(); // consume line buffer
                    quant = scanner.nextInt();

                    updatedOrder = orderService.findById(orderId);
                    items = List.copyOf(updatedOrder.getProducts());
                    for (OrderItem item : items) {
                        if (item.getOrder().getId().equals(orderId) && item.getProduct().getId().equals(itemId)) {
                            item.setQuantity(quant);
                            orderItemService.update(item);
                        }
                    }
                    break;
                case 3:
                    // add item to the order
                    printProducts();
                    System.out.print("Enter the product id: ");
                    Long id = scanner.nextLong();
                    System.out.print("Enter the quantity: ");
                    Integer quantity = scanner.nextInt();
//                    scanner.nextLine(); // consume line buffer
                    Product p1 = productService.findById(id);
                    Order o1 = orderService.findById(orderId);
                    OrderItem orderItem = new OrderItem(o1, p1, quantity);

                    orderItemService.save(orderItem);
            }

        } while (op < 0 || op > 3);
//        scanner.close();
    }
    public static void printProducts() {
        // update the list of products
        updateListOfAllProducts();


        System.out.println("=====================================");
        // for each product
        for (Product p : listOfAllProducts) {
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfAllProducts.clear();
    }

    public static void updateListOfAllProducts() {
        // bring all the products from the database and put in the list
        listOfAllProducts = productService.findAll();
    }

    public static void printOrders() {
        // update the list of orders
        updateListOfAllOrders();


        System.out.println("=====================================");
        // for each order
        for (Order p : listOfAllOrders) {
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfAllOrders.clear();
    }

    public static void updateListOfAllOrders() {
        // bring all the orders from the database and put in the list
        listOfAllOrders = orderService.findAll();
    }

    public static void printOrderItems() {
        // update the list of items in the order
        updateListOfOrderItem();


        System.out.println("=====================================");
        // for each order
        for (OrderItem p : listOfOrderItem) {
            // print all the information
            System.out.println(p.toString());
        }
        System.out.println("=====================================");

        // clear the memory
        listOfOrderItem.clear();
    }

    public static void printOrderItems(long orderid) {
        // update the list of items in the order
        Order order = updateListOfProducts(orderid);
        List<OrderItem> items = List.copyOf(order.getProducts());
        int i = 0;

        System.out.println("=====================================");
        System.out.print("Order " + orderid + ": Products(");
        // for each order
        for (Product p : listOfAllProducts) {
            // print all the information
            System.out.print("Id = " + p.getId() + ", " + p.getName() + ", quantity: ");
            for (OrderItem item : items) {
                if (item.getProduct().getId().equals(p.getId())) {
                    System.out.print(item.getQuantity());
                }
            }

            // TODO corrigir último ;
            if (i < listOfAllProducts.size()) {
                System.out.print(" ; ");
                i++;
            }
        }
        System.out.println(")");
        System.out.println("=====================================");

        // clear the memory
        listOfOrderItem.clear();
        listOfAllProducts.clear();
    }

    public static void updateListOfOrderItem() {
        // bring all the orders from the database and put in the list
        listOfOrderItem = orderItemService.findAll();
    }

    public static Order updateListOfProducts(long orderid) {
        // bring all the orders from the database and put in the list
        Order order = orderService.findById(orderid);

        for (OrderItem items : order.getProducts()) {
            listOfAllProducts.add(productService.findById(items.getProduct().getId()));
        }
        return order;
    }

    public static void printOrderItem(long orderId, long itemId) {
        //TODO exception if not found itemId in order
        Order order = updateListOfProducts(orderId);
        List<OrderItem> items = List.copyOf(order.getProducts());
        int i = 0;

        System.out.println("=====================================");

        // for each order
        for (Product p : listOfAllProducts) {
            // print all the information
            if (p.getId().equals(itemId)) {
                System.out.print("Product(name: " + p.getName() + ", quantity: ");
                for (OrderItem item : items) {
                    if (item.getProduct().getId().equals(p.getId())) {
                        System.out.print(item.getQuantity());
                    }
                }

                System.out.println(")");
            }
        }
        System.out.println(")");
        System.out.println("=====================================");

        // clear the memory
        listOfOrderItem.clear();
        listOfAllProducts.clear();

    }
    public static void populateWithGamest() {
        Product p1 = new Product(null, "rocketleague", "cars with rockets", 2.4);
        Product p2 = new Product(null, "wolfstein", "Kill Nazis", 20.0);
        Product p3 = new Product(null, "resident evil 4 remake", "kill las plaguas", 50.0);
        Product p4 = new Product(null, "resident evil 3 remake", "kill las plaguas", 50.0);
        Product p5 = new Product(null, "resident evil 2 remake", "kill las plaguas", 50.0);


        productService.save(p1);
        productService.save(p2);
        productService.save(p3);
        productService.save(p4);
        productService.save(p5);
    }
    public static void verifyLineBufferScanner(){
        if (scanner.hasNextLine()) { // verify line buffer
            scanner.nextLine();
        }
    }
}