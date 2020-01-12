package com.ds.Assignment3.pillDispenser.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PillDispenserService {

  private final ManagedChannel channel;
  private final MedPlanGrpc.MedPlanBlockingStub blockingStub;

  public PillDispenserService(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build()
    );
  }

  private PillDispenserService(ManagedChannel channel) {
    this.channel = channel;
    blockingStub = MedPlanGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    }

  public MedPlansList fetchMedicationPlans(LocalDateTime simulatedTime) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println(simulatedTime);
    DateMessage dateMessage = DateMessage.newBuilder().setCurrentDate(simulatedTime.format(formatter)).build();
    MedPlansList medPlansList = null;
    try {
      medPlansList = blockingStub.fetchMedPlans(dateMessage);
    } catch (StatusRuntimeException e) {
      e.printStackTrace();
    }

    return medPlansList;

  }

  public void procActions(String type, List<String> reports){
    List<DayReport> dayReports = new ArrayList<>();
    for(String rep: reports){
      dayReports.add(DayReport.newBuilder().setContent(rep).build());
    }
    MedicationPlansReport report = MedicationPlansReport.newBuilder().setType(type).addAllReports(dayReports)
                                                                                      .build();
    try {
     blockingStub.processActions(report);
    } catch (StatusRuntimeException e) {
      return;
    }

  }

}
