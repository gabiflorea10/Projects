package ro.utcluj.reportManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ro.utcluj.api.DrugServiceInterface;
import ro.utcluj.api.OrderServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.OrderInfoDTO;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    private OrderServiceInterface orderService;
    private ApplicationContext applicationContext;
    private ReportFactory reportFactory;

    @Autowired
    public ReportService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.orderService = applicationContext.getBean(OrderServiceInterface.class);
        this.reportFactory = new ReportFactory();
    }

    public void createReport(Date startDate, Date endDate, String reportType, String pathToSave){
        List<OrderInfoDTO> ordersUD = orderService.findAllOrders();

        HashMap<String, Integer> bestSellings = new HashMap<>();
        for(OrderInfoDTO order: ordersUD){
            if (order.getOrderdate().compareTo(startDate)>0 && order.getOrderdate().compareTo(endDate)<0) {
                bestSellings.put(order.getDrugInfoDTO().getName(), bestSellings.getOrDefault(order.getDrugInfoDTO().getName(), 0) + 1);
            }
        }

        Map<String, Integer> sortedMap = bestSellings
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        reportFactory.getProperReport(reportType,pathToSave).showReport(sortedMap);

    }

}
