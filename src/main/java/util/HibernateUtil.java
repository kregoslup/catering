package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;


public class HibernateUtil {

    private final static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    static {
        try {
            logger.info("Creating service registry");

            Configuration config = getConfiguration();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    config.getProperties()).build();

            logger.info("Service registry created");

            config.setSessionFactoryObserver(new SessionFactoryObserver() {
                private static final long  serialVersionUID = 1L;

                @Override
                public void sessionFactoryCreated(SessionFactory factory) {
                }

                @Override
                public void sessionFactoryClosed(SessionFactory factory) {
                    serviceRegistry.close();
                }
            });
            logger.info("Building session factory");
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Error occurred {}", ex.getMessage());
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        logger.info("Opening session");
        return sessionFactory.openSession();
    }

    private static  Configuration getConfiguration() throws URISyntaxException, ClassNotFoundException {
        Configuration cfg = new Configuration();
        cfg.addPackage("main.java.entity");
        EntityFinder.getEntityClassesFromPackage("entity").forEach(cfg::addAnnotatedClass);
//        cfg.addAnnotatedClass(Contact.class );
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//        cfg.setProperty("hibernate.connection.url","jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        cfg.setProperty("hibernate.connection.url","jdbc:h2:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
//        cfg.setProperty("hibernate.connection.username", "postgres");
//        cfg.setProperty("hibernate.connection.password", "postgres");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");
        cfg.setProperty("hibernate.connection.autocommit", "true");
//        cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
//        cfg.setProperty("hibernate.current_session_context_class", "thread");
        return cfg;
    }
}
