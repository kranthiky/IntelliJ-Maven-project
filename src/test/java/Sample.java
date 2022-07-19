import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample {
    WebDriver driver;
@Test(priority = 1)
    public void launchBrowser() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        Thread.sleep(3000);
       // driver.quit();
         System.out.println("Browser launched successfully");
    }
    @Test(priority = 2)
    public void searchGoogle() throws InterruptedException {

        WebElement txt_search = driver.findElement(By.name("q"));
        txt_search.sendKeys("IntelliJ");
        txt_search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        Assert.assertTrue(driver.getPageSource().contains("IntelliJ"));
        System.out.println("Google search is successful!");
        driver.quit();
    }
}
