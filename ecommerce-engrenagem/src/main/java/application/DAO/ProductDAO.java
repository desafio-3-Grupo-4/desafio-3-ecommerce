package application.DAO;

import application.entities.Product;

import javax.persistence.EntityManager;

public class ProductDAO extends GenericDAO<Long, Product>{

    // Singleton


    public ProductDAO(EntityManager entityManager){
        super( entityManager);
    }


}
