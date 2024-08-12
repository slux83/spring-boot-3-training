package fr.slux.training.springboot.restapi.user;

import fr.slux.training.springboot.restapi.jpa.PostRepository;
import fr.slux.training.springboot.restapi.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    private UserRepository userRepository;

    private PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return this.userRepository.findAll();
    }

    // links are added via Spring Hateaos (via Entity Model)
    @GetMapping(path = "/jpa/users/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("Can't find the user with ID " + userId);

        EntityModel<User> entityModel = EntityModel.of(user.get());
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

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);

        // Adds HTTP location of the new added user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        // This will return a HTTP 201 (created)
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        this.userRepository.deleteById(userId);
    }

    @GetMapping(path = "/jpa/users/{userId}/posts")
    public List<Post> retrievePostForUser(@PathVariable Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("Can't find the user with ID " + userId);
        return user.get().getPosts();
    }


    @PostMapping(path = "/jpa/users/{userId}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int userId,
                                                  @Valid @RequestBody Post post) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("Can't find the user with ID " + userId);

        post.setUser(user.get());
        Post savedPost = this.postRepository.save(post);

        // Adds HTTP location of the new added user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedPost.getId()).toUri();

        // This will return a HTTP 201 (created)
        return ResponseEntity.created(location).build();
    }

}
