package application.DAO;

import application.entities.Order;

import javax.persistence.EntityManager;

public class OrderDAO extends GenericDAO<Long, Order>{
    public OrderDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
