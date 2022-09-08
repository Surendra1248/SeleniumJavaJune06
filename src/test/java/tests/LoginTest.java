package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	Logger log;

	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("navigated to application URL");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String pass, String expected) throws IOException {
		
		LandingPage landing = new LandingPage(driver);
		landing.myAccountDropDown().click();
		log.debug("Clicked on myAccountDropDown");
		landing.loginoptionElement().click();
		log.debug("Clicked on loginOption");
		LoginPage loginPage = new LoginPage(driver);

		loginPage.getEmailAddressField().sendKeys(email);
		log.debug("Entered email address");
		loginPage.getPasswordField().sendKeys(pass);
		log.debug("Entered password");
		loginPage.getLoginButton().click();
		log.debug("Clicked on Login Button");
		MyAccountPage account = new MyAccountPage(driver);

		String actual = null;
		try {
			if (account.getEditaccinfo().isDisplayed()) {
				log.debug("User got logged in");
				actual = "Successful";
			}
		} catch (Exception e) {
			log.debug("User didn't login");
			actual = "failure";
		}
		Assert.assertEquals(actual, expected);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "phaniatw2@gmail.com", "atw@123", "Successful" },
				{ "sample@test.com", "test123", "failure" } };

		return data;
	}
}
