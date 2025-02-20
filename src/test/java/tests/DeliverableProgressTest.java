package tests;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.DeliverableProgressPage;
import pages.DojoSideMenuPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtilities;

public class DeliverableProgressTest extends Base {
	@BeforeMethod
	public void setup() {
		prop = CommonUtilities.loadPropertiesFile();
		browserName = prop.getProperty("browserName");

		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("internetexplorar")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String appURL = prop.getProperty("devAppURL");
		driver.get(appURL);

		// Login
		homePage = new HomePage(driver);
		LoginPage loginPage = homePage.clickOnLoginButton();
		userLoginPage = loginPage.clickOnUserLoginButton();
		if (appURL.equals(prop.getProperty("devAppURL"))) {
			userLoginPage.enterTextIntoEmailField(prop.getProperty("devValidEmail"));
			userLoginPage.enterTextIntoPasswordField(prop.getProperty("devValidPassword"));
		} else if (appURL.equals(prop.getProperty("liveAppURL"))) {
			userLoginPage.enterTextIntoEmailField(prop.getProperty("liveValidEmail"));
			userLoginPage.enterTextIntoPasswordField(prop.getProperty("liveValidPassword"));
		}
		// randomWait();
		dashboardPage = userLoginPage.clickOnSubmitButton();
		dojoSideMenuPage = new DojoSideMenuPage(driver);
		aiRoleMapPage = dojoSideMenuPage.clickOnAIRoleMapSideMenuOption();
		Assert.assertEquals("Set Up Your Role Map", aiRoleMapPage.getHeadingText());
	}

	@Test(priority = 1)
	public void VerifyAllLinksWorkingProperlyOnAIRoleMapHeaderTest() {
		deliverableProgressPage = aiRoleMapPage.clickOnFreshDeliverable();
		headerOptions = new HeaderOptions(driver);
		if (headerOptions.isBookYourCallDisplayed() == true) {
			headerOptions.clickOnBookCallButton();
			Set<String> windows = driver.getWindowHandles();
			Assert.assertTrue(windows.size() > 1, "New window did not open.");
			headerOptions.closeNewWindows(headerOptions.getDriver());
		}
		headerOptions.clickOnNotificationDropdown();
		Assert.assertEquals(headerOptions.getNotificationHeading(), "Notifications");
		notifiactionPage = headerOptions.clickOnSeeAllButton();
		Assert.assertEquals(notifiactionPage.getHeadingText(), "Notifications");
		navigateBackInBrowser(notifiactionPage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		dailyJournalPage = headerOptions.clickOnDailyJournalButton();
		Assert.assertEquals(dailyJournalPage.getHeading(), "My Daily Journals");
		navigateBackInBrowser(dailyJournalPage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		bugPage = headerOptions.clickOnBugButton();
		Assert.assertEquals(bugPage.getHeading(),
				"Please share any bugs you have found. please give it score between 1 (minor) and 7 (intolerable)");
		navigateBackInBrowser(bugPage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		featurePage = headerOptions.clickOnFeatureButton();
		Assert.assertEquals(featurePage.getHeading(),
				"Please share any feature you would like to have. Please give it score between 1 (Not so important but good to have) and 7 (Must have)");
		navigateBackInBrowser(featurePage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		Assert.assertTrue(headerOptions.isHeadingDisplayedInDropdown());
		profilePage = headerOptions.clickOnProfileFromDropdown();
		Assert.assertEquals(profilePage.getProfileHeading(), "Profile");
		navigateBackInBrowser(profilePage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		dashboardPage = headerOptions.clickOnDashboardFromMultiSelectDropdown();
		Assert.assertTrue(dashboardPage.isDashboardHeadingDisplayed());
		navigateBackInBrowser(dashboardPage.getDriver());
		deliverableProgressPage = new DeliverableProgressPage(driver);
		Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		if (headerOptions.isAPISectionDisplayed()) {
			apiPage = headerOptions.clickOnAPIFromMultiSelectDropdown();
			Assert.assertTrue(apiPage.isAPITapButtonDisplayed());
			navigateBackInBrowser(apiPage.getDriver());
			deliverableProgressPage = new DeliverableProgressPage(driver);
			Assert.assertTrue(deliverableProgressPage.isHeadingDisplayed());
			headerOptions = new HeaderOptions(driver);
			headerOptions.clickOnMultiOptionDropdown();
		}
		headerOptions.clickOnChangePassword();
		postDelay(500);
		Assert.assertEquals(headerOptions.getDomAttributeOfChangePasswordModule("style"), "display: block;");
		headerOptions.clickOnCrossButtonOnChangePasswordModule();
		postDelay(400);
		headerOptions.clickOnMultiOptionDropdown();
		homePage = headerOptions.clickOnLogout();
		Assert.assertTrue(homePage.isLogogDisplayed());
		navigateBackInBrowser(homePage.getDriver());
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isLogoDisplayed());
	}

	@Test(priority = 2)
	public void verifyRoleMapNameShowingCorrectTest() {
		String rolMapName = aiRoleMapPage.getSelectedRoleMapOptionText();
		deliverableProgressPage = aiRoleMapPage.clickOnDeliverables(2, 1);
		Assert.assertTrue(deliverableProgressPage.getRoleMapName().contains(rolMapName));
	}

	@Test(priority = 3)
	public void verifyCompetenciesDropdownFunctionalityTest() {
		List<String> allCompetency = aiRoleMapPage.getCompetencies();
		// List<String> allDeliverable = aiRoleMapPage.getCompetencyAndItsDeliverable();
		deliverableProgressPage = aiRoleMapPage.clickOnDeliverables(2, 1);
		List<String> validOptions = deliverableProgressPage.getCompetencyValueFromDropdown();
		int count = 0;
		for (String optionText : validOptions) {
			deliverableProgressPage.selectCmpetencyOption(optionText);
			Assert.assertEquals(deliverableProgressPage.getSelectedCompetencyText(), allCompetency.get(count));
			count++;
		}
	}

	@Test(priority = 4)
	public void verifyFirstStepToEarnYourPixelWithValidCheatCodeTest() {

		deliverableProgressPage = aiRoleMapPage.clickOnFreshDeliverable();
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);
		deliverableProgressPage.enterTextIntoCheatCodeField(prop.getProperty("whitePixelCheatCode"));
		Assert.assertEquals(deliverableProgressPage.getAdminNote(), prop.getProperty("admin'sNote"));
		// Lesson Impact
		deliverableProgressPage.clickOnRelaventOption();
		deliverableProgressPage.clickOnConfidentOption();
		deliverableProgressPage.clickOnNewKnowledgeOption();

		// Knowledge Application
		deliverableProgressPage.enterTextIntoBrieflyDescribeTextArea("Test");
		deliverableProgressPage.enterTextIntoPotentialChallengeTextArea("Test");
		deliverableProgressPage.clickOnSavePixelManual();
		postDelay(2000);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Data has been saved now");
	}

	@Test(priority = 5)
	public void verifyFirstStepToEarnYourPixelWithInvalidCheatCodeTest() {
		deliverableProgressPage = aiRoleMapPage.clickOnFreshDeliverable();
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);
		deliverableProgressPage.enterTextIntoCheatCodeField(prop.getProperty("whitePixelInvalidCheatCode"));

		// Lesson Impact
		deliverableProgressPage.clickOnRelaventOption();
		deliverableProgressPage.clickOnConfidentOption();
		deliverableProgressPage.clickOnNewKnowledgeOption();

		// Knowledge Application
		deliverableProgressPage.enterTextIntoBrieflyDescribeTextArea("Test");
		deliverableProgressPage.enterTextIntoPotentialChallengeTextArea("Test");
		deliverableProgressPage.clickOnSavePixelManual();
		postDelay(1000);
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(0), "Cheat code does not match our record");
	}

	@Test(priority = 6)
	public void verifyErrorMessageForMendetoryFieldInPixelManualTest() {
		deliverableProgressPage = aiRoleMapPage.clickOnFreshDeliverable();
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		deliverableProgressPage.clickOnSavePixelManual();
		postDelay(1000);
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(0), "The Cheat Code field is required.");
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(1),
				"The lesson relevance field is required.");
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(2),
				"The lesson confidence field is required.");
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(3),
				"The lesson excitement field is required.");
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(4),
				"The application plan field is required.");
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(5), "The challenge field is required.");
	}

	@Test(priority = 7)
	public void verifyButtonsWorkingProperlyForPixelManualPopupTest() {
		deliverableProgressPage = aiRoleMapPage.clickOnDeliverables(2, 8);
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);
		deliverableProgressPage.clickOnPixelManualCrossButton();
		postDelay(500);
		Assert.assertEquals(deliverableProgressPage.getPixelManualDOMAttribute("style"), "display: none;");
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);
		deliverableProgressPage.clickOnPixelManualCloseButton();
		postDelay(500);
		Assert.assertEquals(deliverableProgressPage.getPixelManualDOMAttribute("style"), "display: none;");
	}

	@Test(enabled = false)
	public void verifyFirstStepToEarnYourPixelWithVideoWatchTest() {
		deliverableProgressPage = aiRoleMapPage.clickOnFreshDeliverable();
		deliverableProgressPage.playAndWaitForVideoToComplete();
		postDelay(500);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Success");
		deliverableProgressPage.closePopupMessage();
		postDelay(1000);
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);

		// Lesson Impact
		deliverableProgressPage.clickOnRelaventOption();
		deliverableProgressPage.clickOnConfidentOption();
		deliverableProgressPage.clickOnNewKnowledgeOption();

		// Knowledge Application
		deliverableProgressPage.enterTextIntoBrieflyDescribeTextArea("Test");
		deliverableProgressPage.enterTextIntoPotentialChallengeTextArea("Test");
		deliverableProgressPage.clickOnSavePixelManual();
		postDelay(2000);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Data has been saved now");
		Assert.assertEquals(deliverableProgressPage.getPixelManualDOMAttribute("style"), "display: none;");
	}

	@Test(priority = 8)
	public void verifySecondStepDigitalProgrssWithAllFieldAndYesAIInnovationTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getAdminNote(), prop.getProperty("admin'sNote"));
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clcikOnDigitalChallangeDropdown();
		deliverableProgressPage.selectDigitalChallengeOptionsByIndex(2);
		deliverableProgressPage.enterTimeInTimeToCompleteBefore("12");
		deliverableProgressPage.enterTimeInTimeToCompleteAfter("11");
		deliverableProgressPage.enterProjectedFutureComplitionTime("123");
		deliverableProgressPage.click5thAIMasteryImpact();
		deliverableProgressPage.selectstrategicAIIntegeration(1);
		deliverableProgressPage.enterTextIntoDescribeAIAssisted("test");
		deliverableProgressPage.enterTextIntoWhatAIChallenge("Test");
		deliverableProgressPage.enterTextIntoMostSignificant("test");
		deliverableProgressPage.clickYesOnHaveYouShared();
		deliverableProgressPage.enterTextIntoIfYes("1234");
		deliverableProgressPage.enterTextWhatAdditionalSupport("test");
		deliverableProgressPage.enterVideoWalkThroughURL("https://demo.com");
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Form has been added successfully.");
	}

	@Test(priority = 9)
	public void errorMessageForMendteoyFieldInDigitalWithYesAIInnovationTest() {
		SoftAssert softAssert = new SoftAssert();
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(0),
				"The challenge frequency field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(1),
				"The time before AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(2),
				"The time with AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(3),
				"The projected time with AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(4),
				"The efficiency gain field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(5),
				"The strategic AI integration field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(6),
				"The AI assisted task field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(7),
				"The AI challenges field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(8), "The AI approach field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(9),
				"The colleagues assisted field is required when AI application colleagues is 0.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(10),
				"The acceleration journey field is required.");
		softAssert.assertAll();
	}

	@Test(priority = 10)
	public void errorMessageForMendteoyFieldInDigitalWithNoAIInnovationTest() {
		SoftAssert softAssert = new SoftAssert();
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clickNoOnHaveYouShared();
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(0),
				"The challenge frequency field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(1),
				"The time before AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(2),
				"The time with AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(3),
				"The projected time with AI field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(4),
				"The efficiency gain field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(5),
				"The strategic AI integration field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(6),
				"The AI assisted task field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(7),
				"The AI challenges field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(8), "The AI approach field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(9),
				"The acceleration journey field is required.");
		softAssert.assertAll();
	}

	@Test(priority = 11)
	public void verifySecondStepDigitalProgrssWithAllFieldAndNoAIInnovationTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clcikOnDigitalChallangeDropdown();
		deliverableProgressPage.selectDigitalChallengeOptionsByIndex(2);
		deliverableProgressPage.enterTimeInTimeToCompleteBefore("12");
		deliverableProgressPage.enterTimeInTimeToCompleteAfter("11");
		deliverableProgressPage.enterProjectedFutureComplitionTime("123");
		deliverableProgressPage.click5thAIMasteryImpact();
		deliverableProgressPage.selectstrategicAIIntegeration(1);
		deliverableProgressPage.enterTextIntoDescribeAIAssisted("test");
		deliverableProgressPage.enterTextIntoWhatAIChallenge("Test");
		deliverableProgressPage.enterTextIntoMostSignificant("test");
		deliverableProgressPage.clickNoOnHaveYouShared();
		deliverableProgressPage.enterTextWhatAdditionalSupport("test");
		deliverableProgressPage.enterVideoWalkThroughURL("https://demo.com");
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Form has been added successfully.");
	}

	@Test(priority = 12)
	public void verifySecondStepDigitalProgrssMoreTimeToCompleteTestThenBeforeTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clcikOnDigitalChallangeDropdown();
		deliverableProgressPage.selectDigitalChallengeOptionsByIndex(1);
		String time = "112";
		deliverableProgressPage.enterTimeInTimeToCompleteBefore(time);
		deliverableProgressPage.enterTimeInTimeToCompleteAfter("112");
		deliverableProgressPage.enterProjectedFutureComplitionTime("123");
		deliverableProgressPage.click5thAIMasteryImpact();
		deliverableProgressPage.selectstrategicAIIntegeration(1);
		deliverableProgressPage.enterTextIntoDescribeAIAssisted("test");
		deliverableProgressPage.enterTextIntoWhatAIChallenge("Test");
		deliverableProgressPage.enterTextIntoMostSignificant("test");
		deliverableProgressPage.clickNoOnHaveYouShared();
		deliverableProgressPage.enterTextWhatAdditionalSupport("test");
		deliverableProgressPage.enterVideoWalkThroughURL("https://demo.com");
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getErrorMessage().get(0),
				"The time with ai field must be less than " + time + ".");
	}

	@Test(priority = 13)
	public void verifySecondStepDigitalProgrssWithOnlyMendetoryFieldTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		deliverableProgressPage.clcikOnDigitalChallangeDropdown();
		String value = "test";
		deliverableProgressPage.enterVlaueInHowFrequentlyDropdown(value);
		deliverableProgressPage.selectDigitalChallengeOptions(value);
		deliverableProgressPage.enterTimeInTimeToCompleteBefore("12");
		deliverableProgressPage.enterTimeInTimeToCompleteAfter("11");
		deliverableProgressPage.enterProjectedFutureComplitionTime("123");
		deliverableProgressPage.click5thAIMasteryImpact();
		deliverableProgressPage.selectstrategicAIIntegeration(1);
		deliverableProgressPage.enterTextIntoDescribeAIAssisted("test");
		deliverableProgressPage.enterTextIntoWhatAIChallenge("Test");
		deliverableProgressPage.enterTextIntoMostSignificant("test");
		deliverableProgressPage.clickYesOnHaveYouShared();
		deliverableProgressPage.enterTextIntoIfYes("123");
		deliverableProgressPage.enterTextWhatAdditionalSupport("test");
		deliverableProgressPage.clickOnDigitalSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Form has been added successfully.");
	}

	@Test(priority = 14)
	public void verifyAllButtonsWorkingInDigitalSectionTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 1");
		deliverableProgressPage.clickOnSecondStep();
		Assert.assertEquals(deliverableProgressPage.getSecondStepHeading(), "Digital Challenge Focus");
		dashboardPage = deliverableProgressPage.clickOnVideoWalkThroughLink();
		Assert.assertTrue(dashboardPage.isDashboardHeadingDisplayed());
	}

	@Test(priority = 15)
	public void verifySubmittingPixelLessionWithAllFieldTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 2");
		deliverableProgressPage.clickOnThirdStep();
		String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "Piolet.pdf").toString();
		deliverableProgressPage.enterFileInSOPMatrixField(filePath);
		deliverableProgressPage.selectValueFromPixelLessionDropdown(1);
		deliverableProgressPage.enterMinuteIncompleteThisLession("123");
		deliverableProgressPage.enterTextIntoMasteryJourny("test");
		deliverableProgressPage.clickYesNextPixelLevel();
		Assert.assertEquals(deliverableProgressPage.getFeelPrepearedHeadingText(), "What makes you feel prepared? *");
		deliverableProgressPage.enterTexWhatMakeYouFeelTextArea("test");
		deliverableProgressPage.enterTextIntoContinousImprovementTextArea("test");
		deliverableProgressPage.clickOnPixelSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Form has been added successfully.");
	}

	@Test(priority = 16)
	public void verifyErrorMessageForPixelLessionOfAllFieldTest() {
		SoftAssert softAssert = new SoftAssert();
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 2");
		deliverableProgressPage.clickOnThirdStep();
		deliverableProgressPage.clickOnPixelSubmitButton();
		postDelay(800);
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(0),
				"The pixel lesson field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(1),
				"The lesson duration field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(2),
				"The journey contribution field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(3),
				"The ready to advance field is required.");
		softAssert.assertAll();
	}

	@Test(priority = 17)
	public void verifyErrorMessageForPixelLessionOfAllWithYesNextPixelLessionFieldTest() {
		SoftAssert softAssert = new SoftAssert();
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 2");
		deliverableProgressPage.clickOnThirdStep();
		deliverableProgressPage.clickYesNextPixelLevel();
		deliverableProgressPage.clickOnPixelSubmitButton();
		postDelay(800);
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(0),
				"The pixel lesson field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(1),
				"The lesson duration field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(2),
				"The journey contribution field is required.");
		softAssert.assertEquals(deliverableProgressPage.getErrorMessage().get(3), "The prepared field is required.");
		softAssert.assertAll();
	}

	@Test(priority = 18)
	public void verifySubmittingPixelLessionWithMendetoryFieldTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 2");
		deliverableProgressPage.clickOnThirdStep();
		deliverableProgressPage.selectValueFromPixelLessionDropdown(1);
		deliverableProgressPage.enterMinuteIncompleteThisLession("123");
		deliverableProgressPage.enterTextIntoMasteryJourny("test");
		deliverableProgressPage.clickYesNextPixelLevel();
		Assert.assertEquals(deliverableProgressPage.getFeelPrepearedHeadingText(), "What makes you feel prepared? *");
		deliverableProgressPage.enterTexWhatMakeYouFeelTextArea("test");
		deliverableProgressPage.clickOnPixelSubmitButton();
		postDelay(800);
		Assert.assertEquals(deliverableProgressPage.getPopupMessage(), "Form has been added successfully.");
	}

	@Test(priority = 19)
	public void verifyAllLinkButtonsAndAdminSideDataShowingAndWorkingProperlyTest() {
		deliverableProgressPage = aiRoleMapPage.clickDeliverableByStepDeliverable("step 2");
		companyVideoPage = deliverableProgressPage.clickHomeworkOverviewVideo();
		navigateBackInBrowser(companyVideoPage.getDriver());
		deliverableProgressPage.clickOnFirstStepToEarnPixel();
		postDelay(500);
		Assert.assertEquals(deliverableProgressPage.getPixelManualDOMAttribute("style"), "display: block;");
	}
}
