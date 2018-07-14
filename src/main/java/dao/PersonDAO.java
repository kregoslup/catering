package dao;

import entity.Client;
import entity.Employee;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class PersonDAO extends BaseDAO implements IPersonDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public Client addClient(Client client) {
        return save(client);
    }

    public Set<Client> fetchClients() {
        return executeInTransaction(session -> {
            TypedQuery<Client> query = session.createQuery("select d from Client d", Client.class);
            return new HashSet<>(query.getResultList());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee addEmployee(Employee employee) {
        return save(employee);
    }

    private Client fetchClient(String clientQuery) {
        return executeInTransaction(session -> {
            TypedQuery<Client> query = session.createQuery(clientQuery, Client.class);
            return query.getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Client not found"));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client fetchMockClient() {
        return fetchClient("select c from Client c");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client fetchMockClientEmployee() {
        return fetchClient("select c from Client c where c.employee is not null");
    }
}
