package application.service;


import application.DAO.ProductDAO;
import application.entities.Product;
import application.managers.SimpleEntityManager;

import java.util.List;

public class ProductService {
    private ProductDAO dao;

    private SimpleEntityManager simpleEntityManager;

    public ProductService(SimpleEntityManager simpleEntityManager){
        this.simpleEntityManager = simpleEntityManager;
        dao = new ProductDAO(simpleEntityManager.getEntityManager());
    }

    public void save(Product product){
        try{
            simpleEntityManager.beginTransaction();

            product.validate();

            dao.persist(product);

            simpleEntityManager.commit();
        }catch(Exception e){
            System.out.println("Fail to save product: " + e.getMessage());
            e.printStackTrace();

            simpleEntityManager.rollBack();
        }
    }

    public void update(Product entity) {

        try {
            simpleEntityManager.beginTransaction();

            dao.merge(entity);

            simpleEntityManager.commit();

        } catch (Exception ex) {
            System.out.println("Fail to update product: " + ex.getMessage());
            ex.printStackTrace();
            simpleEntityManager.rollBack();
        }

    }

    public void delete(Product entity) {
        try {
            simpleEntityManager.beginTransaction();

            dao.remove(entity);

            simpleEntityManager.commit();

        } catch (Exception ex){
            System.out.println("Remove error: " + ex.getMessage());
            ex.printStackTrace();
            simpleEntityManager.rollBack();
        }
    }

    public List<Product> findAll(){
        return dao.findAll();
    }
}