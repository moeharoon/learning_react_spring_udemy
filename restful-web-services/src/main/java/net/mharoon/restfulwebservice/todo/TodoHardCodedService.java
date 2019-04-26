package net.mharoon.restfulwebservice.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoHardCodedService {
		
	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 0; 
	static {
		todos.add(new Todo((long) ++idCounter,"moe","Learn React2 ",false,new Date()));
		todos.add(new Todo((long) ++idCounter,"moe","Learn Angular 2",false,new Date()));
		todos.add(new Todo((long) ++idCounter,"moe","Learn Java 2",false,new Date()));
		todos.add(new Todo((long) ++idCounter,"moe","Learn React",false,new Date()));
		todos.add(new Todo((long) ++idCounter,"moe","Learn Angular",false,new Date()));
		todos.add(new Todo((long) ++idCounter,"moe","Learn Java",false,new Date()));
	}
	
	public List<Todo> findAll(String username){
		return todos;
	}
	
	public Todo findByIdAndUsername(long todo_id, String username) {
		for(Todo todo:todos) {
			if (todo.getId() == todo_id && todo.getUsername().equals(username)) {
				return todo;
			} 
		}
		return null;
	}
	
	public Todo deleteByIdAndUsername(long todo_id, String username) {
		Todo todo = findByIdAndUsername(todo_id, username);
		if (todo != null) {
			if(todos.remove(todo)) {
				return todo;
			}
		} 
		return null;
	}
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId((long) ++idCounter);
			todos.add(todo);
		} else {
			deleteByIdAndUsername(todo.getId(), todo.getUsername());
			todos.add(todo);
		}
		return todo;
	}
	
	/*public Todo findByIdAndUsername(Long todo_id, String username) {
		Todo todo = rep.findByIdAndUsername(todo_id, username);
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
