package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.mapper.DrugInfoMapper;
import ro.utcluj.model.Drug;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DrugInfoMapperTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private Drug drug;
    private DrugInfoMapper drugInfoMapper;

    @Before
    public void setup(){
        drug = new Drug("name", "prducer", "usefulfor", Date.valueOf("2019-07-07"), 100);
        drugInfoMapper = new DrugInfoMapper();
    }

    @Test
    public void mapTest(){

        DrugInfoDTO drugInfoDTO = drugInfoMapper.map(drug);

        Assert.assertEquals(drug.getName(), drugInfoDTO.getName());

    }

    @Test
    public void mapAllTest(){

        List<DrugInfoDTO> returnedList = drugInfoMapper.mapAll(new ArrayList<>());

        Assert.assertEquals(new ArrayList<>(),returnedList);
    }

}
