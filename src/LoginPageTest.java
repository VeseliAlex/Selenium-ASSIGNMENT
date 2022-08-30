import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aveselinovic\\Downloads\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(" https://www.saucedemo.com/ ");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
    }

    @BeforeEach
    public void navigateToRoot() {
        webDriver.navigate().to(" https://www.saucedemo.com/ ");

        WebElement password = webDriver.findElement(By.id("password"));
        password.sendKeys("");
        WebElement username = webDriver.findElement(By.id("user-name"));
        macCleanHack(password);
        macCleanHack(username);
    }

    private static void macCleanHack(WebElement element) {
        String inputText = element.getAttribute("value");
        if (inputText != null) {
            for (int i = 0; i < inputText.length(); i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    @Test
    public void websiteContainsPasswordFiled() {
        assertWebElementExists("password");
    }

    private static WebElement assertWebElementExists(String password) {
        WebElement element = webDriver.findElement(By.id(password));
        assertNotNull(element);

        return element;
    }

    @Test
    public void websiteEnterPasswordValue() {
        WebElement element = assertWebElementExists("password");

        //Type password
        assertEquals(0, element.getText().length());
        element.sendKeys("secret_sauce");
        assertEquals("secret_sauce", element.getAttribute("value"));

    }
    @Test
    public void websiteEnterUserNameValue2(){
        WebElement element2 = assertWebElementExists("user-name");
        assertEquals(0,element2.getText().length());
        element2.sendKeys("locked_out_user");
        assertEquals("locked_out_user", element2.getAttribute("value"));
    }

    @Test
    public void websiteEnterUsernameValue() {
        WebElement element = assertWebElementExists("user-name");

        // Type Username
        assertEquals(0, element.getText().length());
        element.sendKeys("standard_user");
        assertEquals("standard_user", element.getAttribute("value"));
    }

    //
//    @AfterEach
//    public static void after(){
//
//  }
    @Test
    public void successfulLogIn() {
        //Checking the correct log in page
        validateLoginAction("standard_user", "secret_sauce");

        //Actually nvigated to homepage
        String cur = webDriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", cur);

    }

    @Test
    public void failedLogIn() {
        validateLoginAction("", "");

//Actually nvigated to homepage
        String cur = webDriver.getCurrentUrl();
        assertNotEquals("https://www.saucedemo.com/inventory.html", cur);
    }

    private static void validateLoginAction(String x, String x1) {
        //Checking the wrong log in page
        String page = webDriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/", page);

        WebElement element = assertWebElementExists("user-name");

        WebElement element2 = assertWebElementExists("password");

        WebElement element3 = assertWebElementExists("login-button");


        // Type Username
        assertEquals(0, element.getText().length());
        element.sendKeys(x);
        assertEquals(x, element.getAttribute("value"));

        //Type password
        assertEquals(0, element2.getText().length());
        element2.sendKeys(x1);
        assertEquals(x1, element2.getAttribute("value"));

        //Submit Button
        element3.click();
    }


    @AfterAll
    public static void after() {
        webDriver.close();
    }
}