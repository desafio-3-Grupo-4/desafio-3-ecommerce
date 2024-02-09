package application.service;


import application.DAO.GenericDAO;
import application.DAO.OrderItemDAO;
import application.DAO.ProductDAO;
import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;
import application.managers.SimpleEntityManager;

import java.util.List;

public class ProductService extends GenericService<Long, Product>{


    public ProductService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new ProductDAO(simpleEntityManager.getEntityManager()));

    }



}