package com.example.Job.Offer.Analyst;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.SocketException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebClientConnector {



    public List<List<String>> getElementsList(String url) throws SocketException {
        List<List<String>> elementsList = new ArrayList<>();

        try {ChromeDriverSingleton.getInstance();
            WebDriver driver = ChromeDriverSingleton.getInstance().getDriver();
            driver.get(url);

            List<WebElement> elementsByType = driver.findElements(By.className("core_o1k33p9j"));
            List<WebElement> elementsByNumber = driver.findElements(By.className("core_o1i02c8p"));

            for (int i = 0; i < 10 && i < elementsByType.size(); i++) {
                WebElement type1 = elementsByType.get(i);
                WebElement number1 = elementsByNumber.get(i);
                String textType = type1.getText();
                String textNumber = number1.getText();
                List<String> elementData = new ArrayList<>();
                elementData.add(textType);
                elementData.add(textNumber);
                elementsList.add(elementData);
            }

        } catch (Exception e) {
            System.err.println("Błąd połączenia" + e.getMessage());
        }

        return elementsList;
    }

    public String formatElementsList(List<List<String>> elementsList) {
        StringBuilder resultList = new StringBuilder();
        for (int i = 0; i < elementsList.size(); i++) {
            List<String> elementData = elementsList.get(i);
            String textType = elementData.get(0);
            String textNumber = elementData.get(1);
            textType = textType.replaceAll("[()]", "");
            textNumber = textNumber.replaceAll("[()]", "");
            resultList.append((i + 1)).append(",").append(textType).append(",").append(textNumber).append("\n");
        }
        return resultList.toString();
    }

    public String scanPageHTMLUnit(String url) throws SocketException {
        List<List<String>> elementsList = getElementsList(url);
        return formatElementsList(elementsList);
    }

}