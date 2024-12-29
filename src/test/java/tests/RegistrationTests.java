package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void setUp() {
        // Configura manualmente el controlador del navegador Edge
        System.setProperty("webdriver.edge.driver", "C:\\Users\\User\\Downloads\\msedgedriver.exe");

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://test-qa.inlaze.com/auth/sign-up");
        registrationPage = new RegistrationPage(driver);
    }

    @Test(description = "Verifica que el usuario pueda registrarse con datos válidos")
    public void testValidRegistration() {
        registrationPage.enterName("John Doe");
        registrationPage.enterEmail("johndoe@example.com");
        registrationPage.enterPassword("Password123!");
        registrationPage.enterConfirmPassword("Password123!");
        registrationPage.clickRegister();

        // Ejemplo de aserción: verifica que el usuario sea redirigido a una página de bienvenida
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/welcome"), "El usuario no fue redirigido a la página de bienvenida.");
    }

    @Test(description = "Valida que se muestren errores cuando los campos están vacíos")
    public void testEmptyFields() {
        registrationPage.clickRegister();
        String error = registrationPage.getErrorMessage();
        Assert.assertEquals(error, "Todos los campos son obligatorios.", "El mensaje de error no coincide.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

