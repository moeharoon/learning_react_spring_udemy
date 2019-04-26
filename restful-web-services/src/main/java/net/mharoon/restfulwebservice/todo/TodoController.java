package net.mharoon.restfulwebservice.todo;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/users")

public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping(path = "/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return service.findAll(username);
	}
	
	@GetMapping(path = "/{username}/todos/{todo_id}")
	public Todo getTodoItem(
			@PathVariable String username,
			@PathVariable long todo_id) {
		return service.findByIdAndUsername(todo_id,username);
	}
	
	@DeleteMapping(path = "/{username}/todos/{todo_id}")
	public ResponseEntity<Void> deleteTodoItem(
			@PathVariable String username,
			@PathVariable long todo_id) {
		Todo t = service.deleteByIdAndUsername(todo_id,username);
		if (t!=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(path="/{username}/todos/{todo_id}")
	public  ResponseEntity<Todo> updateTodoItem(
			@PathVariable String username,
			@PathVariable long todo_id,
			@RequestBody Todo todo) {
		//todo.setId(todo_id);
		Todo t =  service.save(todo);
		return new ResponseEntity<Todo>(t, HttpStatus.OK);
	}
	
	@PostMapping(path="/{username}/todos")
	public  ResponseEntity<Void> newTodoItem(
			@PathVariable String username, 
			@RequestBody Todo todo) {
		Todo t =  service.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todo_id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-Auth-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if(!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(req, res);
        }	
	}*/
}


