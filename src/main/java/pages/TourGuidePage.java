package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class TourGuidePage extends RootPage {
	WebDriver driver;

	public TourGuidePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='d-flex align-items-center justify-content-between']//button[@aria-label='Close']")
	private WebElement closeButton;

	public void clickOnCrossButton() {
		closeButton.click();
	}
}
