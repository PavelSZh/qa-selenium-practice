import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TestCalculators {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void preparations () {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://calculator888.ru/kalkulator-drobey");
    }

    @Test
    public void calcTest (){
        WebElement firstSign = driver.findElement(By.cssSelector("#celoe_1"));
        firstSign.click();
        firstSign.sendKeys("-");

        WebElement firstDividend = driver.findElement(By.cssSelector("#chslt_1"));
        firstDividend.click();
        firstDividend.sendKeys("3");

        WebElement firstDivider = driver.findElement(By.cssSelector("#znamn_1"));
        firstDivider.click();
        firstDivider.sendKeys("4");

        WebElement secondSign = driver.findElement(By.cssSelector("#celoe_2"));
        secondSign.click();
        secondSign.sendKeys("+");

        WebElement secondDividend = driver.findElement(By.cssSelector("#chslt_2"));
        secondDividend.click();
        secondDividend.sendKeys("1");

        WebElement secondDivider = driver.findElement(By.cssSelector("#znamn_2"));
        secondDivider.click();
        secondDivider.sendKeys("2");

        WebElement calculate = driver.findElement(By.cssSelector("#korpus_calcul > div.knop_dstv_calc > div.knop_ich_1 > input"));
        calculate.click();

        WebElement resultSign = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#otvt_obch > table > tbody > tr:nth-child(1) > td.znak_dest_rvno")));
        WebElement resultDividend = driver.findElement(By.cssSelector("#otvt_obch > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
        WebElement resultDivider = driver.findElement(By.cssSelector("#otvt_obch > table > tbody > tr:nth-child(3) > td"));

        Assert.assertEquals(resultSign.getText(), "- ");
        Assert.assertEquals(resultDividend.getText(), " 1 ");
        Assert.assertEquals(resultDivider.getText(), " 4 ");
    }

    @Test
    public void actionTest() {

        WebElement calculate = driver.findElement(By.xpath("//*[@id='korpus_calcul']/div[3]/div[1]/input"));
        calculate.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='psgv_resn']/table/tbody/tr[1]/td/strong")));

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='psgv_resn']/table/tbody/tr[1]/td/strong")).getText(),
                "Ошибки в задинии:");
    }

    @AfterTest
    public void cleanEverything() {
        if (driver != null) {
            driver.quit();
        }
    }
}
