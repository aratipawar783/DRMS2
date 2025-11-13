package com.example.smartcropapp.soil;

import android.content.Context;
import android.os.Environment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;

public class ReportGenerator {

    public static String generateReport(Context context, String soilType, float confidence) {
        try {
            String filePath = Environment.getExternalStorageDirectory() + "/SoilReport.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("🌱 Soil Health Report"));
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph("1. Soil Type: " + soilType));
            document.add(new Paragraph("2. Model Confidence: " + (confidence * 100) + "%"));
            document.add(new Paragraph("3. General Description: " + Recommendations.getSoilInfo(soilType)));
            document.add(new Paragraph("4. Texture & Structure: Based on " + soilType + " soil characteristics."));
            document.add(new Paragraph("5. Moisture Retention: " + (soilType.equals("Sandy") ? "Low" : "High")));
            document.add(new Paragraph("6. Nutrient Profile: Rich in region-specific minerals."));
            document.add(new Paragraph("7. Soil pH: Varies, generally neutral to slightly acidic."));
            document.add(new Paragraph("8. Organic Matter Requirement: Add compost or manure."));
            document.add(new Paragraph("9. Erosion Risk: Moderate, requires soil conservation."));
            document.add(new Paragraph("10. Suitable Crops: " + String.join(", ", Recommendations.getCrops(soilType))));
            document.add(new Paragraph("11. Unsuitable Crops: Sensitive crops not ideal here."));
            document.add(new Paragraph("12. Fertilizer Recommendation: Balanced NPK with organic manure."));
            document.add(new Paragraph("13. Irrigation Advice: Maintain moderate watering levels."));
            document.add(new Paragraph("14. Improvement Methods: Use soil conditioners & crop rotation."));
            document.add(new Paragraph("15. Final Recommendation: With proper care, " + soilType + " soil can be highly productive."));

            document.close();
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating report.";
        }
    }
}
