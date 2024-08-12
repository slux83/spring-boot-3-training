package fr.slux.training.springboot.springsecurity.resources;

import fr.slux.training.springboot.springsecurity.basic.BasicAuthSecurityConfiguration;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {
    private static final Logger LOG = LoggerFactory.getLogger(TodoResource.class);

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos() {
        return getAllTodos();
    }

    @GetMapping("/users/{username}/todos")
    /* use pre- / post-auth because it's the modern approach */
    // the logged-in user can only check his/her todos and not other user ones
    @PreAuthorize("hasRole('USER_ROLE') and #username == authentication.name")
    // It checks that the returned object contains the username alessio in its class field
    @PostAuthorize("returnObject.username == 'alessio'")
    // if you want to allow roles, you need to enable the jsr250Enabled in the configuration class
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    // Spring legacy way, it checks against the authority, so the ROLE_ prefix..
    @Secured({"ROLE_USER_ROLE", "ROLE_ADMIN_ROLE"})
    public Todo retrieveTodosForUser(@PathVariable String username) {
        return getAllTodos().stream()
                .filter(t -> t.username().equals(username))
                .findFirst().get();
    }

    // CSRF by defaults blocks POST and PUT requests.
    // A token needs to be created for each request (synchronized token pattern)
    @PostMapping("/users/{username}/todos")
    public void createTodoForUser(@PathVariable String username,
                                  @RequestBody Todo todo) {
        LOG.info("Creating {} for {}", username, todo);

    }


    private static List<Todo> getAllTodos() {
        return List.of(new Todo("alessio", "learn linux"),
                new Todo("john", "learn MacOS"));
    }
}

record Todo(String username, String description) {
}
