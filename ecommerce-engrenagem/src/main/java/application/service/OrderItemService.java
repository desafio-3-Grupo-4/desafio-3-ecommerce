package application.service;

import application.DAO.GenericDAO;
import application.DAO.OrderItemDAO;
import application.managers.SimpleEntityManager;

public class OrderItemService extends GenericService{
    public OrderItemService(SimpleEntityManager simpleEntityManager) {
        super(simpleEntityManager, new OrderItemDAO(simpleEntityManager.getEntityManager()));
    }
}
