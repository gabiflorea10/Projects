package ro.utcluj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.model.Mess;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Mess, Integer> {
    List<Mess> findAll();
}
