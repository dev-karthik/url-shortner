package com.mylabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylabs.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
