package com.todo.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.api.model.ToDo;
import com.todo.api.repository.ToDoRepository;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {
	
	@Mock
	private ToDoRepository todoRepository;
	
	@Autowired
	ToDoService todoService;

//	@Test
//	void testFindAll() {
//		
//		ToDo todo = new ToDo(1L, "TEST1", "DESC1");
//		List<ToDo> todoList =  new ArrayList<ToDo>();
//		todoList.add(todo);
//		when(todoRepository.findAll()).thenReturn(todoList);
//		assertEquals(todoList,todoService.findAll());
//	}

	@Test
	void testAddTodo() {
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateToDo() {
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteToDo() {
		//fail("Not yet implemented");
	}

	@Test
	void testFindByID() {
		//fail("Not yet implemented");
	}

	@Test
	void testFindAllByUerID() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetAsyncData() {
		//fail("Not yet implemented");
	}

}
