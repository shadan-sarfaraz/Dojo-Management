package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class APIPage extends RootPage {
	WebDriver driver;

	public APIPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='apiTapBtn']")
	private WebElement apiButton;

	public boolean isAPITapButtonDisplayed() {
		return apiButton.isDisplayed();
	}

}
