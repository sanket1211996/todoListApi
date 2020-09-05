package com.todo.api.comman;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.model.ToDo;
import com.todo.api.model.User;
import com.todo.api.repository.UserRepository;
import com.todo.api.service.ToDoService;

@RestController	
@RequestMapping("api")
@CrossOrigin
public class ToDoController {
	
	@Autowired
	ToDoService todoService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/todos")
	public ResponseEntity<List<ToDo>> getTodoList() {
		return new ResponseEntity<>(todoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getCurrentUser(@PathVariable String username) {
		User _user = userRepository.findByUsername(username);
		_user.setPassword("");
		return new ResponseEntity<>(_user,HttpStatus.OK);
	}
	
	@GetMapping("/todos/user/{userID}")
	public ResponseEntity<List<ToDo>> getTodoListByUser(@PathVariable Long userID) {
		return new ResponseEntity<>(todoService.findAllByUerID(userID),HttpStatus.OK);
	}
	
	@GetMapping("/todos/{id}") 
	public ResponseEntity<ToDo> getTodo(@PathVariable Long id) {
		
		  Optional<ToDo> optToDo = todoService.findByID(id);
		  if(optToDo.isPresent())
			  return new ResponseEntity<>(optToDo.get(), HttpStatus.OK);
		  else 
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  
	}
	
	@PostMapping("/todos")
	public ResponseEntity<ToDo> addTodo(@RequestBody ToDo toDo) {
		
		
		  Optional<ToDo> optToDo = todoService.addTodo(toDo);
		  if(optToDo.isPresent())
			  return new ResponseEntity<>(optToDo.get(), HttpStatus.OK);
		  else 
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
	@PutMapping("/todos/{id}") 
	public ResponseEntity<ToDo> updateTodo(@RequestBody ToDo toDo, @PathVariable Long id) {
		
		  Optional<ToDo> optToDo = todoService.updateToDo(toDo, id);
		  if(optToDo.isPresent())
			  return new ResponseEntity<>(optToDo.get(), HttpStatus.OK);
		  else 
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/todos/{id}")
	@CrossOrigin
	public ResponseEntity<HttpStatus> deleteTodo(@PathVariable Long id) {
		
		  boolean isDeleted = todoService.deleteToDo(id);
		  if(isDeleted)
			  return new ResponseEntity<>(HttpStatus.OK);
		  else 
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	


}
