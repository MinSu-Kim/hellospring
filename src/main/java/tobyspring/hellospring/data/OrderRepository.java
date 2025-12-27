package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring.order.Order;

public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void sava(Order order) {
        entityManager.persist(order);
    }
}
