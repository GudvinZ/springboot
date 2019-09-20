package test.springboot.service;

import test.springboot.model.Role;

import java.util.List;

public interface RoleService {

    boolean addRole(Role role);

    void deleteRoleById(Long id);

//    void deleteAllRoles();

    boolean updateRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);
}
