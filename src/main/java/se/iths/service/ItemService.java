package se.iths.service;

import se.iths.entity.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    //Dynamic
    public List<Item> getByNameDynamic(String name) {
        String query = "SELECT i FROM Item i WHERE i.name = '" + name + "'";
        return entityManager.createQuery(query, Item.class)
                .getResultList();
    }

    //Names parameters
    public List<Item> getByNamedParameters(String name) {
        String query = "SELECT i FROM Item i WHERE i.name = :name";
        return entityManager.createQuery(query, Item.class)
                .setParameter("name", name)
                .getResultList();
    }

    //Positional parameter
    public List<Item> getByNamePositionParameters(String name) {
        String query = "SELECT i FROM Item i WHERE i.name = ?1";
        return entityManager.createQuery(query, Item.class)
                .setParameter(1, name)
                .getResultList();
    }

    //Where between
    public List<Item> getAllItemsBetweenPrice(double minPrice, double maxPrice) {
        String query = "SELECT i FROM Item i WHERE i.price BETWEEN :minPrice AND :maxPrice";
        return entityManager.createQuery(query, Item.class).setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    //Get all name values from Item
    public List<Item> getAllNames() {
        String query = "SELECT i.name FROM Item i";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    //Order by category (alphabetically)
    public List<Item> getAllItemsSortedByCategory() {
        String query = "SELECT i FROM Item i ORDER BY i.category";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    //Select the highest value from all items
    public Double selectMaxPrice() {
        TypedQuery<Double> typedQuery = entityManager.createQuery("SELECT MAX(i.price) FROM Item i", Double.class);
        return typedQuery.getSingleResult();
    }

    //Get all items with Named Query
    public List<Item> getAllWithNamedQuery() {
        return entityManager.createNamedQuery("itemEntity.findAll", Item.class).getResultList();
    }

    //Update price where price > 200
    public void updatePriceWhere() {
        String query = "UPDATE Item i SET i.price=200.00 WHERE i.price > 200.00";
        entityManager.createQuery(query).executeUpdate();
    }

    //Delete items more expensive than 200
    public void deleteExpensive() {
        String query = "DELETE FROM Item i WHERE i.price > 200.00";
        entityManager.createQuery(query).executeUpdate();
    }

}
