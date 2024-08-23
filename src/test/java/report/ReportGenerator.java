package report;

import config.EnvironmentConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ReportGenerator {

    public static void main(String[] args) {
        String ruta = new File("").getAbsolutePath() + "/build/reports/cucumber/";
        File reportFolder = new File(ruta + "Forza");

        List<String> jsonList = new ArrayList<>();
        jsonList.add(ruta + "report.json");

        Configuration configuration = new Configuration(reportFolder, EnvironmentConfig.executionName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonList, configuration);
        reportBuilder.generateReports();

        try {
            modifyHtmlToAddLogo(ruta + "Forza/cucumber-html-reports/overview-features.html", "src/test/resources/testdata/logo/"+ EnvironmentConfig.Logo);
            modifyHtmlToAddLogo(ruta + "Forza/cucumber-html-reports/overview-failures.html", "src/test/resources/testdata/logo/"+ EnvironmentConfig.Logo);
            modifyHtmlToAddLogo(ruta + "Forza/cucumber-html-reports/overview-steps.html", "src/test/resources/testdata/logo/"+ EnvironmentConfig.Logo);
            modifyHtmlToAddLogo(ruta + "Forza/cucumber-html-reports/overview-tags.html", "src/test/resources/testdata/logo/"+ EnvironmentConfig.Logo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void modifyHtmlToAddLogo(String htmlFilePath, String imagePath) throws IOException {
        File input = new File(htmlFilePath);

        if (!input.exists()) {
            System.err.println("El archivo no existe: " + input.getAbsolutePath());
            return;
        }

        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        Document doc = Jsoup.parse(input, "UTF-8");
        Element body = doc.selectFirst("body");
        body.prepend("<img src='data:image/png;base64," + encodedString + "' alt='Logo' style='width:300px; height:100px; margin:20px;' />");

        Files.write(Paths.get(input.getPath()), doc.outerHtml().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
