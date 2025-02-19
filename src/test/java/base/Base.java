package base;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import pages.AIRoleMapPage;
import pages.APIPage;
import pages.BugPage;
import pages.CompanyVideoPage;
import pages.DailyJournalPage;
import pages.DashboardPage;
import pages.DeliverableProgressPage;
import pages.DojoSideMenuPage;
import pages.FeaturePage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.NotificationPage;
import pages.ProfilePage;
import pages.TourGuidePage;
import pages.UserLoginPage;

public class Base {

	WebDriverWait wait;

	public WebDriver driver;
	public String browserName;
	public String roleMap = "Automation Tester";
	public String description = "Create a role map";
	public JavascriptExecutor js;
	public Properties prop;

	public HomePage homePage;
	public UserLoginPage userLoginPage;
	public TourGuidePage tourGuidePage;
	public DojoSideMenuPage dojoSideMenuPage;
	public AIRoleMapPage aiRoleMapPage;
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public HeaderOptions headerOptions;
	public ProfilePage profilePage;
	public DeliverableProgressPage deliverableProgressPage;
	public NotificationPage notifiactionPage;
	public DailyJournalPage dailyJournalPage;
	public BugPage bugPage;
	public FeaturePage featurePage;
	public APIPage apiPage;
	public CompanyVideoPage companyVideoPage;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void postDelay(int delayMiliSecond) {
		try {
			Thread.sleep(delayMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void randomWait() {
		try {
			int waitTime = ThreadLocalRandom.current().nextInt(2000, 5000); // 2 to 5 seconds
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
		waitForPageLoad();
	}

	// Explicit Wait for Page Load
	public void waitForPageLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

}
