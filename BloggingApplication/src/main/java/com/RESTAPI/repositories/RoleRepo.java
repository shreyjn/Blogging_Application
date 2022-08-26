package com.RESTAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RESTAPI.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
