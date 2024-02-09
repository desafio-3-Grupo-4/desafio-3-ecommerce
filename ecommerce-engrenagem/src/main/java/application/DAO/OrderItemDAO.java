package application.DAO;

import application.entities.Order;
import application.entities.OrderItem;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderItemDAO extends GenericDAO<Long, OrderItem> {
    public OrderItemDAO(EntityManager entityManager) {
        super(entityManager);
    }


}
