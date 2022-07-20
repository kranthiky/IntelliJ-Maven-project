import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Sample {
   public static WebDriver driver;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
@BeforeTest
public void setup(){
   reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+"extentReport_"+Commons.date+".html");
   extent = new ExtentReports();
   extent.attachReporter(reporter);
}
@Test(priority = 1)
    public void launchBrowser(Method testMethod) throws InterruptedException {

        logger= extent.createTest(testMethod.getName());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        Thread.sleep(3000);
       // driver.quit();
         System.out.println("Browser launched successfully");
         logger.log(Status.PASS,"Browser launched..!");
    }
    @Test(priority = 2)
    public void searchGoogle(Method testMethod) throws InterruptedException {
        logger= extent.createTest(testMethod.getName());
        WebElement txt_search = driver.findElement(By.name("q"));
        txt_search.sendKeys("IntelliJ");
        txt_search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        Assert.assertTrue(driver.getPageSource().contains("IntelliJ"));
        System.out.println("Google search is successful!");

        logger.log(Status.PASS,"Search is successful");
    }
    @AfterTest
    public void flushReport(){
        driver.quit();
        extent.flush();
    }
}
