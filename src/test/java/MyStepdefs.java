import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.Math;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;
    private String email, username, password;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        driver = new ChromeDriver();


    }

    @Given("I have open {string} browser")
    public void iHaveOpenBrowser(String browser) {
        DriveCreator creator = new DriveCreator();
        driver = creator.createBrowser(browser);


        driver.get("https://login.mailchimp.com/signup/");
    }


    @Given("enter emailaddress {string}")
    public void enterEmailaddress(String email) {

        if (email.equals("newEmail")) {

            int min = 100;
            int max = 1000;
            int range = max - min + 1;

            int myRands = (int) (Math.random() * (max - min + 1) + min);

            //System.out.println(myRands);
            //System.out.println(myRands + email);
            String newEmail = myRands + email + "@hotmail.com";
            //System.out.println(newEmail);

            sendKeys(driver, By.id("email"), newEmail);
        } else {
            sendKeys(driver, By.id("email"), email);
        }

        //System.out.println(email);


    }


    @And("enter username {string}")
    public void enterUsername(String username) {


        if (username.equals("newUser")) {
            int min = 100;
            int max = 1000;
            int range = max - min + 1;
            int myRands = (int) (Math.random() * (max - min + 1) + min);

            sendKeys(driver, By.id("new_username"), username + myRands);
        } else {
            sendKeys(driver, By.id("new_username"), username);
        }
        //System.out.println(username);

    }

    @And("enter password {string}")
    public void enterPassword(String password) {

        sendKeys(driver, By.id("new_password"), password);
        //System.out.println(password);

        scroll(driver);
    }


    @When("click Sign Up")
    public void clickSignUp() {
        WebElement button = driver.findElement(By.id("create-account"));

        button.click();
    }


    @Then("account created {string} with {string}")
    public void accountCreatedWithStatus(String text, String status) throws InterruptedException {

        if (status.equals("ok")) {
            WebElement account = driver.findElement(By.xpath("//*[@id=\"signup-content\"]/div/div/div/h1"));
            String actual = account.getText();
            //System.out.println(actual);

            assertEquals(text, actual);

        } else if (status.equals("failed")) {
            WebElement account = driver.findElement(By.className("invalid-error"));
            String actual1 = account.getText();
            //System.out.println(actual1);

            assertEquals(text, actual1);

        }
    }


    @After
    public void stopDriver() {

        driver.quit();
    }

    private static void sendKeys(WebDriver driver, By by, String text) {

        WebDriverWait foobar = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = foobar.until(ExpectedConditions.presenceOfElementLocated(by));

        element.sendKeys(text);
    }


    private static void scroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }


}
