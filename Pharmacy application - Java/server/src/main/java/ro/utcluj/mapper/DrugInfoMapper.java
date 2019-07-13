package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.model.Drug;
import ro.utcluj.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class DrugInfoMapper {

    public DrugInfoDTO map(Drug drug){
        DrugInfoDTO drugInfoDTO = new DrugInfoDTO();

        drugInfoDTO.setIddrug(drug.getIddrug());
        drugInfoDTO.setName(drug.getName());
        drugInfoDTO.setProducer(drug.getProducer());
        drugInfoDTO.setUsefulfor(drug.getUsefulfor());
        drugInfoDTO.setTermofvalidity(drug.getTermofvalidity());
        drugInfoDTO.setPrice(drug.getPrice());

        return drugInfoDTO;

    }

    public List<DrugInfoDTO> mapAll(List<Drug> drugs){
        List<DrugInfoDTO> dtoDrugs = new ArrayList<>();
        for(Drug drug: drugs){
            dtoDrugs.add(map(drug));
        }
        return dtoDrugs;
    }
}
