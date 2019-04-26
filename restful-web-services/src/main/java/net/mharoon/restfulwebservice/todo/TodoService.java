package net.mharoon.restfulwebservice.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoService {
		
	@Autowired
	private TodoRepository rep;
	
	public List<Todo> findAll(String username){
		return rep.findAll();
	}
	
	public Todo deleteByIdAndUsername(long todo_id, String username) {
		Todo todo = rep.findByIdAndUsername(todo_id, username);
		if (todo != null) {
			rep.delete(todo); 
			return todo;
		} 
		return null;
	}
	
	public Todo updateByIdAndUsername(Todo t) {
		rep.save(t);
		return t;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			rep.save(todo);
		} else {
			//deleteByIdAndUsername(todo.getId(), todo.getUsername());
			//rep.save(todo);
			updateByIdAndUsername(todo);
		}
		return todo;
	}
	
	public Todo addTodoItem(Todo t) {
		return rep.save(t);
	}
	
	public Todo updateByIdAndUsername(Long id,String username, Todo t) {
		Todo todo = rep.findByIdAndUsername(id, username);
		if (todo.getId() !=null && todo.getUsername() != null ) {
			todo.setDescription(t.getDescription());
			todo.setIsDone(t.getIsDone());
			todo.setTargetDate(t.getTargetDate());
			return rep.save(todo);
		}
		return null;
		
	}

	public Todo findByIdAndUsername(long todo_id, String username) {
		return rep.findByIdAndUsername(todo_id, username);
	}

	/*
	public String deleteByIdAndUsername(Long todo_id, String username) {
		Todo todo = rep.findByIdAndUsername(todo_id, username);
		if (todo.getId() != null && todo.getUsername()!= null) {
			rep.delete(todo);
			return "Item "+String.valueOf(todo_id)+" for "+username+" deleted Successfully";
		} else {
			return "Error: Item No :"+String.valueOf(todo_id)+"not found for user: "+username; 
		}
	}*/
	
}
