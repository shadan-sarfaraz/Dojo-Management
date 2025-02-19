package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LoginPage extends RootPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='user-tab']")
	private WebElement userButton;

	@FindBy(xpath = "//h2[normalize-space()='Welcome Back!']")
	private WebElement heading;

	public boolean isLogoDisplayed() {
		return heading.isDisplayed();
	}

	public UserLoginPage clickOnUserLoginButton() {
		userButton.click();
		return new UserLoginPage(driver);
	}

}
