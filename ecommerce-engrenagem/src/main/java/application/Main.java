package application;

import application.DAO.ProductJpaDAO;
import application.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ProductJpaDAO products = ProductJpaDAO.getInstance();
    static List<Product> listOfAllProducts = new ArrayList<>();


    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        Product p1 = new Product( null, "rocketleague","cars with rockets", 2.4);
        Product p2 = new Product( null, "wolfstein", "Kill Nazis", 20.0);
        Product p3 = new Product( null, "resident evil 4 remake", "kill las plaguas", 50.0);
        Product p4 = new Product( null, "resident evil 3 remake", "kill las plaguas", 50.0);
        Product p5 = new Product( null, "resident evil 2 remake", "kill las plaguas", 50.0);


        products.persist(p1);
        products.persist(p2);
        products.persist(p3);
        products.persist(p4);
        products.persist(p5);




        products.remove(p4);

        printProducts();


        products.removeById(2);

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
        listOfAllProducts = products.findAll();
    }
}