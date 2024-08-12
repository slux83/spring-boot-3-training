package fr.slux.training.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggerUsername();
        List<Todo> todos = this.todoService.findByUser(username);
        model.put("todos", todos);
        return "listTodosView";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        model.put("todo", new Todo(0, getLoggerUsername(), "", LocalDate.now(), false));
        return "addTodoView";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(
            ModelMap model,
            @Valid Todo todo,
            BindingResult result) {

        if (result.hasErrors()) {
            return "addTodoView";
        }
        this.todoService.addNewTodo(getLoggerUsername(), todo.getDescription(), LocalDate.now(), false);
        return "redirect:list-todos";
    }

    private String getLoggerUsername() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        return user.getName();
    }
}
