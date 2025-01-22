package vladdossik.jdbc.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vladdossik.jdbc.jpa.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres?preferQueryMode=extended",
                    "postgres",
                    "admin"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error while connecting to PostgreSQL", e);
        }
    }

    public static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres?preferQueryMode=extended");
                configuration.setProperty("hibernate.connection.username", "postgres");
                configuration.setProperty("hibernate.connection.password", "admin");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("org.hibernate.SQL", "WARN");
                configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                throw new RuntimeException("Error creating Hibernate SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}
