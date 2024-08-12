package fr.slux.training.springboot.restapi.jpa;

import fr.slux.training.springboot.restapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
