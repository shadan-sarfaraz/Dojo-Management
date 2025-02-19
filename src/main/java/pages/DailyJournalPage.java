package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class DailyJournalPage extends RootPage {
	WebDriver driver;

	public DailyJournalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[normalize-space()='My Daily Journals']")
	private WebElement headingText;

	public String getHeading() {
		return headingText.getText();
	}
}
