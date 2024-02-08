package application.DAO;

import application.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductJpaDAO {
    private static ProductJpaDAO instance;

    protected EntityManager entityManager;
    private ProductJpaDAO(){
        entityManager = getEntityManager();
    }

    public static ProductJpaDAO getInstance(){
        if (instance == null){
            instance = new ProductJpaDAO();
        }

        return instance;
    }

    private EntityManager getEntityManager (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
        if(entityManager == null){
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }


    public Product getById(final int id){
        return entityManager.find(Product.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll(){
        return entityManager.createQuery("FROM " + Product.class.getName()).getResultList();
    }

    public void persist(Product product){
        try{
            entityManager.getTransaction().begin();

            entityManager.persist(product);

            entityManager.getTransaction().commit();
        } catch (Exception ex){
            System.out.println("Fail to save product: " + ex.getMessage());
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Product product){
        try{
            entityManager.getTransaction().begin();

            product = entityManager.find(Product.class, product.getId());
            entityManager.remove(product);

            entityManager.getTransaction().commit();


        } catch (Exception ex){
            System.out.println("Remove error: " + ex.getMessage());
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id){
        try {
            Product p1 = getById(id);

            remove(p1);
        } catch (Exception ex){
            System.out.println("Remove By Id Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
