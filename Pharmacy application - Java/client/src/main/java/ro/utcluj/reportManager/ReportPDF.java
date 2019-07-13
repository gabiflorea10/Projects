package ro.utcluj.reportManager;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class ReportPDF implements Report {
    public String pathToSaveReport;

    public ReportPDF(String pathToSaveReport) {
        this.pathToSaveReport = pathToSaveReport;
    }

    @Override
    public void showReport(Map<String, Integer> sortedMap) {

        Document document = new Document( PageSize.A4, 20, 20, 20, 20 );
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pathToSaveReport + "/BestSellingDrugs.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        try {

            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                document.add( new Paragraph( key + " -> " + value ));
                document.add( Chunk.NEWLINE );
                document.add( Chunk.NEWLINE );
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }
}
