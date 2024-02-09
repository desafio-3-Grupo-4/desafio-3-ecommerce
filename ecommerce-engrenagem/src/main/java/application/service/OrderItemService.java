package application.service;

import application.DAO.OrderItemDAO;
import application.entities.OrderItem;
import application.managers.SimpleEntityManager;

import java.util.List;

public class OrderItemService extends GenericService{
    public OrderItemService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new OrderItemDAO(simpleEntityManager.getEntityManager()));
    }


}
