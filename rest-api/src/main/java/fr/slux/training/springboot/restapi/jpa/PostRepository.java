package fr.slux.training.springboot.restapi.jpa;

import fr.slux.training.springboot.restapi.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
