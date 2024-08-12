package fr.slux.training.springboot.restapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "Alessio", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Linus", LocalDate.now().minusYears(45)));
        users.add(new User(++userCount, "Elon", LocalDate.now().minusYears(52)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(Integer id) {
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.orElse(null);
    }

    public void deleteById(Integer id) {
        users.removeIf(u -> u.getId().equals(id));
    }


}
