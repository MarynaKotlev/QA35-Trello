package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    WebDriver wd;

    @BeforeMethod
    public void preConditions() {
        if (isLogged()) {
            logOut();
        }

    }

    @Test
    public void loginPositive() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://trello.com/");
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement loginButton = wd.findElement(By.cssSelector("[href='/login']"));
        loginButton.click();

        WebElement emailButton = wd.findElement(By.cssSelector("#user"));
        emailButton.click();
        emailButton.clear();
        emailButton.sendKeys("mkotielieva@gmail.com");

        WebElement attlassianButton = wd.findElement(By.cssSelector("#login"));
        attlassianButton.click();
        pause(5000);

        WebElement passwordInput = wd.findElement(By.cssSelector("#password"));
        passwordInput.click();
        //   passwordInput.clear();
        passwordInput.sendKeys("Mkot594362778");

        WebElement loginSubmit = wd.findElement(By.cssSelector("#login-submit"));
        loginSubmit.click();

        Assert.assertTrue(isLogged());

      //  wd.quit();

    }

    @AfterTest
    public void quit() {
        wd.quit();
    }

    public void pause(int millis) {
       try{
           Thread.sleep(millis);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    public boolean isLogged() {
        return wd.findElements(By.cssSelector("[data-test-id='header-member-menu-button']")).size()>0;
    }

    public void logOut() {
        WebElement clickAvatar = wd.findElement(By.cssSelector("[data-test-id='header-member-menu-button']"));
        clickAvatar.click();
        WebElement exit = wd.findElement(By.cssSelector("[data-test-id='header-member-menu-logout']"));
        exit.click();
        WebElement quit = wd.findElement(By.cssSelector("#logout-submit"));
        quit.click();


    }
}
