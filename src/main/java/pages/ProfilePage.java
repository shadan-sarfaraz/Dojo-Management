package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProfilePage extends RootPage {
	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card-body']//div[2]//div[2]//p[1]")
	private WebElement name;

	@FindBy(xpath = "//h3[normalize-space()='Profile']")
	private WebElement profileHeading;

	public String getProfileHeading() {
		return profileHeading.getText();
	}

	public String getUserNameFromProfilePage() {
		return name.getText();
	}
}
