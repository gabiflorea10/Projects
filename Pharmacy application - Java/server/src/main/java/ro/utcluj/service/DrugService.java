package ro.utcluj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.api.DrugServiceInterface;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.mapper.DrugInfoMapper;
import ro.utcluj.model.Drug;
import ro.utcluj.notification.NotificationService;
import ro.utcluj.repositories.DrugRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class DrugService implements DrugServiceInterface {

    private DrugRepository drugRepository;
    private DrugInfoMapper drugInfoMapper;

    private NotificationService notificationService;

    @Autowired
    public DrugService(DrugRepository drugRepository, DrugInfoMapper drugInfoMapper, NotificationService notificationService) {
        this.drugRepository = drugRepository;
        this.drugInfoMapper = drugInfoMapper;
        this.notificationService = notificationService;
    }

    public List<DrugInfoDTO> findAllObservable(){
        List<Drug> lista = drugRepository.findAll();

        Date today = new Date(System.currentTimeMillis());

        List<DrugInfoDTO> dtoDrugs = drugInfoMapper.mapAll(lista);
        List<DrugInfoDTO> dtoDrugsForReturn = new ArrayList<>();

        for(DrugInfoDTO drug: dtoDrugs){

            long timeDifference = Math.abs(today.getTime() - drug.getTermofvalidity().getTime());
            long daysBetween = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

            if(daysBetween == 3 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.9)));
            }
            else if(daysBetween == 2 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.8)));
            }
            else if(daysBetween == 1 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.65)));
            }
            else if(daysBetween == 0 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.5)));
            }
            else if(!(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(drug);
            }

        }
        return dtoDrugsForReturn;
    }

    public List<DrugInfoDTO> findAllBySymptom(String symptom){
        List<Drug> lista = drugRepository.findAllByUsefulfor(symptom);

        Date today = new Date(System.currentTimeMillis());

        List<DrugInfoDTO> dtoDrugs = drugInfoMapper.mapAll(lista);
        List<DrugInfoDTO> dtoDrugsForReturn = new ArrayList<>();

        for(DrugInfoDTO drug: dtoDrugs){

            long timeDifference = Math.abs(today.getTime() - drug.getTermofvalidity().getTime());
            long daysBetween = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

            if(daysBetween == 3 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.9)));
            }
            else if(daysBetween == 2 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.8)));
            }
            else if(daysBetween == 1 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.65)));
            }
            else if(daysBetween == 0 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.5)));
            }
            else if(!(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(drug);
            }

        }
        return dtoDrugsForReturn;
    }

    public List<DrugInfoDTO> findAllByName(String nameOfDrug){
        List<Drug> lista = drugRepository.findAllByName(nameOfDrug);

        Date today = new Date(System.currentTimeMillis());

        List<DrugInfoDTO> dtoDrugs = drugInfoMapper.mapAll(lista);
        List<DrugInfoDTO> dtoDrugsForReturn = new ArrayList<>();

        for(DrugInfoDTO drug: dtoDrugs){

            long timeDifference = Math.abs(today.getTime() - drug.getTermofvalidity().getTime());
            long daysBetween = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

            if(daysBetween == 3 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.9)));
            }
            else if(daysBetween == 2 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.8)));
            }
            else if(daysBetween == 1 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.65)));
            }
            else if(daysBetween == 0 && !(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(new DrugInfoDTO(drug.getIddrug(), drug.getName(), drug.getProducer(), drug.getUsefulfor(), drug.getTermofvalidity(), (int)(drug.getPrice()*0.5)));
            }
            else if(!(drug.getTermofvalidity().before(today))){
                dtoDrugsForReturn.add(drug);
            }

        }
        return dtoDrugsForReturn;
    }

    public String addDrug(String name, String producer, String useful, Date validity, Integer price){
        Drug drug = new Drug(name, producer, useful,validity, price);
        drugRepository.save(drug);
        notificationService.sendMessageToAllClients("The drug " +  drug.getName() + " was added!");
        return "created";
    }

    public Integer updateDrug(Integer currentDrugId, String name, String producer, String useful, Date validity, Integer price){
        Drug drug = drugRepository.getOne(currentDrugId);
        drug.setName(name);
        drug.setProducer(producer);
        drug.setUsefulfor(useful);
        drug.setTermofvalidity(validity);
        drug.setPrice(price);
        return 1;
    }

    public String deleteDrug(Integer idDrug){
        drugRepository.deleteById(idDrug);
        return "Deleted";
    }


}
