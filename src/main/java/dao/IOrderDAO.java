package dao;

import entity.Client;
import entity.DietOrder;

import java.util.List;

public interface IOrderDAO {
    /**
     * Saves entity in database
     *
     * @param dietOrder Order entity to save
     * @return Saved entity
     */
    DietOrder saveDiet(DietOrder dietOrder);

    /**
     * Fetches all orders for provided client
     *
     * @param client Client who is owner of orders
     * @return List of client's orders
     */
    List<DietOrder> fetchOrders(Client client);
}
