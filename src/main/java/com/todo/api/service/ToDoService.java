package com.todo.api.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.todo.api.model.ToDo;
import com.todo.api.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	public List<ToDo> findAll() {
		return toDoRepository.findAll();
	}
	
	public Optional<ToDo> addTodo(ToDo toDo) {
		return Optional.of(toDoRepository.save(toDo));
	}
	
	public Optional<ToDo> updateToDo(ToDo toDo, Long id) {
		
		Optional<ToDo> optToDo = toDoRepository.findById(id);
		
		if(optToDo.isPresent()) {
			ToDo _toDo = optToDo.get();
			_toDo.setTitle(toDo.getTitle());
			_toDo.setDescription(toDo.getDescription());
			return Optional.of(toDoRepository.save(_toDo));
		}
		
		return Optional.empty();
	}
	
	public boolean deleteToDo(Long id) {
		
		if(toDoRepository.existsById(id)) {
			toDoRepository.deleteById(id);
			return true;
		}
		
		return false;

	}

	public Optional<ToDo> findByID(Long id) {
		return toDoRepository.findById(id);
	}

	public List<ToDo> findAllByUerID(Long userID) {
		return toDoRepository.findAllByUserID(userID);
	}
	
	
	@Async
	public CompletableFuture<String> getAsyncData() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  CompletableFuture.completedFuture(Thread.currentThread().getName()); 
	}
	

}
