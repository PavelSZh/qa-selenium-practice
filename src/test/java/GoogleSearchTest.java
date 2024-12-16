import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class GoogleSearchTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeMethod
    public void preparations (){
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void dropEverything(){
        driver.findElement(By.xpath("//*[@id='content']/ul/li[11]/a")).click();

        WebElement list = driver.findElement(By.xpath("//*[@id = 'dropdown']"));
        Select option = new Select(list);
        option.selectByVisibleText("Option 2");
    }

    @Test
    public void dragAndDropEverything(){
        driver.findElement(By.xpath("//*[@id='content']/ul/li[10]/a")).click();

        WebElement a = driver.findElement(By.id("column-a"));
        WebElement b = driver.findElement(By.id("column-b"));

        Actions drag = new Actions(driver);
        drag.dragAndDrop(a, b).perform();
    }

    @Test
    public void contextRight (){
        driver.findElement(By.xpath("//*[@id='content']/ul/li[7]/a")).click();

        driver.findElement(By.id("hot-spot"));

        Actions click = new Actions(driver);
        click.contextClick().perform();

    }

    @Test
    public void uploading (){
        driver.findElement(By.xpath("//*[@id='content']/ul/li[18]/a")).click();

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys("C:\\Users\\User\\Desktop\\Cart.csv");
        driver.findElement(By.id("file-submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/h3")));

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText(), "File Uploaded!");
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "Cart.csv");
    }

    @Test
    public void hovers (){
        driver.findElement(By.xpath("//*[@id='content']/ul/li[25]/a")).click();

        WebElement image = driver.findElement(By.xpath("//*[@id='content']/div/div[1]/img"));
        Actions hover = new Actions(driver);
        hover.moveToElement(image).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div[1]/div")));

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/h5")).getText(), "name: user1");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/a")).getText(), "View profile");
    }

    @AfterTest
    public void close (){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        driver.quit();
    }
}
