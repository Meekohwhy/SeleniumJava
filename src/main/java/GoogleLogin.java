import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GoogleLogin {
    public static String browser = "firefox";
    public static WebDriver driver;


    public static void main(String[] args) {
        if (browser == "chrome") {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser == "firefox") {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browser == "opera") {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }

        driver.get("https://accounts.google.com/signin");

        driver.findElement(By.id("identifierId")).sendKeys("Your Email"); //Enter E-mail

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
        WebElement password = driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));

        password.sendKeys("Your Password"); // <===enter password

        driver.findElement(By.id("passwordNext")).click();

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.getCurrentUrl();
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedTitle = "Konta Google";
        org.junit.Assert.assertTrue(expectedTitle.equals(actualTitle));
        driver.close();
        System.out.println("Test passed");
    }
}
