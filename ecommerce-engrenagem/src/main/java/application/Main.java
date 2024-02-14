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
        //populateWithGamest();
        System.out.println("Hello world!");
        int op = 1;

        while (op != 0) {
            op = mainMenu();
        }

        System.out.println("End of application");
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ===================================================================
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
    // =====================================================================
    // ---------------------------------------------------------------------
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
            System.out.print("Id = "+ p.getId() + ", " + p.getName() + ", quantity: ");
            for(OrderItem item : items){
                if(item.getProduct().getId().equals(p.getId())){
                    System.out.print(item.getQuantity());
                }
            }

            // TODO corrigir último ;
            if(i < listOfAllProducts.size()){
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

        for(OrderItem items: order.getProducts()){
            listOfAllProducts.add(productService.findById(items.getProduct().getId()));
        }


        return order;
    }

    // ----------------------------------------------------------------------
    // ......................................................................
    public static void printOrderItem(long orderId,long itemId){
        Order order = updateListOfProducts(orderId);
        List<OrderItem> items = List.copyOf(order.getProducts());
        int i = 0;

        System.out.println("=====================================");

        // for each order
        for (Product p : listOfAllProducts) {
            // print all the information
            if(p.getId().equals(itemId)){
                System.out.print("Product(name: " + p.getName() + ", quantity: " );
                for(OrderItem item : items){
                    if(item.getProduct().getId().equals(p.getId())){
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
    // ......................................................................

    public static int menuAdm() {
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------MenuAdm-------------");
            System.out.println("1- Criar produto");
            System.out.println("2- Atualizar produto");
            System.out.println("3- Consultar produto por ID");
            System.out.println("4- Deletar produtos");
            System.out.println("5- Listar produtos");
            System.out.println("0- Sair");
            System.out.println("---------------------------------");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 5);

        return op;
    }

    public static int menuCostumer() {
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------MenuCostumer-------------");
            System.out.println("1- Novo pedido");
            System.out.println("2- Modificar produtos do pedido");
            System.out.println("3- Modificar status do pedido");
            System.out.println("4- Excluir pedido");
            System.out.println("5- Mostrar pedido em detalhe");
            System.out.println("6- Mostrar pedidos");
            System.out.println("0- Sair");
            System.out.println("------------------------------------");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 6);

        return op;
    }

    public static int mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int op = -1;

        do {
            System.out.println("-------------Main-------------");
            System.out.println("1- Acessar como cliente");
            System.out.println("2- Acessar como adm");
            System.out.println("0- Sair");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");
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

    public static void costumerDecision(int op) {
        Scanner scanner = new Scanner(System.in);
        Order o1 = new Order();
        Long id;
        Integer quantity;
        String keep = "y";
        int idStatus;

        switch (op) {
            //"1- Novo pedido");
            case 1:

                while (!keep.equalsIgnoreCase("n")) {
                    printProducts();
                    System.out.print("Digite o id do produto: ");
                    id = scanner.nextLong();
                    System.out.print("Digite a quantidade: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // consume line buffer
                    Product p1 = productService.findById(id);

                    o1.addProduct(p1,quantity);
                    System.out.println("Deseja adicionar mais pedidos? (y/n)");
                    keep = scanner.next();
                }
                o1.setOrderStatus(OrderStatus.WAITING_PAYMENT);
                orderService.save(o1);
                break;
           // "2- Modificar produtos do carrinho"
            case 2:
                printOrders();
                System.out.print("Digite o id da Ordem: ");
                long orderId = scanner.nextLong();
                printOrderItems(orderId);

                updateOrderitems(orderId);
                break;

            // "3- Modificar status do pedido");
            case 3:
                printOrders();
                System.out.print("Digite o id da ordem: ");
                id = scanner.nextLong();

                o1 = orderService.findById(id);

                System.out.println("Deseja modificá-la para qual status: ");
                OrderStatus.printOrderStatus();
                idStatus = scanner.nextInt();

                o1.setOrderStatus(OrderStatus.fromValue(idStatus));
                orderService.update(o1);
                break;

            // "4- excluir pedido");
            case 4:
                printOrders();
                System.out.print("Digite o id da Ordem: ");
                orderId = scanner.nextLong();
                printOrderItems(orderId);

                Order orderToDelete = orderService.findById(orderId);

                orderService.delete(orderToDelete);
                break;

           // "5- Mostrar pedido em detalhe");
            // TODO mostrar detalhes
            case 5:
                printOrders();
                System.out.print("Digite o id da Ordem: ");
                break;

           // "6- Mostrar pedidos");
            case 6:
                printOrders();
                break;
        }
        scanner.close();


    }

    public static void updateOrderitems(long orderId){
        Scanner scanner = new Scanner(System.in);
        int operation = 0;

        List<OrderItem> items = new ArrayList<>();
        long itemId;
        int quant = 0;
        //TODO acrescentar adicionar produtos ao pedido
        System.out.println("1 - Remover Item");
        System.out.println("2 - Alterar a quantidade");
        System.out.println("3 - Adicionar item ao pedido");
        do{
            operation = scanner.nextInt();
            switch (operation) {
                // delete a item from order
                case 1:
                    System.out.println("Digite o id do item");
                    itemId = scanner.nextLong();
                    printOrderItem(orderId, itemId);

                    Order updatedOrder = orderService.findById(orderId);
                    items = List.copyOf(updatedOrder.getProducts());
                    OrderItem newOrderItem = null;

                    for (OrderItem item : items){
                        if(item.getOrder().getId().equals(orderId) && item.getProduct().getId().equals(itemId)){
                            orderItemService.delete(item);
                            updatedOrder.getProducts().remove(item);
                            orderService.update(updatedOrder);

                            Product updatedProduct = productService.findById(item.getProduct().getId());
                            updatedProduct.getOrders().remove(item);
                            productService.update(updatedProduct);
                        }
                    }

                    break;
                // update a item quantity
                case 2:
                    System.out.println("Digite o id do item");
                    itemId = scanner.nextLong();
                    printOrderItem(orderId, itemId);

                    System.out.println("Digite a nova quantidade do item");
                    scanner.nextLine();
                    quant = scanner.nextInt();

                    updatedOrder = orderService.findById(orderId);
                    items = List.copyOf(updatedOrder.getProducts());
                    for (OrderItem item : items){
                        if(item.getOrder().getId().equals(orderId) && item.getProduct().getId().equals(itemId)){
                            item.setQuantity(quant);
                            orderItemService.update(item);
                        }
                    }
                    break;
                case 3:
                    // add item from order
                    printProducts();
                    System.out.print("Digite o id do produto: ");
                    Long id = scanner.nextLong();
                    System.out.print("Digite a quantidade: ");
                    Integer quantity = scanner.nextInt();
                    scanner.nextLine(); // consume line buffer
                    Product p1 = productService.findById(id);
                    Order o1 = orderService.findById(orderId);
                    OrderItem orderItem = new OrderItem(o1,p1,quantity);

                    orderItemService.save(orderItem);
            }

        } while (operation < 0 || operation > 2);
        scanner.close();
    }

    public  static void populateWithGamest(){
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

    }

    private static void admDecision(int op) {
        Scanner scanner = new Scanner(System.in);
        Product p1;
        Long id;
        String name;
        String description;
        Double value;

        switch (op){
            case 1:
                System.out.println("1- Criar produto");
                System.out.print("Digite o nome do produto: ");
                name = scanner.nextLine();
                System.out.print("Digite a descrição do produto: ");
                description = scanner.nextLine();
                System.out.print("Digite o valor do produto: ");
                value = scanner.nextDouble();
                p1 = new Product(null,name, description,value);

                productService.save(p1);
                break;
            case 2:
                System.out.println("2- Atualizar produto");
                printProducts();
                System.out.print("Digite o id do produto: ");
                id = scanner.nextLong();
                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);

                System.out.println(p1);
                System.out.print("Digite o nome do produto: ");
                name = scanner.nextLine();
                p1.setName(name);

                System.out.print("Digite a descrição do produto: ");
                description = scanner.nextLine();
                p1.setDescription(description);

                System.out.print("Digite o valor do produto: ");
                value = scanner.nextDouble();
                p1.setValue(value);

                productService.update(p1);
                break;
            case 3:
                System.out.println("3- Consultar produto por ID");
                //TODO mostrar mais detalhes
                System.out.print("Digite o id do produto: ");
                id = scanner.nextLong();
                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);

                System.out.println(p1);
                break;
            case 4:
                System.out.println("4- Deletar produtos");
                printProducts();
                System.out.print("Digite o id do produto: ");
                id = scanner.nextLong();
                scanner.nextLine(); // consume line buffer
                p1 = productService.findById(id);

                productService.delete(p1);
                break;
            case 5:
                System.out.println("5- Listar produtos");

                break;
        }
        scanner.close();
    }
    

     /*Product p1 = new Product( null, "rocketleague","cars with rockets", 2.4);
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


        orderService.save(order1);

        OrderItem orderItem1 = new OrderItem(order1, p1);

        orderItemService.save(orderItem1);

        printProducts();

        printOrders();

        Product p1 = new Product(null, "wolfstein", "Killing mother fóckers", 20.0);

        for (int i = 0; i < 2; i++) {
            productService.save(p1);
        }*/

}