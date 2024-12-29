package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\User\\Downloads\\msedgedriver.exe");

        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://test-qa.inlaze.com/auth/sign-in");
    }

    @Test(priority = 1, description = "Verifica que el usuario pueda iniciar sesión con credenciales válidas")
    public void testLoginWithValidCredentials() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-sign-in/main/section[1]/app-sign-in-form/form/button"));

        emailField.sendKeys("pruebas@example.com");
        passwordField.sendKeys("ValidPassword123");
        loginButton.click();

        WebElement userNameDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNameDisplay")));
        Assert.assertTrue(userNameDisplay.isDisplayed(), "El nombre del usuario no se muestra.");
        Assert.assertEquals(userNameDisplay.getText(), "Usuario Ejemplo");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

