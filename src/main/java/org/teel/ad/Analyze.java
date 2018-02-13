package org.teel.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyze {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    /*protected EntityManager getEntityManager(String driver, String url, String username, String password) {
        EntityManager em = null;
        Map<String, String> properties = new HashMap();
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("dynamicJPA", properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em = (EntityManager) entityManagerFactory.createEntityManager();
    }*/

    private void run() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("DMPA");
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            TypedQuery<User> query =
                    entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> results = query.getResultList();

            for (User u : results) {
                System.out.println(u.getName());
                for (Company c : u.getCompanys()) {
                    System.out.println(c.getName());
                }
                for (Beh b : u.getBehs()) {
                    System.out.println(b.getName());
                }
            }

            TypedQuery<Beh> query2 =
                    entityManager.createQuery("SELECT b FROM Beh b inner join b.users u where u.name = :na", Beh.class);
            List<Beh> ll = query2.setParameter("na", "u1").getResultList();
            for (Beh b : ll) {
                System.out.println(b.getName());
            }

            //"SELECT d.name, COUNT(e), AVG(e.salary) "
            //        + "FROM Department d JOIN d.employees e GROUP BY d.name")

            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public static void main(String[] args) {
        new Analyze().run();
    }


}
