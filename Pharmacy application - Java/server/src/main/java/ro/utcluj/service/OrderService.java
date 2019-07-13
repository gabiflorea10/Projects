package ro.utcluj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.api.OrderServiceInterface;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.mapper.OrderInfoMapper;

import ro.utcluj.model.Drug;
import ro.utcluj.model.User;
import ro.utcluj.model.UserDrug;
import ro.utcluj.repositories.DrugRepository;
import ro.utcluj.repositories.OrderRepository;
import ro.utcluj.repositories.UserRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class OrderService implements OrderServiceInterface {

    private OrderRepository orderRepository;
    private DrugRepository drugRepository;
    private UserRepository userRepository;
    private OrderInfoMapper orderInfoMapper;
    private SecurityContextUsernameGet securityContextUsernameGet;

    @Autowired
    public OrderService(OrderRepository orderRepository, DrugRepository drugRepository, UserRepository userRepository, OrderInfoMapper orderInfoMapper, SecurityContextUsernameGet securityContextUsernameGet) {
        this.orderRepository = orderRepository;
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
        this.orderInfoMapper = orderInfoMapper;
        this.securityContextUsernameGet = securityContextUsernameGet;
    }

    private static User currentUser = new User();


    public List<OrderInfoDTO> findAllByUser(){
         String contextUserName = securityContextUsernameGet.getUserNameFromContext();
         User user = userRepository.findByUsername(contextUserName);
         currentUser = user;
         List<UserDrug> lista = orderRepository.findByUser(user);
         return orderInfoMapper.mapAll(lista);
    }

    public String buyDrug(Integer idOfDrug, String prescriptionName, String prescriptionDrug, String prescriptionType){

        String returnedString;

        User user = userRepository.getOne(currentUser.getIduser());
        Drug currentDrug = drugRepository.getOne(idOfDrug);

        if(currentDrug == null){
            returnedString = "Invalid drug id!";
            return returnedString;
        }

        Drug currentDrugForBuy = null;
        Date today = new Date(System.currentTimeMillis());

        long timeDifference = Math.abs(today.getTime() - currentDrug.getTermofvalidity().getTime());
        long daysBetween = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

        if(daysBetween == 3 && !currentDrug.getTermofvalidity().before(today)){
            currentDrugForBuy = new Drug(currentDrug.getName(), currentDrug.getProducer(), currentDrug.getUsefulfor(), currentDrug.getTermofvalidity(), (int)(currentDrug.getPrice()*0.9));
            currentDrugForBuy.setIddrug(currentDrug.getIddrug());
        }
        else if(daysBetween == 2 && !currentDrug.getTermofvalidity().before(today)){
            currentDrugForBuy = new Drug(currentDrug.getName(), currentDrug.getProducer(), currentDrug.getUsefulfor(), currentDrug.getTermofvalidity(), (int)(currentDrug.getPrice()*0.8));
            currentDrugForBuy.setIddrug(currentDrug.getIddrug());
        }
        else if(daysBetween == 1 && !currentDrug.getTermofvalidity().before(today)){
            currentDrugForBuy = new Drug(currentDrug.getName(), currentDrug.getProducer(), currentDrug.getUsefulfor(), currentDrug.getTermofvalidity(), (int)(currentDrug.getPrice()*0.65));
            currentDrugForBuy.setIddrug(currentDrug.getIddrug());
        }
        else if(daysBetween == 0 && !currentDrug.getTermofvalidity().before(today)){
            currentDrugForBuy = new Drug(currentDrug.getName(), currentDrug.getProducer(), currentDrug.getUsefulfor(), currentDrug.getTermofvalidity(), (int)(currentDrug.getPrice()*0.5));
            currentDrugForBuy.setIddrug(currentDrug.getIddrug());
        }
        else if(!currentDrug.getTermofvalidity().before(today)){
            currentDrugForBuy = new Drug(currentDrug.getName(), currentDrug.getProducer(), currentDrug.getUsefulfor(), currentDrug.getTermofvalidity(), currentDrug.getPrice());
            currentDrugForBuy.setIddrug(currentDrug.getIddrug());
        }

        if(currentDrugForBuy != null) {
            if (currentDrug.getName().equals(prescriptionDrug)) {
                if (prescriptionType.equals("Free")) {
                    returnedString = "Drug bought free!";
                    orderRepository.save(new UserDrug(user, currentDrug, new Date(System.currentTimeMillis())));

                    return returnedString;
                } else if (prescriptionType.equals("Compensated")) {
                    if (user.getMoney() < currentDrugForBuy.getPrice() * 0.8) {
                        returnedString = "Not enough money available!";
                    } else {
                        returnedString = "Drug bought 20% compensated!";
                        orderRepository.save(new UserDrug(user, currentDrug, new Date(System.currentTimeMillis())));
                        userRepository.getOne(user.getIduser()).setMoney(user.getMoney() - (int) (currentDrugForBuy.getPrice() * 0.8));
                    }

                    return returnedString;
                } else {
                    if (user.getMoney() < currentDrugForBuy.getPrice()) {
                        returnedString = "Not enough money available!";
                    } else {
                        returnedString = "Drug bought!";
                        orderRepository.save(new UserDrug(user, currentDrug, new Date(System.currentTimeMillis())));
                        userRepository.getOne(user.getIduser()).setMoney(user.getMoney() - currentDrugForBuy.getPrice());
                    }

                    return returnedString;
                }

            } else {
                if (user.getMoney() < currentDrugForBuy.getPrice()) {
                    returnedString = "Not enough money available!";
                } else {
                    returnedString = "Drug bought!";
                    orderRepository.save(new UserDrug(user, currentDrug, new Date(System.currentTimeMillis())));
                    userRepository.getOne(user.getIduser()).setMoney(user.getMoney() - currentDrugForBuy.getPrice());
                }

                return returnedString;
            }
        }
        else{
            return "Drug out of date!";
        }
    }


    public List<OrderInfoDTO> findAllOrders(){
        List<UserDrug> lista = orderRepository.findAll();
        return orderInfoMapper.mapAll(lista);
    }

}
