package session;

import entity.Client;
import entity.Diet;

public class Session {

    private Diet diet;

    private Client client;

    private static Session ourInstance = new Session();

    public static Session getInstance() {
        return ourInstance;
    }

    private Session() {
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
