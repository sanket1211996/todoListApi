package com.todo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.api.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

	List<ToDo> findAllByUserID(Long userID);

}
