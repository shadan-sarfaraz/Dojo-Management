package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class DashboardPage extends RootPage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='col-12 col-lg-12 mb-3']")
	private WebElement profileHeading;

	public boolean isDashboardHeadingDisplayed() {
		return profileHeading.isDisplayed();
	}
}
