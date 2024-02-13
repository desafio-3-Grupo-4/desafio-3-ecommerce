package application.service;

import application.DAO.OrderDAO;
import application.DAO.OrderItemDAO;
import application.DAO.ProductDAO;
import application.entities.BaseEntity;
import application.entities.Order;
import application.entities.OrderItem;
import application.managers.SimpleEntityManager;

public class OrderService extends GenericService<Long, Order>{
    private OrderItemDAO orderItemDAO ;

    private ProductDAO productDAO;


    public OrderService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new OrderDAO(simpleEntityManager.getEntityManager()));
        orderItemDAO = new OrderItemDAO(simpleEntityManager.getEntityManager());
        productDAO = new ProductDAO((simpleEntityManager.getEntityManager()));

    }

    public void save(Order entity){
        try{
            simpleEntityManager.beginTransaction();

            entity.validate();

            dao.persist(entity);

            for(OrderItem item: entity.getProducts()){
                orderItemDAO.persist(item);
                productDAO.getById(item.getProduct().getId()).addOrder(entity);
            }

            simpleEntityManager.commit();
        }catch(Exception e){
            System.out.println("Fail to save Order item: " + e.getMessage());
            e.printStackTrace();

            simpleEntityManager.rollBack();
        }
    }

    public void update(Order entity) {
        try{
            simpleEntityManager.beginTransaction();

            entity.validate();

            dao.merge(entity);

            for(OrderItem item: entity.getProducts()){
                orderItemDAO.merge(item);
                productDAO.getById(item.getProduct().getId()).addOrder(entity);
            }

            simpleEntityManager.commit();
        }catch(Exception e){
            System.out.println("Fail to save Order item: " + e.getMessage());
            e.printStackTrace();

            simpleEntityManager.rollBack();
        }
    }

    public void delete(Order entity){
        try{
            simpleEntityManager.beginTransaction();

            dao.remove(entity);

            for(OrderItem item: entity.getProducts()){
                productDAO.getById(item.getProduct().getId()).removeOrder(entity.getId());
                orderItemDAO.remove(item);

            }

            simpleEntityManager.commit();
        }catch(Exception e){
            System.out.println("Fail to save Order item: " + e.getMessage());
            e.printStackTrace();

            simpleEntityManager.rollBack();
        }
    }




}
