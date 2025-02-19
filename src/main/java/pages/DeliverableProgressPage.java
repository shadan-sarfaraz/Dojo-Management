package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.root.RootPage;

public class DeliverableProgressPage extends RootPage {
	WebDriver driver;

	public DeliverableProgressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card-header fw-bold']")
	private WebElement adminNoteHeading;

	@FindBy(xpath = "//select[@name='cars']")
	private WebElement copetencyDropdown;

	@FindBy(xpath = "//div[@class='col-12 col-lg-6']//strong")
	private WebElement rolMapNameText;

	@FindBy(xpath = "(//div[@class='card-body']//a)[1]")
	private WebElement firstStepButton;

	@FindBy(xpath = "//input[@placeholder='Enter a cheat code']")
	private WebElement cheatCodeField;

	@FindBy(xpath = "(//div//div[5]//input)[3]")
	private WebElement relaventOption;

	@FindBy(xpath = "(//div//div[5]//input)[4]")
	private WebElement confidentOption;

	@FindBy(xpath = "(//div//div[5]//input)[5]")
	private WebElement newKnowledgeOption;

	@FindBy(xpath = "//textarea[@id='applicationPlan']")
	private WebElement brieflyDescribeTextArea;

	@FindBy(xpath = "//textarea[@id='challenges']")
	private WebElement potentialChallengeTextArea;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement savePixelManualButton;

	@FindBy(xpath = "(//div//button[@aria-label='Close'])[3]")
	private WebElement crossPixelManualButton;

	@FindBy(xpath = "//div[@id='openPixelManualModal']")
	private WebElement pixelManualPopupModule;

	@FindBy(xpath = "(//button[@type='button'])[15]")
	private WebElement closePixelManualButton;

	@FindBy(xpath = "(//div[@class='card-body']//div[@class='tooltip-wrapper'])[1]")
	private WebElement secondStepButton;

	@FindBy(xpath = "(//div[@class='card-body']//div[@class='tooltip-wrapper'])[2]")
	private WebElement thirdStepButton;

	@FindBy(xpath = "//div[normalize-space()='Digital Challenge Focus']")
	private WebElement secondStepHeading;

	@FindBy(xpath = "(//span[@role='combobox'])[2]")
	private WebElement digitalChallangeDropdown;

	@FindBy(xpath = "//select[@name='challenge_frequency']")
	private WebElement digitalChallangeOptions;

	@FindBy(xpath = "//input[@id='timeBeforeAI']")
	private WebElement timeToCompleteBefore;

	@FindBy(xpath = "//input[@id='timeWithAI']")
	private WebElement timeToCompleteAfter;

	@FindBy(xpath = "//input[@id='projectedTimeWithAI']")
	private WebElement projectedFutureComplitionTime;

	@FindBy(xpath = "//label[@for='efficiency5' and text()='5']")
	private WebElement aiMasteryImpact;

	@FindBy(xpath = "//select[@id='StrategicAiIntegration']")
	private WebElement strategicAIIntegerationDropdown;

	@FindBy(xpath = "//textarea[@id='AiAssistedTask']")
	private WebElement describeAIAssisted;

	@FindBy(xpath = "//textarea[@id='AiChallenges']")
	private WebElement whatAIChallenge;

	@FindBy(xpath = "//textarea[@id='AiApproach']")
	private WebElement mostSignificant;

	@FindBy(xpath = "//input[@id='yes']")
	private WebElement haveYouShareYesOption;

	@FindBy(xpath = "//input[@id='colleguesAssisted']")
	private WebElement ifYesTextArea;

	@FindBy(xpath = "//textarea[@id='accelerationJourney']")
	private WebElement whatAdditionalSupport;

	@FindBy(xpath = "//input[@id='videoUrl']")
	private WebElement videoWalkThroughURL;

	@FindBy(xpath = "(//input[@value='Submit'])[1]")
	private WebElement digitalSubmitButton;

	@FindBy(xpath = "(//input[@value='Submit'])[2]")
	private WebElement pixelSubmitButton;

	@FindBy(xpath = "//label[@for='no']")
	private WebElement haveYouShareNoOption;

	@FindBy(xpath = "//input[@aria-label='Search']")
	private WebElement inputHowFrequently;

	@FindBy(xpath = "//a[@class='text-primary']")
	private WebElement videoWalkThroughLink;

	@FindBy(xpath = "//input[@id='fileInput']")
	private WebElement sopMatrixFileField;

	@FindBy(xpath = "//select[@id='pixelLesson']")
	private WebElement pixelLessionDropdown;

	@FindBy(xpath = "//input[@id='lessonDuration']")
	private WebElement completeLession;

	@FindBy(xpath = "//textarea[@id='journeyContribution']")
	private WebElement masteryJourny;

	@FindBy(xpath = "//label[@for='readyYes']")
	private WebElement yesNextPixel;

	@FindBy(xpath = "//label[@for='readyNo']")
	private WebElement noNextPixel;

	@FindBy(xpath = "//textarea[@id='preparedness']")
	private WebElement pixelLessionTextArea;

	@FindBy(xpath = "//label[@for='preparedness']")
	private WebElement feelPrepearedHeading;

	@FindBy(xpath = "//textarea[@id='additionalSupport']")
	private WebElement continousImprovementTextArea;

	@FindBy(xpath = "//div[@class='col-12 col-lg-6']//strong")
	private WebElement headingText;

	@FindBy(xpath = "//div[normalize-space()='Doloribus veritatis doloremque officiis deleniti, voluptate iusto perferendis dolorum ea repudiandae id autem odit debitis beatae itaque illum dolor sapiente qui iure.']")
	private WebElement adminNotes;

	@FindBy(xpath = "//a[normalize-space()='HomeWork Overview Video']")
	private WebElement homeworkOverviewVideo;

	public CompanyVideoPage clickHomeworkOverviewVideo() {
		homeworkOverviewVideo.click();
		return new CompanyVideoPage(driver);
	}

	public String getAdminNote() {
		return adminNotes.getText();
	}

	public boolean isHeadingDisplayed() {
		return headingText.isDisplayed();
	}

	public String getFeelPrepearedHeadingText() {
		return feelPrepearedHeading.getText();
	}

	public void enterTextIntoContinousImprovementTextArea(String text) {
		continousImprovementTextArea.sendKeys(text);
	}

	public void enterTexWhatMakeYouFeelTextArea(String text) {
		pixelLessionTextArea.sendKeys(text);
	}

	public void clickNoNextPixelLevel() {
		noNextPixel.click();
	}

	public void clickYesNextPixelLevel() {
		yesNextPixel.click();
	}

	public void enterTextIntoMasteryJourny(String text) {
		masteryJourny.sendKeys(text);
	}

	public void enterMinuteIncompleteThisLession(String minute) {
		completeLession.sendKeys(minute);
	}

	public void selectValueFromPixelLessionDropdown(int index) {
		Select select = new Select(pixelLessionDropdown);
		select.selectByIndex(index);
	}

	public void enterFileInSOPMatrixField(String filePath) {
		sopMatrixFileField.sendKeys(filePath);
	}

	public DashboardPage clickOnVideoWalkThroughLink() {
		videoWalkThroughLink.click();
		return new DashboardPage(driver);
	}

	public void enterVlaueInHowFrequentlyDropdown(String value) {
		inputHowFrequently.sendKeys(value);
	}

	public void clickOnPixelSubmitButton() {
		pixelSubmitButton.click();
	}

	public void clickOnDigitalSubmitButton() {
		digitalSubmitButton.click();
	}

	public void enterVideoWalkThroughURL(String text) {
		videoWalkThroughURL.sendKeys(text);
	}

	public void enterTextWhatAdditionalSupport(String text) {
		whatAdditionalSupport.sendKeys(text);
	}

	public void enterTextIntoIfYes(String text) {
		ifYesTextArea.sendKeys(text);
	}

	public void clickNoOnHaveYouShared() {
		haveYouShareNoOption.click();
	}

	public void clickYesOnHaveYouShared() {
		haveYouShareYesOption.click();
	}

	public void enterTextIntoMostSignificant(String text) {
		mostSignificant.sendKeys(text);
	}

	public void enterTextIntoWhatAIChallenge(String text) {
		whatAIChallenge.sendKeys(text);
	}

	public void enterTextIntoDescribeAIAssisted(String text) {
		describeAIAssisted.sendKeys(text);
	}

	public void selectstrategicAIIntegeration(int index) {
		Select select = new Select(strategicAIIntegerationDropdown);
		select.selectByIndex(index);
	}

	public void click5thAIMasteryImpact() {
		Actions action = new Actions(driver);
		action.moveToElement(aiMasteryImpact).click().perform();
		aiMasteryImpact.click();
	}

	public void enterProjectedFutureComplitionTime(String time) {
		projectedFutureComplitionTime.sendKeys(time);
	}

	public void enterTimeInTimeToCompleteAfter(String time) {
		timeToCompleteAfter.sendKeys(time);
	}

	public void enterTimeInTimeToCompleteBefore(String time) {
		timeToCompleteBefore.sendKeys(time);
	}

	public void selectDigitalChallengeOptionsByIndex(int option) {
		Select select = new Select(digitalChallangeOptions);
		select.selectByIndex(option);
	}

	public void selectDigitalChallengeOptions(String option) {
		Select select = new Select(digitalChallangeOptions);
		select.selectByValue(option);
	}

	public void clcikOnDigitalChallangeDropdown() {
		digitalChallangeDropdown.click();
	}

	public String getSecondStepHeading() {
		return secondStepHeading.getText();
	}

	public void clickOnSecondStep() {
		secondStepButton.click();
	}

	public void clickOnThirdStep() {
		thirdStepButton.click();
	}

	public String getPixelManualDOMAttribute(String attributeName) {
		return pixelManualPopupModule.getDomAttribute(attributeName);
	}

	public void clickOnPixelManualCloseButton() {
		closePixelManualButton.click();
	}

	public void clickOnPixelManualCrossButton() {
		crossPixelManualButton.click();
	}

	public void clickOnSavePixelManual() {
		savePixelManualButton.click();
	}

	public void enterTextIntoPotentialChallengeTextArea(String text) {
		potentialChallengeTextArea.sendKeys(text);
	}

	public void enterTextIntoBrieflyDescribeTextArea(String text) {
		brieflyDescribeTextArea.sendKeys(text);
	}

	public void clickOnNewKnowledgeOption() {
		newKnowledgeOption.click();
	}

	public void clickOnConfidentOption() {
		confidentOption.click();
	}

	public void clickOnRelaventOption() {
		relaventOption.click();
	}

	public void enterTextIntoCheatCodeField(String code) {
		cheatCodeField.sendKeys(code);
	}

	public void clickOnFirstStepToEarnPixel() {
		firstStepButton.click();
	}

	public String getRoleMapName() {
		return rolMapNameText.getText();
	}

	public String getAdminNoteHeading() {
		return adminNoteHeading.getText();
	}

	public void selectCmpetencyOption(String optionText) {
		Select select = new Select(copetencyDropdown);
		select.selectByVisibleText(optionText);
	}

	public String getSelectedCompetencyText() {
		Select select = new Select(copetencyDropdown);
		return select.getFirstSelectedOption().getText().trim();
	}

	public List<String> getCompetencyValueFromDropdown() {
		List<String> validOptions = new ArrayList<>();
		Select select = new Select(copetencyDropdown);

		for (WebElement option : select.getOptions()) {
			if (option.getText().trim().isEmpty() || !option.isEnabled()) {
				continue;
			}
			validOptions.add(option.getText().trim());
		}
		return validOptions;
	}

}
