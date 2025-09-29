package com.challenge.mvc.web;

import com.challenge.mvc.model.Todo;
import com.challenge.mvc.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service;
    public TodoController(TodoService service) { this.service = service; }

    @GetMapping
    public List<Todo> list() {
        return service.list();
    }

    @PostMapping
    public Todo create(@RequestBody Todo input) {
        return service.create(input);
    }

    @PostMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggle(@PathVariable Long id) {
        return service.toggle(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
