package org.teel.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private void run() {

        entityManagerFactory = Persistence.createEntityManagerFactory("DMP");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Company c1 = new Company();
        Company c2 = new Company();
        c2.setName("c1");
        User u = new User();
        Beh b1 = new Beh();
        Beh b2 = new Beh();

        c1.setName("c1");
        c2.setName("c2");
        u.setName("u1");
        b1.setName("b1");
        b2.setName("b2");

        entityManager.flush();
        entityManager.persist(u);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(b1);
        entityManager.persist(b2);

        u.getBehs().add(b1);
        u.getBehs().add(b2);
        u.getCompanys().add(c1);
        u.getCompanys().add(c2);

        c1.getUsers().add(u);
        c2.getUsers().add(u);

        b1.getUsers().add(u);
        b2.getUsers().add(u);

        entityManager.flush();
        entityManager.persist(u);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }


    public static void main(String[] args) {
        new App().run();
    }

}
