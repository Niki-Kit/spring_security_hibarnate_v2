package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.PersonDAO;
import web.dao.RoleDAO;
import web.model.Person;
import web.model.Role;

import java.util.Collections;
import java.util.List;

@Service("personServiceImp")
public class PersonServiceImp implements PersonService, UserDetailsService {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    RoleDAO roleDAO;


    public List<Person> index() {
        return personDAO.index();
    }
    @Transactional
    public Person show(Long  id) {
        return personDAO.show(id);
    }

    @Transactional
    public boolean save(Person person) {
        person.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        personDAO.save(person);
        return true;
    }

    @Transactional
    public void update(Long  id, Person updatedPerson) {
       personDAO.update(id, updatedPerson);
    }

    @Transactional
    public void delete(Long  id) {
       personDAO.delete(id);
    }

    @Override
    public Person findByName(String username) {
        return personDAO.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDAO.findByName(username);

        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return person;
    }
}
