package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class FeaturePage extends RootPage {
	WebDriver driver;

	public FeaturePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div//h5[@class='card-title']")
	private WebElement headingText;

	public String getHeading() {
		return headingText.getText();
	}
}
