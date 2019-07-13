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
import ro.utcluj.notification.NotificationService;
import ro.utcluj.repositories.DrugRepository;
import ro.utcluj.service.DrugService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DrugServiceTest {


    @Mock private DrugRepository drugRepository;
    @Mock private DrugInfoMapper drugInfoMapper;
    @Mock private NotificationService notificationService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private DrugService drugService;
    private Drug drug;

    @Before
    public void setup(){
        drugService = new DrugService(drugRepository, drugInfoMapper, notificationService);
        drug = new Drug("name", "prducer", "usefulfor", Date.valueOf("2019-07-07"), 100);

    }

    @Test
    public void findAllObservableTest(){
        when(drugRepository.findAll()).thenReturn(new ArrayList<>());
        when(drugInfoMapper.mapAll(anyList())).thenReturn(new ArrayList());

        List<DrugInfoDTO> returnedList = drugService.findAllObservable();

        Assert.assertEquals(new ArrayList<>(),returnedList);

        verify(drugRepository,times(1)).findAll();
        verify(drugInfoMapper,times(1)).mapAll(anyList());

    }

    @Test
    public void findAllBySymptomTest(){
        when(drugRepository.findAllByUsefulfor(anyString())).thenReturn(new ArrayList<>());
        when(drugInfoMapper.mapAll(anyList())).thenReturn(new ArrayList());

        List<DrugInfoDTO> returnedList = drugService.findAllBySymptom(anyString());

        Assert.assertEquals(new ArrayList<>(),returnedList);

        verify(drugRepository,times(1)).findAllByUsefulfor(anyString());
        verify(drugInfoMapper,times(1)).mapAll(anyList());

    }

    @Test
    public void findAllByNameTest(){
        when(drugRepository.findAllByName(anyString())).thenReturn(new ArrayList<>());
        when(drugInfoMapper.mapAll(anyList())).thenReturn(new ArrayList());

        List<DrugInfoDTO> returnedList = drugService.findAllByName(anyString());

        Assert.assertEquals(new ArrayList<>(),returnedList);

        verify(drugRepository,times(1)).findAllByName(anyString());
        verify(drugInfoMapper,times(1)).mapAll(anyList());

    }

    @Test
    public void addDrugTest(){
        when(drugRepository.save(any(Drug.class))).thenReturn(drug);

        String returnedString = drugService.addDrug("name", "producer", "useful", Date.valueOf("2019-07-07"), 50);

        Assert.assertEquals(returnedString,"created");
        verify(drugRepository,times(1)).save(any(Drug.class));

    }

    @Test
    public void updateDrugTest(){
        when(drugRepository.getOne(anyInt())).thenReturn(drug);

        int returnedInt = drugService.updateDrug(2,"name", "producer", "useful", Date.valueOf("2019-07-07"), 50);

        Assert.assertEquals(returnedInt, 1);

        verify(drugRepository,times(1)).getOne(anyInt());

    }

    @Test
    public void deleteDrugTest(){
        String returnedString = drugService.deleteDrug(2);
        Assert.assertEquals(returnedString, "Deleted");
        verify(drugRepository,times(1)).deleteById(2);
    }

}
