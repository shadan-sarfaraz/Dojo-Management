package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class UserLoginPage extends RootPage {
	WebDriver driver;

	public UserLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//form[@id='userLoginForm']//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//form[@id='userLoginForm']//input[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//form[@id='userLoginForm']//input[@id='submit']")
	private WebElement submitButton;

	public DashboardPage clickOnSubmitButton() {
		submitButton.click();
		return new DashboardPage(driver);
	}

	public void enterTextIntoPasswordField(String password) {
		passwordField.sendKeys(password);
	}

	public void enterTextIntoEmailField(String email) {
		emailField.sendKeys(email);
	}

}
