package application;

import application.DAO.ProductDAO;
import application.entities.Product;
import application.managers.SimpleEntityManager;
import application.service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static SimpleEntityManager simpleEntityManager = new SimpleEntityManager("ecommerce-jpa");
    static ProductService productService = new ProductService(simpleEntityManager );
    static List<Product> listOfAllProducts = new ArrayList<>();


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

        printProducts();

        printProducts();


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
}