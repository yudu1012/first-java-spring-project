package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");


    public List<Person> index(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Person> people = entityManager.createQuery("select p from Person p", Person.class).getResultList();
        return people;
    }
    public Person show(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person person = entityManager.find(Person.class,id);
        return person;
    }
    public void save(Person person){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void update(int id,Person personUpdate){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        personUpdate.setId(id);
        entityManager.merge(personUpdate);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void delete(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Person person = entityManager.find(Person.class,id);
        if (person != null){
            entityManager.remove(person);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
