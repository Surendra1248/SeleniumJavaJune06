package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LoginPage login;
	@Given("^Open any browser$")
    public void open_any_browser() throws Throwable {
     driver = initializeDriver();
    }
	 @And("^Navigate to login page$")
	    public void navigate_to_login_page() throws Throwable {
	      driver.get(prop.getProperty("url"));
	      
	      LandingPage landing= new LandingPage(driver); 
	      landing.myAccountDropDown().click();
	      landing.loginoptionElement().click();
	    }
    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" in to the fields$")
    public void user_enters_username_as_something_and_password_as_something_in_to_the_fields(String strArg1, String strArg2) throws Throwable {
       login = new LoginPage(driver);
       login.getEmailAddressField().sendKeys(strArg1);
       login.getPasswordField().sendKeys(strArg2);
    }
    @And("^User clicks on login button$")
    public void user_clicks_on_login_button() throws Throwable {
       login.getLoginButton().click();
    }

    @Then("^verify user is able to successfully login$")
    public void verify_user_is_able_to_successfully_login() throws Throwable {
       MyAccountPage account= new MyAccountPage(driver);
       Assert.assertTrue(account.getEditaccinfo().isDisplayed());
    }
    @After
    public void closure() {
    	driver.close();
    }

   

   
}
