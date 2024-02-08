package application;

import application.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        Product p1 = new Product( null, "rocketleague","cars with rockets", 2.4);
        Product p2 = new Product( null, "wolfstein", "Kill Nazis", 20.0);
        Product p3 = new Product( null, "resident evil 4 remake", "kill las plaguas", 50.0);


        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);


    }
}