package edu.fzu.anop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class AutoTest {

    private static final String DRIVER_PATH = "C:\\IDEA\\chromedriver\\chromedriver.exe";

    @Test
    public void chromeTest() throws IOException {

        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get("localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.tagName("button")).click();

    }
}
