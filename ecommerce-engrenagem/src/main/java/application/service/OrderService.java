package application.service;

import application.DAO.GenericDAO;
import application.DAO.OrderDAO;
import application.DAO.OrderItemDAO;
import application.DAO.ProductDAO;
import application.entities.BaseEntity;
import application.entities.Order;
import application.entities.OrderItem;
import application.entities.Product;
import application.managers.SimpleEntityManager;

public class OrderService extends GenericService<Long, Order>{
    private OrderItemDAO orderItemDAO ;


    public OrderService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new OrderDAO(simpleEntityManager.getEntityManager()));
        orderItemDAO = new OrderItemDAO(simpleEntityManager.getEntityManager());

    }


    public void save(Order entity){
        try{
            simpleEntityManager.beginTransaction();

            entity.validate();

            dao.persist(entity);

            for(OrderItem item: entity.getProducts()){
                orderItemDAO.persist(item);

            }

            simpleEntityManager.commit();
        }catch(Exception e){
            System.out.println("Fail to save Order item: " + e.getMessage());
            e.printStackTrace();

            simpleEntityManager.rollBack();
        }
    }

}
