package com.BlogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(String name);
}
