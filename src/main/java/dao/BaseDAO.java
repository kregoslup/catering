package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.function.Function;

public abstract class BaseDAO {

    /**
     * Executes provided statement inside transaction
     *
     * @param func Function to be executed in transaction
     * @param <T> Type returned by aforementioned function
     * @return Result of database manipulation
     */
    public <T> T executeInTransaction(Function<Session, T> func) {
        Session session = HibernateUtil.openSession();
        Transaction trans = session.beginTransaction();

        T result = func.apply(session);

        trans.commit();
        session.close();

        return result;
    }

    /**
     * Saves entity in database
     *
     * @param entity Entity to save
     * @param <T> Type of entity
     * @return Saved Entity
     */
    public <T> T save(T entity) {
        executeInTransaction(session -> session.save(entity));
        return entity;
    }
}
