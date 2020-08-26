package com.todo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

}
