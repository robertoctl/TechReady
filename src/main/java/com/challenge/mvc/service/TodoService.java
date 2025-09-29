package com.challenge.mvc.service;

import com.challenge.mvc.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public List<Todo> list() {
        return new ArrayList<>(store.values());
    }

    public Todo create(Todo input) {
        long id = seq.incrementAndGet();
        Todo saved = new Todo(id, input.getText(), false);
        store.put(id, saved);
        return saved;
    }

    public Optional<Todo> toggle(Long id) {
        Todo t = store.get(id);
        if (t == null) return Optional.empty();
        t.setDone(!t.isDone());
        return Optional.of(t);
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
