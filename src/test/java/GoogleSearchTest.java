import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void preparations() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Test
    public void testDropEverything() {
        driver.findElement(By.xpath("//*[@id='content']/ul/li[11]/a")).click();

        WebElement list = driver.findElement(By.xpath("//*[@id = 'dropdown']"));
        Select option = new Select(list);
        option.selectByVisibleText("Option 2");
    }

    @Test
    public void testDragAndDropEverything() {
        driver.findElement(By.xpath("//*[@id='content']/ul/li[10]/a")).click();

        WebElement a = driver.findElement(By.id("column-a"));
        WebElement b = driver.findElement(By.id("column-b"));

        Actions drag = new Actions(driver);
        drag.dragAndDrop(a, b).perform();
    }

    @Test
    public void testContextRight() {
        driver.findElement(By.xpath("//*[@id='content']/ul/li[7]/a")).click();

        driver.findElement(By.id("hot-spot"));

        Actions click = new Actions(driver);
        click.contextClick().perform();
    }

    @Test
    public void testHovers() {
        driver.findElement(By.xpath("//*[@id='content']/ul/li[25]/a")).click();

        WebElement image = driver.findElement(By.xpath("//*[@id='content']/div/div[1]/img"));
        Actions hover = new Actions(driver);
        hover.moveToElement(image).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div[1]/div")));

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/h5")).getText(), "name: user1");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/a")).getText(), "View profile");
    }

    @AfterTest
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

