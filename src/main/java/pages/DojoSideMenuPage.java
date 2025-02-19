package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class DojoSideMenuPage extends RootPage {

	WebDriver driver;

	public DojoSideMenuPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='AI Role Map']")
	private WebElement AIRoleMap;

	public AIRoleMapPage clickOnAIRoleMapSideMenuOption() {
		AIRoleMap.click();
		return new AIRoleMapPage(driver);
	}

}
