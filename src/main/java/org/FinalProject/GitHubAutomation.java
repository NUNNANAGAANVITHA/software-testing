package org.FinalProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GitHubAutomation {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://github.com");
            System.out.println("Opened GitHub homepage");

            WebElement signInButton = driver.findElement(By.linkText("Sign in"));
            highlight(driver, signInButton);
            signInButton.click();
            System.out.println("Clicked 'Sign in' button");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
            highlight(driver, usernameField);
            usernameField.sendKeys("Mahidharchowdary2004");
            System.out.println("Entered username");

            WebElement passwordField = driver.findElement(By.id("password"));
            highlight(driver, passwordField);
            passwordField.sendKeys("Mahidhar@8688131949");
            System.out.println("Entered password");

            WebElement submitButton = driver.findElement(By.name("commit"));
            highlight(driver, submitButton);
            submitButton.click();
            System.out.println("Clicked 'Submit' button");

            try {
                WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='https://avatars.githubusercontent.com/u/116910543?v=4']")));
                highlight(driver, profileIcon);
                System.out.println("Successfully logged in");
                profileIcon.click();

                WebElement yourRepositories = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your repositories")));
                highlight(driver, yourRepositories);
                yourRepositories.click();
                System.out.println("Navigated to 'Your repositories' page");
            } catch (Exception e) {
                System.out.println("Login failed: Credentials are incorrect");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
//            driver.quit();
        }
    }

    private static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}
