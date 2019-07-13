package ro.utcluj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByIduser(Integer iduser);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
