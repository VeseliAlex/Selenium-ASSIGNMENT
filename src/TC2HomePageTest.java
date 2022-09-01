import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC2HomePageTest {

    public static WebDriver webdriver;

    @BeforeAll
    public static void init(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aveselinovic\\Downloads\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get("https://www.saucedemo.com/inventory.html");
        webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
        LogIn(webdriver);
    }

    private static void LogIn(WebDriver webdriver){
        WebElement element = webdriver.findElement(By.id("password"));
        element.sendKeys("secret_sauce");
        webdriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webdriver.findElement(By.id("login-button")).click();

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
    public void labsBackPack() {
        webdriver.get("https://www.saucedemo.com/inventory.html");
        WebElement element = webdriver.findElement(By.className("inventory_item_name"));
        assertNotNull(element);
        element.click();

        String cur = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory-item.html?id=4",cur);

        WebElement price = webdriver.findElement(By.className("inventory_details_price"));
        assertNotNull(price);
        WebElement addtoCartbtn = webdriver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        assertNotNull(addtoCartbtn);
        addtoCartbtn.click();

        WebElement backbtn = webdriver.findElement(By.id("back-to-products"));
        backbtn.click();
        String back = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html",back);
    }

    @Test
    public void addJacket() {
        WebElement jcaket = webdriver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        assertNotNull(jcaket);
        jcaket.click();
    }
    @Test
    public void shoppingCartPage() {
        WebElement cartpagebtn = webdriver.findElement(By.className("shopping_cart_link"));
        assertNotNull(cartpagebtn);
        cartpagebtn.click();

        String site = webdriver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/cart.html",site);

        WebElement checkout = webdriver.findElement(By.id("checkout"));
        assertNotNull(checkout);
        checkout.click();
    }

    @AfterAll
    public static void close(){
        webdriver.close();
    }


}
