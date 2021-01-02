package web.dao;

import web.model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> index();
    Person show(Long  id);
    void save(Person person);
    void update(Long  id, Person updatedPerson);
    void delete(Long  id);
    Person findByName(String username);

}
