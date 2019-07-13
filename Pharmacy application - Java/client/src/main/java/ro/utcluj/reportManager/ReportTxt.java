package ro.utcluj.reportManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReportTxt implements Report {

    public String pathToSaveReport;

    public ReportTxt(String pathToSaveReport) {
        this.pathToSaveReport = pathToSaveReport;
    }

    @Override
    public void showReport(Map<String, Integer> sortedMap) {

        File file = new File(pathToSaveReport + "/BestSellingDrugs.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                bufferedWriter.write(key+" -> "+ value + "\n\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
