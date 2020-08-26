package com.todo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.api.model.ToDo;
import com.todo.api.model.User;
import com.todo.api.repository.ToDoRepository;
import com.todo.api.repository.UserRepository;


@Service
public class DbInit implements CommandLineRunner {

	@Autowired	
	ToDoRepository todoRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		

		
		User user = new User("user", passwordEncoder.encode("password"), "ACCESS_PUBLIC","USER","test@nomail.com");
		userRepository.save(user);
		Long userID = userRepository.findByUsername("user").getId();
		ToDo todo = new ToDo(userID,"todo1", "description1");
		todoRepository.save(todo);
		
		user = new User("user2", passwordEncoder.encode("password2"), "ACCESS_PUBLIC","USER","test2@nomail.com");
		userRepository.save(user);
		userID = userRepository.findByUsername("user2").getId();	
		todo = new ToDo(userID,"todo2", "description2");
		todoRepository.save(todo);
		
		
	}

}
