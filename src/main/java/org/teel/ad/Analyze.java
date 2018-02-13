package org.teel.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Analyze {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private void run() {
        entityManagerFactory = Persistence.createEntityManagerFactory("DMPA");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    public static void main(String[] args) {
        new Analyze().run();
    }


}
