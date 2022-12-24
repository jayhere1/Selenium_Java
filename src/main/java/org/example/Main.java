package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();


        String baseUrl = "http://books.toscrape.com/";

        driver.get(baseUrl);

        System.out.println(driver.findElements(By.xpath("//li[@class='next']/a")));

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='next']/a")));
        List books = new ArrayList<>();

        Thread.sleep(2000);
        List booksList = null;
        for (int i = 0; i <= 50; i++) {
            WebElement lnkNextBtn = driver.findElement(By.xpath("//li[@class='next']/a"));
            clickNextBtn(lnkNextBtn);
            System.out.println(driver.getCurrentUrl());
            booksList = getBookName(driver, books);

        }
        System.out.println(booksList);
        Thread.sleep(5000);
        System.out.println("Closing");

        driver.quit();

    }
    public static void  clickNextBtn( WebElement button) throws InterruptedException {
        button.click();
        Thread.sleep(2000);
        System.out.println("Clicking Next");

    }
    public static List getBookName(WebDriver driver, List books){
        List<WebElement> allBooks = driver.findElements(By.tagName("h3"));
        for(WebElement link:allBooks){
            System.out.println(link.getText());
            books.add(link.getText());
        }
        System.out.println(books);
        return books;

    }
}