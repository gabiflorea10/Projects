package ro.utcluj.reportManager;

public class ReportFactory {

    public Report getProperReport(String reportType, String pathToSaveReport){

        if(reportType.contains("reportPDF")){
            return new ReportPDF(pathToSaveReport);
        }
        else{
            return new ReportTxt(pathToSaveReport);
        }

    }
}
