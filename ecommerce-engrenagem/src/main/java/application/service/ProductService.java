package application.service;


import application.DAO.ProductDAO;
import application.entities.Product;
import application.managers.SimpleEntityManager;

public class ProductService extends GenericService<Long, Product>{


    public ProductService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new ProductDAO(simpleEntityManager.getEntityManager()));

    }



}