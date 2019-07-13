package ro.utcluj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.model.Drug;


import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    List<Drug> findAllByUsefulfor(String usefulfor);
    List<Drug> findAllByName(String name);
    Drug findByIddrug(Integer iddrug);
}
