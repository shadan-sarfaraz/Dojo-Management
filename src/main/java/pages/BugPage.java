package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class BugPage extends RootPage {
	WebDriver driver;

	public BugPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h5[contains(text(),'Please share any bugs')]")
	private WebElement headingText;

	public String getHeading() {
		return headingText.getText();
	}

}
