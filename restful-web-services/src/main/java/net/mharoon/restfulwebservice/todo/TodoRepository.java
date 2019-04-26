package net.mharoon.restfulwebservice.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	//List<Todo> findAll();
	Todo findByIdAndUsername(long id, String username);
}
