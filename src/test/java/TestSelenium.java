import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestSelenium {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

    @BeforeMethod
    public void baseURL(){
        driver.get("https://thecode.media/good-tester/");
        driver.manage().window().maximize();
    }

    @Test
    public void sTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Тренажёры для тестирования: подборка сайтов, где тестировщики могут проверить свои силы — Журнал «Код»");
    }

    @Test
    public void sTest1 (){
        driver.findElement(By.cssSelector("#body > article > div:nth-child(1) > div.article-header > div.article-header-top > div > div.article-info-top > div.df > div > div > a"));
        Assert.assertEquals(driver.findElement(By.cssSelector("#body > article > div:nth-child(1) > div.article-header > div.article-header-top > div > div.article-info-top > div.df > div > div > a")).getText(), "easy");
    }

    @Test
    public void sTest2(){
        WebElement easy = driver.findElement(By.cssSelector("#body > article > div:nth-child(1) > div.article-header > div.article-header-top > div > div.article-info-top > div.df > div > div > a"));
        easy.click();
        Assert.assertEquals(driver.getTitle(), "Проекты и задачки уровня easy — Журнал «Код» программирование без снобизма");
    }

    @Test
    public void sTest3(){
        WebElement topics = driver.findElement(By.cssSelector("#body > article > div:nth-child(1) > div.article-header > div.article-header-top > div > div.article-info-top > div.df > button > div.icon-unsubscribed > svg"));
        topics.click();
        WebElement yandex = driver.findElement(By.cssSelector("#body > div.login-popup.login-popup--visible > div > div.login-popup__btn > div > div.wp-social-login-provider-list > a"));
        yandex.click();

    }

    @AfterMethod
    public void cleanAfterYourself(){
        driver.quit();
    }
}
