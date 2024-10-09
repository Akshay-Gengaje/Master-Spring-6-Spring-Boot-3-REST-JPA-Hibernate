package com.darklord.school.repository;

import com.darklord.school.model.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles,Integer> {
    Roles getByRoleName(String roleName);
}
