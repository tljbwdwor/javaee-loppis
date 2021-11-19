package se.iths.service;

import se.iths.entity.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;



    public void createItem(Item item) {
        entityManager.persist(item);
    }

    public void updateItem(Item item) {
        entityManager.merge(item);
    }

    public Item findItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("SELECT i from Item i", Item.class).getResultList();
    }

    public void deleteItem(Long id) {
        Item foundItem = entityManager.find(Item.class, id);
        entityManager.remove(foundItem);
    }

    // PATCH
    public Item updateName(Long id, String name) {
        Item foundItem = entityManager.find(Item.class, id);
        foundItem.setName(name);
        // merge() behövs ej
        // entityManager.merge(foundItem);
        return foundItem;
    }


    // For demo purpose
    @PostConstruct
    public void itemClassCreate() {
        System.out.println("Item service class created!");
    }

    // For demo purpose
    @PreDestroy
    public void itemClassDestroy() {
        System.out.println("Item service class says goodbye for now!");
    }

    //JPQL QUERIES

    public List<Item> getAllNames() {
        String query = "SELECT i.name FROM Item i";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    public List<Item> getAllItemsSortedByCategory() {
        String query = "SELECT i FROM Item i ORDER BY i.category";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    public Query selectMaxPrice() {
        return entityManager.createQuery("SELECT MAX(i.price) FROM Item i");
    }

}
