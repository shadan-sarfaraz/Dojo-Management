package tests;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AIRoleMapPage;
import pages.DojoSideMenuPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtilities;

public class AIRoleMap extends Base {

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
//		randomWait();
		dashboardPage = userLoginPage.clickOnSubmitButton();
		dojoSideMenuPage = new DojoSideMenuPage(driver);
		aiRoleMapPage = dojoSideMenuPage.clickOnAIRoleMapSideMenuOption();
		Assert.assertEquals("Set Up Your Role Map", aiRoleMapPage.getHeadingText());
		postDelay(800);
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void VerifyAllLinksWorkingProperlyOnAIRoleMapHeaderTest() {
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
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		dailyJournalPage = headerOptions.clickOnDailyJournalButton();
		Assert.assertEquals(dailyJournalPage.getHeading(), "My Daily Journals");
		navigateBackInBrowser(dailyJournalPage.getDriver());
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		bugPage = headerOptions.clickOnBugButton();
		Assert.assertEquals(bugPage.getHeading(),
				"Please share any bugs you have found. please give it score between 1 (minor) and 7 (intolerable)");
		navigateBackInBrowser(bugPage.getDriver());
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		featurePage = headerOptions.clickOnFeatureButton();
		Assert.assertEquals(featurePage.getHeading(),
				"Please share any feature you would like to have. Please give it score between 1 (Not so important but good to have) and 7 (Must have)");
		navigateBackInBrowser(featurePage.getDriver());
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		Assert.assertTrue(headerOptions.isHeadingDisplayedInDropdown());
		profilePage = headerOptions.clickOnProfileFromDropdown();
		Assert.assertEquals(profilePage.getProfileHeading(), "Profile");
		navigateBackInBrowser(profilePage.getDriver());
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		dashboardPage = headerOptions.clickOnDashboardFromMultiSelectDropdown();
		Assert.assertTrue(dashboardPage.isDashboardHeadingDisplayed());
		navigateBackInBrowser(dashboardPage.getDriver());
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		if (headerOptions.isAPISectionDisplayed()) {
			apiPage = headerOptions.clickOnAPIFromMultiSelectDropdown();
			Assert.assertTrue(apiPage.isAPITapButtonDisplayed());
			navigateBackInBrowser(apiPage.getDriver());
			aiRoleMapPage = new AIRoleMapPage(driver);
			Assert.assertTrue(aiRoleMapPage.isHeadingDisplayed());
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
	public void createAIRoleMapForFirstTimeForAFreshAccounTest() {
		if (aiRoleMapPage.isNoRoleMapTextDisplayed().equals(true)) {
			aiRoleMapPage.clickOnFirstTimeRoleMapCreateButton();
			postDelay(500);
			Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
			aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("validRoleMap"));
			aiRoleMapPage
					.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("validRoleMapDescription"));
			aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			if (aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule().equals(true)) {
				aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
				aiRoleMapPage.waitUntillLoaderToDisappear();
			}
			aiRoleMapPage.clickOnGenerateButtonOnCreateRoleMapPopUpModule();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Role Map has been added.");
		} else {
			System.out.println("There is already role Map created");
		}
	}

	@Test(priority = 3)
	public void verifyButtonsOnCreateRoleMapPopupModuleAreWorkingProperlyTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		aiRoleMapPage.closePopupModule();
		postDelay(500);
		Assert.assertTrue(
				aiRoleMapPage.getDomAttributeForPopupModuleOfCreateAIRoleMap("style").equals("display: none;"));
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		aiRoleMapPage.clickOnCloseButtonOnAIRoleMapCreatePopupModule();
		postDelay(500);
		Assert.assertTrue(
				aiRoleMapPage.getDomAttributeForPopupModuleOfCreateAIRoleMap("style").equals("display: none;"));
	}

	@Test(priority = 4)
	public void verifyCreateAIRoleMapWithInValidRoleMapNameWithoutFileTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
		aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("invalidRoleMap"));
		aiRoleMapPage
				.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("invalidRoleMapDescription"));
		aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		aiRoleMapPage = new AIRoleMapPage(driver);
		aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertTrue(aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule());
	}

	@Test(priority = 5)
	public void verifyCreateAIRoleMapWithInValidRoleMapNameWithFileTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		if (aiRoleMapPage.isFilePathFieldDisplayed().equals(true)) {
			Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
			aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("invalidRoleMap"));
			aiRoleMapPage
					.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("invalidRoleMapDescription"));
			String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "RolePlanDescriptionFile.txt")
					.toString();
			aiRoleMapPage.sendFielIntoCreateRoleMapSection(filePath);
			aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			aiRoleMapPage = new AIRoleMapPage(driver);
			aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			aiRoleMapPage = new AIRoleMapPage(driver);
			Assert.assertTrue(aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule());
		} else {
			System.out.println("File Field is not present");
		}
	}

	@Test(priority = 6)
	public void verifyCreateAIRoleMapWithValidRoleMapNameWithoutFileTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
		aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("validRoleMap"));
		aiRoleMapPage.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("validRoleMapDescription"));
		aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		if (aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule().equals(true)) {
			aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
			aiRoleMapPage.waitUntillLoaderToDisappear();
		}
		aiRoleMapPage.clickOnGenerateButtonOnCreateRoleMapPopUpModule();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Role Map has been added.");
	}

	@Test(priority = 7)
	public void verifyErrorMessageForMendetoryFieldInCreateRoleMapSection() {
		aiRoleMapPage.clickOnCreateButton();
		aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		List<String> errorMessages = aiRoleMapPage.getErrorMessage();
		Assert.assertEquals(errorMessages.get(0), "The name field is required.");
		Assert.assertEquals(errorMessages.get(1), "The description field is required when files is not present.");
		aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("validRoleMap"));
		aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertEquals(errorMessages.get(1), "The description field is required when files is not present.");
		aiRoleMapPage.clearTextFromRoleNameField();
		aiRoleMapPage.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("validRoleMapDescription"));
		aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertEquals(errorMessages.get(0), "The name field is required.");
		if (aiRoleMapPage.isFilePathFieldDisplayed().equals(true)) {
			aiRoleMapPage.clearTextFromRoleDescriptionField();
			String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "RolePlanDescriptionFile.txt")
					.toString();
			aiRoleMapPage.sendFielIntoCreateRoleMapSection(filePath);
			aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			Assert.assertEquals(errorMessages.get(0), "The name field is required.");
		}
	}

	@Test(priority = 8)
	public void verifyCreatingRolePlanWithFileWithoutDescriptionFieldTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		if (aiRoleMapPage.isFilePathFieldDisplayed().equals(true)) {
			Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
			aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("validRoleMap"));
			String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "RolePlanDescriptionFile.txt")
					.toString();
			aiRoleMapPage.sendFielIntoCreateRoleMapSection(filePath);
			aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			if (aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule().equals(true)) {
				aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
				aiRoleMapPage.waitUntillLoaderToDisappear();
			}
			aiRoleMapPage.clickOnGenerateButtonOnCreateRoleMapPopUpModule();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Role Map has been added.");
		} else {
			System.out.println("File Field is not present");
		}
	}

	@Test(priority = 9)
	public void verifyCreatingRolePlanWithFileWithDescriptionFieldTest() {
		aiRoleMapPage.clickOnCreateButton();
		postDelay(500);
		if (aiRoleMapPage.isFilePathFieldDisplayed().equals(true)) {
			Assert.assertEquals(aiRoleMapPage.getCreatePopupModuleHeadingText(), "Create Role Map");
			aiRoleMapPage.enterTextIntoRolePlanTextAreaPopupModule(prop.getProperty("validRoleMap"));
			aiRoleMapPage
					.enterTextIntoRolePlanDescriptionTextAreaPopupModule(prop.getProperty("validRoleMapDescription"));
			String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "RolePlanDescriptionFile.txt")
					.toString();
			aiRoleMapPage.sendFielIntoCreateRoleMapSection(filePath);
			aiRoleMapPage.clickOnCreateButtonFromPopupmoduleOfCreateRoleMap();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			if (aiRoleMapPage.isInvalidRoleMapMessageDisplayedInPopupModule().equals(true)) {
				aiRoleMapPage.clickOnRegenerateButtonOnPopupModule();
				aiRoleMapPage.waitUntillLoaderToDisappear();
			}
			aiRoleMapPage.clickOnGenerateButtonOnCreateRoleMapPopUpModule();
			aiRoleMapPage.waitUntillLoaderToDisappear();
			Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Role Map has been added.");
		} else {
			System.out.println("File Field is not present");
		}
	}

	@Test(priority = 10)
	public void verifyUserNameDisplayedOnTheAIRoleMapPageTest() {
		String displayName = aiRoleMapPage.getDisplayedUserName();
		HeaderOptions headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMultiOptionDropdown();
		profilePage = headerOptions.clickOnProfileFromDropdown();
		Assert.assertEquals(displayName, profilePage.getUserNameFromProfilePage());
	}

	@Test(priority = 11)
	public void verifyDropdownWorkingProperlyTest() {
		List<String> validOptions = aiRoleMapPage.getValidDropdownOptions();
		for (String optionText : validOptions) {
			aiRoleMapPage.selectDropdownOption(optionText);
			Assert.assertEquals(aiRoleMapPage.getSelectedRoleMapOptionText(), optionText);
		}
	}

	@Test(priority = 12)
	public void verifyEditAndSaveFunctionality() {
		Assert.assertEquals(aiRoleMapPage.getSaveAndEditDeliverableButtonText(), "Edit deliverable");
		aiRoleMapPage.clickSaveAndEditDeliverableButton();
		Assert.assertEquals(aiRoleMapPage.getSaveAndEditDeliverableButtonText(), "Save deliverable");
		aiRoleMapPage.clickDeliverableForEditing(2, 2);
		postDelay(800);
		Assert.assertEquals(aiRoleMapPage.getModuleHeadingText(), "Edit Deliverable");
		aiRoleMapPage.clearEditDeliverableModuleTextArea();
		aiRoleMapPage.enterTextIntoEditDeliverableModuleTextArea("This deliverable is edited");
		aiRoleMapPage.clickOnDeliverableUpdateButtonFromPopupModule();
		postDelay(800);
		Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Deliverable has been updated successfully.");
		aiRoleMapPage.closePopupMessage();
		aiRoleMapPage = new AIRoleMapPage(driver);
		Assert.assertEquals(aiRoleMapPage.getDeliverableText(2, 2), "This deliverable is edited");
	}

	@Test(priority = 13)
	public void verifyButtonsOnEditDeliverablePopupModuleAreWorkingProperlyTest() {
		aiRoleMapPage.clickSaveAndEditDeliverableButton();
		aiRoleMapPage.clickDeliverableForEditing(2, 2);
		postDelay(500);
		aiRoleMapPage.closePopupModule();
		postDelay(500);
		Assert.assertTrue(
				aiRoleMapPage.getDomAttributeForPopupModuleOfEditDeliverable("style").equals("display: none;"));
		aiRoleMapPage.clickDeliverableForEditing(2, 2);
		postDelay(500);
		aiRoleMapPage.clickOnCloseButtonOnEditDeliverablePopupModule();
		postDelay(500);
		Assert.assertTrue(
				aiRoleMapPage.getDomAttributeForPopupModuleOfEditDeliverable("style").equals("display: none;"));
	}

	@Test(priority = 14)
	public void verifyTextInPixelLevelBoxTest() {
		String actualFirstPixelText = "White Pixel Lesson";
		String actualSecondPixelText = "Yellow Pixel Lesson";
		String actualThirdPixelText = "Orange Pixel Lesson";
		String actualFourthPixelText = "Green Pixel Lesson";
		String actualFifthPixelText = "Blue Pixel Lesson";
		String actualSixthPixelText = "Purple Pixel Lesson";
		String actualSeventhPixelText = "Brown Pixel Lesson";
		String actualEighthPixelText = "Black Pixel Lesson";

		String whitePixelTooltipTile = "White Pixel Lesson";
		String yellowPixelTooltipTile = "Yellow Pixel Lesson";
		String orangePixelTooltipTile = "Orange Pixel Lesson";
		String greenPixelTooltipTile = "Green Pixel Lesson";
		String bluePixelTooltipTile = "Blue Pixel Lesson";
		String purplePixelTooltipTile = "Purple Pixel Lesson";
		String brownPixelTooltipTile = "Brown Pixel Lesson";
		String blackPixelTooltipTile = "Black Pixel Lesson";

		String whitePixelTooltipDescription = "This lesson  is designed to guide you through the White Pixel level of the ASAP program, where you'll focus on transforming your deliverables with AI and preparing for deeper integration in later stages.";
		String yellowPixelTooltipDescription = "This lesson is designed to guide you through the Yellow Pixel level of the ASAP program, where you'll focus on refining your context-building skills, optimizing deliverables with AI, and expanding your strategic use of AI.";
		String orangePixelTooltipDescription = "This lesson is designed to guide you through the Orange Pixel level of the ASAP program, where you will refine your deliverables using expert frameworks, advanced AI techniques, and strategic context-building. The goal of this phase is to enhance your SOPs, incorporate expert methodologies, and calculate the tangible impact of AI on your workflows.";
		String greenPixelTooltipDescription = "This lesson is designed to guide you through the Green Pixel level of the ASAP program, where you will learn to use Knowledge Files (KFs) to master token-efficient workflows, build an SOP Matrix using orchestration and Knowledge Files (KFs), and prepare for advanced AI integration.";
		String bluePixelTooltipDescription = "In this lesson, the user will focus on Transforming the SOP Matrix process we have been building with individual prompts into a runnable assistant tailored to create SOP Matricies for any chosen deliverable.";
		String purplePixelTooltipDescription = "Purple Pixel goal: Transform the SOP Matrix into an orchestration-driven deliverable assistant for that specific deliverable.";
		String brownPixelTooltipDescription = "This lesson focuses on being able to call actions that are built into your assistant.";
		String blackPixelTooltipDescription = "Advanced Automation With Your AI Assistants for Your Deliverable";

		Assert.assertEquals(actualFirstPixelText, aiRoleMapPage.getPixelLevalText(1));
		Assert.assertEquals(actualSecondPixelText, aiRoleMapPage.getPixelLevalText(2));
		Assert.assertEquals(actualThirdPixelText, aiRoleMapPage.getPixelLevalText(3));
		Assert.assertEquals(actualFourthPixelText, aiRoleMapPage.getPixelLevalText(4));
		Assert.assertEquals(actualFifthPixelText, aiRoleMapPage.getPixelLevalText(5));
		Assert.assertEquals(actualSixthPixelText, aiRoleMapPage.getPixelLevalText(6));
		Assert.assertEquals(actualSeventhPixelText, aiRoleMapPage.getPixelLevalText(7));
		Assert.assertEquals(actualEighthPixelText, aiRoleMapPage.getPixelLevalText(8));

		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(0), whitePixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(1), yellowPixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(2), orangePixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(3), greenPixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(4), bluePixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(5), purplePixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(6), brownPixelTooltipTile);
		Assert.assertEquals(aiRoleMapPage.getTitleOfPixelLessionFormToolTip(7), blackPixelTooltipTile);

		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(0), whitePixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(1), yellowPixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(2), orangePixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(3), greenPixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(4), bluePixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(5), purplePixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(6), brownPixelTooltipDescription);
		Assert.assertEquals(aiRoleMapPage.getDescriptionOfPixelLessionFormToolTip(7), blackPixelTooltipDescription);

	}

	@Test(priority = 15)
	public void verifyIButtonHoverWorkingProperlyTest() {
		Actions action = new Actions(driver);
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(1)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 1).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(2)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 2).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(3)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 3).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(4)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 4).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(5)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 5).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(6)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 6).contains("tooltip"));
		action.moveToElement(aiRoleMapPage.getIButtonOfPixelLesson(7)).perform();
		Assert.assertTrue(aiRoleMapPage.getIButtonDomAttribute("aria-describedby", 7).contains("tooltip"));
	}

	@Test(priority = 16)
	public void verifyCompetencyAndDeliverableNameComingCorrectlyFromAdminTest() {
		String competencyNameFromAdmin = prop.getProperty("CompetencyNameFromAdmin");
		String deliverableNameFromAdmin = prop.getProperty("DeliverableNameFromAdmin");
		Assert.assertEquals(aiRoleMapPage.getCompetencyHeadingText(), competencyNameFromAdmin);
		Assert.assertEquals(aiRoleMapPage.getDeliverableHeadingText(), deliverableNameFromAdmin);
	}

	@Test(priority = 17)
	public void verifyPlaceHolderOnCompetenciesEditPopupModuleTest() {
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		Assert.assertEquals(aiRoleMapPage.getOldCompitencyNameFieldPlaceholderText("placeholder"),
				"Type competencies name here");
		Assert.assertEquals(aiRoleMapPage.getNewCompitencyNameFieldPlaceholderText("placeholder"),
				"Type competencies name here");
		Assert.assertEquals(aiRoleMapPage.getCompitencyDescriptionNameFieldPlaceholderText("placeholder"),
				"Type Competencies description  here");
	}

	@Test(priority = 18)
	public void verifErrorMessageAndStarMarkForMendetoryFieldForChangeCompetencyModuleTest() {

		// All Field Empty Test
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.clearOldCompitencyNameField();
		aiRoleMapPage.clearNewCompitencyNameField();
		aiRoleMapPage.clearCompitencyDescriptionNameField();
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		List<String> errorMessages = aiRoleMapPage.getErrorMessage();
		Assert.assertEquals(errorMessages.get(0), "The competencies name field is required.");
		Assert.assertEquals(errorMessages.get(1), "The competencies name field is required.");
		Assert.assertEquals(errorMessages.get(2), "The description field is required when file is not present.");
		Assert.assertEquals(errorMessages.get(3), "The file field is required when description is not present.");

		// Error messages after filling Old Competency and New Competency Field.
		aiRoleMapPage.closePopupModule();
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.clearOldCompitencyNameField();
		aiRoleMapPage.enterTextIntoOldCompitencyNameField("Old Competency");
		aiRoleMapPage.clearNewCompitencyNameField();
		aiRoleMapPage.enterTextIntoNewCompitencyNameField("New Competency");
		aiRoleMapPage.clearCompitencyDescriptionNameField();
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		errorMessages = aiRoleMapPage.getErrorMessage();
		Assert.assertEquals(errorMessages.get(0), "The description field is required when file is not present.");
		Assert.assertEquals(errorMessages.get(1), "The file field is required when description is not present.");

		// Error messages after filling Old Description Field.
		aiRoleMapPage.closePopupModule();
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.clearOldCompitencyNameField();
		aiRoleMapPage.clearNewCompitencyNameField();
		aiRoleMapPage.clearCompitencyDescriptionNameField();
		aiRoleMapPage.enterTextIntoCompitencyDescriptionNameField("Description Field text");
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		errorMessages = aiRoleMapPage.getErrorMessage();
		Assert.assertEquals(errorMessages.get(0), "The competencies name field is required.");
		Assert.assertEquals(errorMessages.get(1), "The competencies name field is required.");

		// Error messages after filling Old Competency Field.
		aiRoleMapPage.closePopupModule();
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.clearOldCompitencyNameField();
		aiRoleMapPage.enterTextIntoOldCompitencyNameField("Old Competency");
		aiRoleMapPage.clearNewCompitencyNameField();
		aiRoleMapPage.clearCompitencyDescriptionNameField();
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		errorMessages = aiRoleMapPage.getErrorMessage();
		Assert.assertEquals(errorMessages.get(0), "The competencies name field is required.");
		Assert.assertEquals(errorMessages.get(1), "The description field is required when file is not present.");
		Assert.assertEquals(errorMessages.get(2), "The file field is required when description is not present.");
	}

	@Test(priority = 19)
	public void verifyChangingCopetencyNameWithoutFileTest() {
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.enterTextIntoNewCompitencyNameField("Api Tester");
		aiRoleMapPage
				.enterTextIntoCompitencyDescriptionNameField("Change The copetency and its deliverable accordingly");
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		aiRoleMapPage.clickOngenerateNewCompetencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Copetency has been updated.");
	}

	@Test(priority = 20)
	public void verifyChangingCopetencyNameWithFileTest() {
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.enterTextIntoNewCompitencyNameField("Api Tester");
		aiRoleMapPage
				.enterTextIntoCompitencyDescriptionNameField("Change The copetency and its deliverable accordingly");
		String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "ComitencyChangeText.txt").toString();
		if (aiRoleMapPage.isFilePathForChangeCopetencyDisplayed() == true) {
			aiRoleMapPage.sendFileIntoChangeCompetency(filePath);
		}
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		aiRoleMapPage.clickOngenerateNewCompetencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertEquals(aiRoleMapPage.getPopupMessage(), "Copetency has been updated.");
	}

	@Test(priority = 21)
	public void verifyWrongCompitencyNameInChangeCompetencyTest() {
		aiRoleMapPage.clickRandomlyOnEditCompetencyButton(CommonUtilities.getRandomNumberBetween1And8());
		postDelay(400);
		aiRoleMapPage.enterTextIntoNewCompitencyNameField("Wrong Compitency");
		aiRoleMapPage.enterTextIntoCompitencyDescriptionNameField("Do not change copetency");
		aiRoleMapPage.clickOnCreateNewCompitencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		aiRoleMapPage.clickOngenerateNewCompetencyButton();
		aiRoleMapPage.waitUntillLoaderToDisappear();
		Assert.assertNotEquals(aiRoleMapPage.getPopupMessage(), "Copetency has been updated.");
	}

	@Test(priority = 22)
	public void VerifyClickEveryDeliverableTest() {
		for (int i = 2; i <= 9; i++) {
			for (int j = 1; j <= 8; j++) {
				deliverableProgressPage = aiRoleMapPage.clickOnDeliverables(i, j);
				Assert.assertEquals(deliverableProgressPage.getAdminNoteHeading(), "Admin's Note");
				navigateBackInBrowser(deliverableProgressPage.getDriver());
				aiRoleMapPage = new AIRoleMapPage(driver);
			}
		}
	}
}
