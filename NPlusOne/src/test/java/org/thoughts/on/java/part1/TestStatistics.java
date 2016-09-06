/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thoughts.on.java.part1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughts.on.java.model.Author;

/**
 *
 * @author Thorben
 */
public class TestStatistics {

    private EntityManagerFactory emf;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @After
    public void close() {
        emf.close();
    }

    @Test
    public void selectAuthors() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Author> authors = em.createQuery("SELECT a FROM Author a",
                Author.class).getResultList();

        for (Author a : authors) {
            System.out.println("Author "
                    + a.getFirstName()
                    + " "
                    + a.getLastName()
                    + " wrote "
                    + a.getBooks().size()
                    + " books.");
        }

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void selectAuthorsInJpa2() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> q = cb.createQuery(Author.class);
        Root<Author> r = q.from(Author.class);
        r.fetch("books", JoinType.LEFT);
        q.distinct(true);

        List<Author> authors = em.createQuery(q).getResultList();

        for (Author a : authors) {
            System.out.println("Author "
                    + a.getFirstName()
                    + " "
                    + a.getLastName()
                    + " wrote "
                    + a.getBooks().size()
                    + " books.");
        }

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void selectAuthorsHibernate() {
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(org.hibernate.Session.class);

        Criteria c = session.createCriteria(Author.class);
        c.setFetchMode("books", FetchMode.JOIN);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Author> authors = c.list();

        for (Author a : authors) {
            System.out.println("Author "
                    + a.getFirstName()
                    + " "
                    + a.getLastName()
                    + " wrote "
                    + a.getBooks().size()
                    + " books.");
        }

    }
}
