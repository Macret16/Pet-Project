package Application.Repositories;

import java.util.Optional;

import Application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
//    User findByUsername(String username); // Consider changing this to List<User> if necessary
}
