import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HomePageTest {
    public static WebDriver webdriver;
    public static WebElement element;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aveselinovic\\Downloads\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get("https://www.saucedemo.com/inventory.html");
        webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
        //Login to Home
        logIn(webdriver) ;
    }
    //Immediately log in. Tested in previous class
    private static void logIn(WebDriver webdriver) {
        WebElement element =webdriver.findElement(By.id("password"));
        element.sendKeys("secret_sauce");
        webdriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webdriver.findElement(By.id("login-button")).click();

    }
    @Test
    public void header(){
        WebElement header = webdriver.findElement(By.className("title"));
        assertNotNull(header);
    }


   @Test
    public void currentPage() {
        String cur = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", cur);
    }

    @Test
    public void shoppingCart() {
        WebElement shoppingbtn =webdriver.findElement(By.className("shopping_cart_link"));
        assertNotNull(shoppingbtn);
    }
    @Test
    public void burgerMenu() {
        WebElement burgerbtn = webdriver.findElement(By.id("react-burger-menu-btn"));
        assertNotNull(burgerbtn);

    }
    @Test
    public void logOutBar() {
        webdriver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement logoutbtn = webdriver.findElement(By.id("logout_sidebar_link"));
        assertNotNull(logoutbtn);
    }
    @Test
    public void faceBook() {
        WebElement facebookbtn = webdriver.findElement(By.xpath("//*/li[@class='social_facebook']/a"));
        assertNotNull(facebookbtn);

        String cur = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", cur);

    }
    @Test
    public void linkedIn(){
      WebElement linkedinbtn=  webdriver.findElement(By.className("social_linkedin"));
      assertNotNull(linkedinbtn);
      String cur = webdriver.getCurrentUrl();
      assertEquals("https://www.saucedemo.com/inventory.html", cur);

    }
    @Test
    public void twitter() {
        WebElement twitterbtn = webdriver.findElement(By.xpath("//*/li[@class='social_twitter']/a"));
        assertNotNull(twitterbtn);
        String cur = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", cur);
    }
    @Test
    public void logOut() {
        WebElement logoutbtn= webdriver.findElement(By.id("logout_sidebar_link"));
        assertNotNull(logoutbtn);
    }

   @AfterAll
    public static void Close() {
        webdriver.close();
   }
}
