package application.service;

import application.DAO.GenericDAO;
import application.DAO.ProductDAO;
import application.entities.BaseEntity;
import application.entities.Product;
import application.managers.SimpleEntityManager;

import java.util.List;

public class GenericService<PK, T> {
    private GenericDAO dao;

    private SimpleEntityManager simpleEntityManager;

    public GenericService(SimpleEntityManager simpleEntityManager, GenericDAO entity){
        this.simpleEntityManager = simpleEntityManager;
        dao = entity;

    }

    public void save(BaseEntity entity){
        try{
            simpleEntityManager.beginTransaction();

            entity.validate();

            dao.persist(entity);

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
