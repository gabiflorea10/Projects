package com.ds.Assignment4.models.dtos;

import java.util.HashMap;

public class ActivitiesChartDTO {
    public HashMap<String, Double> activitiesMap;

    public ActivitiesChartDTO() {
    }

    public ActivitiesChartDTO(HashMap<String, Double> activitiesMap) {
        this.activitiesMap = activitiesMap;
    }

}
