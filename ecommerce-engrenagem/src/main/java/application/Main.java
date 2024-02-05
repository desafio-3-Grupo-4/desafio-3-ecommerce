package application;

import db.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        Connection conn = DB.getConnection();

        
        DB.closeConnection();
    }
}