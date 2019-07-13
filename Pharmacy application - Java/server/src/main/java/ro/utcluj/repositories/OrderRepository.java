package ro.utcluj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.model.User;
import ro.utcluj.model.UserDrug;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserDrug, Integer> {
    List<UserDrug> findByUser(User user);
    List<UserDrug> findAll();
}
