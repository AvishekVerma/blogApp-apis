package com.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
