package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Person;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAOImp implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> index() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role show(Long  id) {
        Query query =  entityManager.createQuery("from Role where id = :id");
        query.setParameter("id", id);
        return (Role) query.getSingleResult();
    }

    @Override
    public void save(Role role) {
        entityManager.merge(role);
        entityManager.flush();
    }

    @Override
    public void delete(Long  id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

}
