package web.dao;


import web.model.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> index();
    Role show(Long  id);
    void save(Role role);
    void delete(Long  id);
}
