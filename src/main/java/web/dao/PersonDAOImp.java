package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Person;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonDAOImp implements PersonDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Person> index() {
        return entityManager.createQuery("from Person").getResultList();
    }

    public Person show(Long  id) {
       Query query =  entityManager.createQuery("from Person where id = :id");
       query.setParameter("id", id);
       return (Person) query.getSingleResult();
    }

    public void save(Person person) {
        String pass = passwordEncoder.encode(person.getPassword());
        person.setPassword(pass);
        entityManager.merge(person);
        entityManager.flush();
    }

    public void update(Long  id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        String pass= passwordEncoder.encode(updatedPerson.getPassword());
        personToBeUpdated.setPassword(pass);
    }


    public void delete(Long  id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }

    @Override
    public Person findByName(String username) {
        Query query =  entityManager.createQuery("from Person where name = :name");
        query.setParameter("name", username);
        return (Person) query.getSingleResult();
    }
}