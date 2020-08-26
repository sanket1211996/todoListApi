package com.todo.api.comman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.model.User;
import com.todo.api.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signUp")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User _user = userRepository.save(new User(user.getUsername(), user.getPassword(), "ACCESS_PUBLIC", "USER", user.getEmail()));
		if(_user != null) {
			return new ResponseEntity<>(_user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(_user, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userRepository.findAll();
		if(users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
