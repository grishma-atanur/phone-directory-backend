package com.directory.contacts;

import com.directory.contacts.model.Contact;
import com.directory.contacts.model.Contact.ContactBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by hp on 4/3/2017.
 */
public class Application {
    //Hpld a reusuble reference to the session factory(Since we need only one)
   private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        //Create standard service registery object
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();


    }

    public static void main(String[] args) {
        System.out.println("Inside the main method");
        Contact contact = new ContactBuilder("grishma", "atanur")
                .withEmail("atanur.grishma@gmail.com")
                .withPhone(9535469419L)
                .build();
        int id= save(contact);
        //Display a list of contacts before the update
        System.out.printf("%n%n Before Updating%n%n");
        fetchAllContacts().stream().forEach(System.out::println);
        //Get the persisted contact
        Contact c=findContactById(id);

        //Update the contact
        c.setFirstName("viresh");

        //persist the changes
        System.out.printf("%n%n Updating...%n%n");
        update(c);
        System.out.printf("%n%nupdate complete%n%n");
        //Display a list of contacts after update
        System.out.printf("%n%n After Updating%n%n");
        fetchAllContacts().stream().forEach(System.out::println);
    }
    private static Contact findContactById(int id){
        //open a session
        Session session=sessionFactory.openSession();
        //Retrieve the persistent object or(null if not found)
        Contact contact=session.get(Contact.class,id);
        //Close the session
        session.close();
        //return the object
        return contact;
    }
    private static void update(Contact contact){
        //open a session
        Session session=sessionFactory.openSession();
        //Begin a transaction
        session.beginTransaction();

        //use the session to update the contact
        session.update(contact);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();

    }

    @SuppressWarnings("Unchecked")
    private static List<Contact> fetchAllContacts(){
        //open a session
        Session session=sessionFactory.openSession();
        //Create a criteria
        Criteria criteria=session.createCriteria(Contact.class);
        //Get a list Contact objects according to the criteria object
        List<Contact> contacts =criteria.list();



        //close a session
        session.close();
        return contacts;
    }
    private static int save(Contact contact){
        //open a session
        Session session=sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //use the session to save the object
        int id= (int)session.save(contact);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
        return id;


    }
}
