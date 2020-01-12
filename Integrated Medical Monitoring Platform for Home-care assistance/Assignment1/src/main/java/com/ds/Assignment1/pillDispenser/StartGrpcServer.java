package com.ds.Assignment1.pillDispenser;

import com.ds.Assignment1.pillDispenser.service.GrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartGrpcServer {

    private static final int port = 8889;

    // for using service instead of rest call
//    @Autowired
//    private GrpcService grpcService;

    @Autowired
    private GrpcService grpcService;

    @Bean
    public void startGrpcCommunication(){
        new Thread(() -> {
            Server server;
            try {
                server = ServerBuilder.forPort(port)
                        //.addService(new GrpcService())
                        .addService(grpcService)
                        .build()
                        .start();
                server.awaitTermination();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
