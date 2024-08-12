package fr.slux.training.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 0;

    static {
        todos.add(new Todo(++todoCount, "admin", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todoCount, "admin", "Learn DevOps", LocalDate.now().plusYears(2), false));
    }

    public List<Todo> findByUser(String username) {
        return todos.stream()
                .filter(t -> t.getUsername().equals(username))
                .toList();
    }


    public void addNewTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        Todo todo = new Todo(++todoCount, username, description, targetDate, isDone);
        todos.add(todo);
    }
}
