package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NotificationPage extends RootPage {
	WebDriver driver;

	public NotificationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[normalize-space()='Notifications']")
	private WebElement headingText;

	public String getHeadingText() {
		return headingText.getText();
	}

}
