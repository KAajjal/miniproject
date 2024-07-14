package StepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.testng.annotations.Test;
import java.time.Duration;
import Pages.*;

public class PopularPageSteps {
    public WebDriver driver;
    PopularPage popularPage;
    LoginPage loginPage;
    HeaderSectionPage headerSectionPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ASUS\\\\Downloads\\\\chromedriver-win32\\\\chromedriver-win32\\\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Given("user is on popular page")
    public void userISOnPopularPage(){
        driver.get("https://qamoviesapp.ccbp.tech");
        headerSectionPage = new HeaderSectionPage(driver);
        loginPage = new LoginPage(driver);
        popularPage = new PopularPage(driver);


        loginPage.loginApplication("rahul", "rahul@2021");
        String actualUrl = "https://qamoviesapp.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(actualUrl));

        headerSectionPage.clickPopularNavbar();
        String actualPopularUrl = "https://qamoviesapp.ccbp.tech/popular";

        wait.until(ExpectedConditions.urlToBe(actualPopularUrl));

    }

    @Then("movies are displayed on popular page")
    public void checkMoviesDisplay(){
        Assert.assertTrue(popularPage.getAlLMoviesList().isDisplayed(),"Movies are not displayed");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}