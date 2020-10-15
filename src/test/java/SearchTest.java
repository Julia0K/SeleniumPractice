import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {

    public static WebDriver driver;

    @BeforeAll
    public static void chromeSetup() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");
    }

    @Test
    @Order(1)
    public void mainPageVerification() {

        assertEquals("Google", driver.getTitle());
    }

    @Test
    @Order(2)
    public void searchFieldTest() {
        WebElement searchField = driver.findElement(By.xpath("//input[@name=\"q\"]"));
        searchField.sendKeys("News");
        searchField.sendKeys(Keys.ENTER);
        WebElement resultsText = driver.findElement(By.xpath("//div[@id=\"result-stats\"]"));

        Assertions.assertTrue(resultsText.isDisplayed());
    }

    @Test
    @Order(3)
    public void searchInTabNews() {
        WebElement searchField = driver.findElement(By.xpath("//input[@name=\"q\"]"));
        searchField.sendKeys("News");
        searchField.sendKeys(Keys.ENTER);
        WebElement newsTab = driver.findElement(By.xpath("//body/div[@id='main']/div[@id='cnt']/div[@id='top_nav']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
        newsTab.click();
        WebElement searchResult = driver.findElement(By.xpath("//div[contains(text(),'About')]"));

        Assertions.assertTrue(searchResult.isDisplayed());
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("After All cleanUp() method called");
        driver.quit();
    }
}
