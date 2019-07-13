package ro.utcluj.api;

import ro.utcluj.dto.DrugInfoDTO;

import java.sql.Date;
import java.util.List;

public interface DrugServiceInterface {
    List<DrugInfoDTO> findAllObservable();

    List<DrugInfoDTO> findAllBySymptom(String symptom);

    List<DrugInfoDTO> findAllByName(String nameOfDrug);

    String addDrug(String name, String producer, String useful, Date validity, Integer price);

    Integer updateDrug(Integer currentDrugId, String name, String producer, String useful, Date validity, Integer price);

    String deleteDrug(Integer idDrug);
}
