package dao;

import entity.Client;
import entity.Employee;

public interface IPersonDAO {

    /**
     * Saves client in database
     * @param client Client to save
     * @return Saved client entity
     */
    Client addClient(Client client);

    /**
     * Saves employee in database
     * @param employee Employee to save
     * @return Saved employee
     */
    Employee addEmployee(Employee employee);

    /**
     * Fetches mock client, do not use in production
     * @return Mock client
     */
    Client fetchMockClient();

    /**
     * Fetches mock client, do not use in production
     * @return Mock client
     */
    Client fetchMockClientEmployee();
}
