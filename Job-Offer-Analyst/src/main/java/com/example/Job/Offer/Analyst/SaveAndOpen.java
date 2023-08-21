package com.example.Job.Offer.Analyst;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.*;

public class SaveAndOpen {
    private FileInputStream myxls;
    private WebClientConnector webClientConnector;

    public SaveAndOpen() throws FileNotFoundException {
        String fileName="Results.xlsx";
        this.myxls= new FileInputStream(fileName);
        this.webClientConnector=new WebClientConnector();
    }

    public String connectWithUserPage() throws SocketException {
        UserCommunication userCommunication=new UserCommunication();
        String url = userCommunication.userURL();
        return webClientConnector.scanPageHTMLUnit(url);
    }
    public String connectWithProvidedPages() throws SocketException {
        StringBuilder result = new StringBuilder();

        for (Pages pageurl : Pages.values()) {
            String pageContent = webClientConnector.scanPageHTMLUnit(pageurl.getUrl());
            if(pageurl.getUrl().contains("java")){
                result.append("Java").append(":\n");
            }
            else if(pageurl.getUrl().contains("python")){
                result.append("Python").append(":\n");
            }
            if(pageurl.getUrl().contains("c%2B%2B")){
                result.append("c++").append(":\n");
            }
            if(pageurl.getUrl().contains("c%23")){
                result.append("c#").append(":\n");
            }
            if(pageurl.getUrl().contains("JavaScript")){
                result.append("JavaScript").append(":\n");
            }

            result.append(pageContent).append("\n");
        }

        return result.toString();
    }
    public String getDate() {
        LocalDate localDate= LocalDate.now();
        return localDate.toString();
    }
    public Document scanPageWithHTMLUNIT(){
       String url = "https://www.pracuj.pl/praca/java;kw";
        try {
            return Jsoup.connect(url).get();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String documentToString(Document document){
        return document.outerHtml();
    }
    public void displayResults(String results) {
        System.out.println(results);
    }

    //poprawne
    public void appendToExcel(String results) throws IOException {
        String sheetName= "Results";
        XSSFWorkbook resultsOffer = new XSSFWorkbook(myxls);
        XSSFSheet worksheet = resultsOffer.getSheetAt(0);
        if(worksheet==null) {
            worksheet=resultsOffer.createSheet(sheetName);
        }

        String[] lines = results.split("\n");

        int rowIndex = worksheet.getLastRowNum() + 1;
        String currentDate = getDate();
        Row dateRow = worksheet.createRow(rowIndex);
        Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("Data as of: " +currentDate);
        rowIndex++;

        XSSFCellStyle numericCellStyle = resultsOffer.createCellStyle();
        numericCellStyle.setDataFormat(resultsOffer.getCreationHelper().createDataFormat().getFormat("0"));

        for (String line : lines) {
            String[] values = line.split(",");
            Row row = worksheet.createRow(rowIndex);

            for (int cellNum = 0; cellNum < values.length; cellNum++) {
                Cell cell = row.createCell(cellNum);
                String value = values[cellNum];
                if (value.startsWith("(") && value.endsWith(")")) {
                    value = value.substring(1, value.length() - 1);
                }

                if (value.matches("\\d+")) { // Check if the value is numeric
                    cell.setCellValue(Double.parseDouble(value));
                    cell.setCellStyle(numericCellStyle);
                } else {
                    cell.setCellValue(value);
                }
            }

            rowIndex++;
        }

        try (FileOutputStream outputStream = new FileOutputStream("Results.xlsx")) {
            resultsOffer.write(outputStream);
        }
        System.out.println("Results appended to Excel.");
    }
    public void openInformation(File file){
        try{
            List<String> command = new ArrayList<>();
            command.add("C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2022.2.3\\bin\\idea64.exe");
            command.add(file.getAbsolutePath());
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.start();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
}

