package fr.slux.training.springboot.restapi.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return this.userDaoService.findAll();
    }

    // links are added via Spring Hateaos (via Entity Model)
    @GetMapping(path = "/users/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable Integer userId) {
        User user = this.userDaoService.findOne(userId);
        if (user == null)
            throw new UserNotFoundException("Can't find the user with ID " + userId);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        /*
         *
         * response will look like this now
         * <pre>
         *     {
         *          "id": 2,
         *          "name": "Linus",
         *          "birthDate": "1979-07-18",
         *          "_links": {
         *              "all-users": {
         *                  "href": "http://localhost:8080/users"
         *              }
         *          }
         *     }
         </pre>
         */
        return entityModel;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userDaoService.save(user);

        // Adds HTTP location of the new added user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        // This will return a HTTP 201 (created)
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        this.userDaoService.deleteById(userId);
    }
}
