package dao;

import entity.Client;
import entity.DietOrder;

import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDAO extends BaseDAO implements IOrderDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public DietOrder saveDiet(DietOrder dietOrder) {
        return save(dietOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DietOrder> fetchOrders(Client client) {
        return executeInTransaction(session -> {
            TypedQuery<DietOrder> query = session.createQuery(
                    "select d from DietOrder d where " +
                            "d.client = :client",
                    DietOrder.class
            );
            query.setParameter("client", client);
            return query.getResultList();
        });
    }
}
