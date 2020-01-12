package com.ds.Assignment2.producer.service;

import com.ds.Assignment2.producer.entity.SensorMonitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileParser {

    private static SensorMonitor splitLine(String line) throws ParseException {
        StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");

        String start_date = stringTokenizer.nextToken();
        String end_date = stringTokenizer.nextToken();
        String activity_label = stringTokenizer.nextToken();

        Date date_start_date;
        Date date_end_date;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_start_date = format.parse(start_date);
        date_end_date = format.parse(end_date);

        return new SensorMonitor(UUID.randomUUID(), date_start_date, date_end_date, activity_label);

    }

    public static List<SensorMonitor> readFile(){

        BufferedReader bufferedReader;
        List<SensorMonitor> sensorMonitors = new ArrayList<>();
        SensorMonitor sensorMonitor;
        try {
            bufferedReader = new BufferedReader(new FileReader("activity.txt"));
            String line = bufferedReader.readLine();

            while(line != null){
                sensorMonitor = splitLine(line);
                sensorMonitors.add(sensorMonitor);
                line = bufferedReader.readLine();
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return sensorMonitors;
    }
}
