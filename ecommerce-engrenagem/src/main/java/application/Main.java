package application;

import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;

import java.sql.SQLException;
import java.util.Scanner;

import static application.utilities.Util.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        int op;
        
               


        System.out.println("End of application");
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

    public static void updateListOfOrderItem() {
        // bring all the orders from the database and put in the list
        listOfOrderItem = orderItemService.findAll();
    }

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

        scanner.close();

        return op;
    }

    public static int menuCostumer() {
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------MenuCostumer-------------");
            System.out.println("1- Adicionar produto ao carrinho");
            System.out.println("2- Retirar produto do carrinho");
            System.out.println("3- Modificar status do pedido");
            System.out.println("0- Sair");
            System.out.println("------------------------------------");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 3);

        scanner.close();

        return op;
    }

    public static int mainMenu(){
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("-------------Main-------------");
            System.out.println("1- Acessar como cliente");
            System.out.println("2- Acessar como adm");
            System.out.println("0- Sair");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

        } while (op < 0 || op > 2);

        scanner.close();
        
        switch (op){
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
    public static int costumerDecision(int op){
        Scanner scanner = new Scanner(System.in);
        switch (op){
            case 1:


        }
        
    }
    private static void admDecision(int i) {
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