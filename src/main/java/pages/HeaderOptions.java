package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class HeaderOptions extends RootPage {
	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[@id='dropdownMenuButton'])[1]")
	private WebElement notificationDropdown;

	@FindBy(xpath = "//button[@class='btn btn-outline-theme dropdown-toggle']")
	private WebElement multiOptionDropdown;

	@FindBy(xpath = "//a[normalize-space()='Profile']")
	private WebElement profileButton;

	@FindBy(xpath = "(//a[normalize-space()='Schedule your 1x1 call'])[1]")
	private WebElement bookCallButton;

	@FindBy(xpath = "(//h5[text()='Notifications'])[1]")
	private WebElement notificationHeading;

	@FindBy(xpath = "(//a[text()='See all'])[1]")
	private WebElement seeAllButton;

	@FindBy(xpath = "(//a[@title='Daily journal'])[1]")
	private WebElement dailyJournalButton;

	@FindBy(xpath = "(//div//a[@title='Report a Bug'])[1]")
	private WebElement bugButton;

	@FindBy(xpath = "(//div//a[@title='Suggest a Feature'])[1]")
	private WebElement featureButton;

	@FindBy(xpath = "//ul[@class='dropdown-menu show']")
	private WebElement dropdownHeading;

	@FindBy(xpath = "//div[@class='dropdown']//li[3]")
	private WebElement dashboardButtonFromDropdown;

	@FindBy(xpath = "//a[normalize-space()='API']")
	private WebElement apiButtonFromDropdown;

	@FindBy(xpath = "//a[normalize-space()='Change Password']")
	private WebElement changePasswordButtonFromMultiSelectDropdown;

	@FindBy(xpath = "//div[@id='changePasswordModal']")
	private WebElement changePasswordModal;

	@FindBy(xpath = "//div[@id='changePasswordModal']//button")
	private WebElement changePasswordCrossButton;

	@FindBy(xpath = "//a[@id='logOut']")
	private WebElement logoutButton;

	public HomePage clickOnLogout() {
		logoutButton.click();
		return new HomePage(driver);
	}

	public void clickOnCrossButtonOnChangePasswordModule() {
		changePasswordCrossButton.click();
	}

	public String getDomAttributeOfChangePasswordModule(String attributeName) {
		return changePasswordModal.getDomAttribute(attributeName);
	}

	public void clickOnChangePassword() {
		changePasswordButtonFromMultiSelectDropdown.click();
	}

	public boolean isAPISectionDisplayed() {
		boolean b = false;
		try {
			b = apiButtonFromDropdown.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public APIPage clickOnAPIFromMultiSelectDropdown() {
		apiButtonFromDropdown.click();
		return new APIPage(driver);
	}

	public DashboardPage clickOnDashboardFromMultiSelectDropdown() {
		dashboardButtonFromDropdown.click();
		return new DashboardPage(driver);
	}

	public boolean isHeadingDisplayedInDropdown() {
		return dropdownHeading.isDisplayed();
	}

	public FeaturePage clickOnFeatureButton() {
		featureButton.click();
		return new FeaturePage(driver);
	}

	public BugPage clickOnBugButton() {
		bugButton.click();
		return new BugPage(driver);
	}

	public DailyJournalPage clickOnDailyJournalButton() {
		dailyJournalButton.click();
		return new DailyJournalPage(driver);
	}

	public NotificationPage clickOnSeeAllButton() {
		seeAllButton.click();
		return new NotificationPage(driver);
	}

	public String getNotificationHeading() {
		return notificationHeading.getText();
	}

	public boolean isBookYourCallDisplayed() {
		boolean b = false;
		try {
			b = bookCallButton.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public void clickOnBookCallButton() {
		bookCallButton.click();
	}

	public ProfilePage clickOnProfileFromDropdown() {
		profileButton.click();
		return new ProfilePage(driver);
	}

	public void clickOnMultiOptionDropdown() {
		multiOptionDropdown.click();
	}

	public void clickOnNotificationDropdown() {
		notificationDropdown.click();
	}
}
