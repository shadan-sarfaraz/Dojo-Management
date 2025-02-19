package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class HomePage extends RootPage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//img[@class='img-logo']")
	private WebElement logo;

	public boolean isLogogDisplayed() {
		return logo.isDisplayed();

	}

	public LoginPage clickOnLoginButton() {
		loginButton.click();
		return new LoginPage(driver);
	}

}
