package se.iths.util;

import se.iths.entity.Item;
import se.iths.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton //only one object of this type allowed in the application
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        User user1 = new User("Donald Duck","d@d.dk");
        User user2 = new User("Goofy", "g@g.gf");
        User user3 = new User("Mickey Mouse", "m@m.ms");

        Item item1 = new Item("Sofa", "Furniture", 1, 150.00);
        Item item2 = new Item("Guitar", "Instruments", 1, 1000.00);
        Item item3 = new Item("Ball", "Sports Equipment", 1, 100.00);
        Item item4 = new Item("Keyboard", "Instruments", 1, 900.00);
        Item item5 = new Item("Lord of the Rings", "Books", 1, 55.00);
        Item item6 = new Item("TV", "Electronics", 1, 700.00);
        Item item7 = new Item("Diablo II", "Games", 1, 400.00);
        Item item8 = new Item("Diablo", "Games", 1, 150.00);
        Item item9 = new Item("Drums", "Instruments", 1 , 750.00);
        Item item10 = new Item("Xbox", "Electronics", 1 , 500.00);

        user1.addItem(item1);
        user1.addItem(item2);
        user1.addItem(item3);
        user2.addItem(item4);
        user2.addItem(item5);
        user2.addItem(item6);
        user3.addItem(item7);
        user3.addItem(item8);
        user3.addItem(item9);
        user3.addItem(item10);

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

    }

}
