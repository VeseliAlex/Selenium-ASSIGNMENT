import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC2CheckoutTest {
    public static WebDriver weBDdriver;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aveselinovic\\Downloads\\chromedriver.exe");
        weBDdriver = new ChromeDriver();
        weBDdriver.get(" https://www.saucedemo.com/ ");
        weBDdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
        LogIn(weBDdriver);
        weBDdriver.get("https://www.saucedemo.com/checkout-step-one.html");
    }
    private static void LogIn(WebDriver weBDdriver){
        WebElement element = weBDdriver.findElement(By.id("password"));
        element.sendKeys("secret_sauce");
        weBDdriver.findElement(By.id("user-name")).sendKeys("standard_user");
        weBDdriver.findElement(By.id("login-button")).click();

    }

    @Test
    public void firstNameField(){
        WebElement firstNamefield = weBDdriver.findElement(By.id("first-name"));
        assertNotNull(firstNamefield);
        firstNamefield.sendKeys("aleksa");
        assertEquals("aleksa",firstNamefield.getAttribute("value"));

    }
    @Test
    public void lastNameField(){
         WebElement lastNameField = weBDdriver.findElement(By.id("last-name"));
         assertNotNull(lastNameField);

         lastNameField.sendKeys("Veselinovic");
         assertEquals("Veselinovic",lastNameField.getAttribute("value"));
    }
    @Test
    public void zipCode() {
      WebElement zipCodeField = weBDdriver.findElement(By.id("postal-code"));
      assertNotNull(zipCodeField);


      zipCodeField.sendKeys("1234");
      assertEquals("1234",zipCodeField.getAttribute("value"));

        validateNext();
    }
    public void validateNext() {

        //Finding Check button and checking its existence
        WebElement nextbtn = weBDdriver.findElement(By.id("continue"));
        assertNotNull(nextbtn);
        nextbtn.click();

        WebElement finishbtn = weBDdriver.findElement(By.id("finish"));
        finishbtn.click();
        thankYou();
    }
    public void thankYou() {
        String cur = weBDdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-complete.html",cur);

        WebElement element = weBDdriver.findElement(By.className("complete-header"));
        assertNotNull(element);

        WebElement logOut = weBDdriver.findElement(By.id("react-burger-menu-btn"));
        logOut.click();

        weBDdriver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @AfterAll
    public static void close(){
        weBDdriver.close();
    }
}
