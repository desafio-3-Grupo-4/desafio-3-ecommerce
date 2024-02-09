package application.DAO;

import application.entities.OrderItem;

import javax.persistence.EntityManager;

public class OrderItemDAO extends GenericDAO<Long, OrderItem> {
    public OrderItemDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
