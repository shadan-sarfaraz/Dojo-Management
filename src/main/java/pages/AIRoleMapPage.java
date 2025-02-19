package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.root.RootPage;

public class AIRoleMapPage extends RootPage {

	WebDriver driver;

	public AIRoleMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private WebElement getDeliverable(int row, int column) {
		String dynamicXPath = String.format("//tbody/tr[%d]/td[%d]", row, column);
		return driver.findElement(By.xpath(dynamicXPath));

	}

	private WebElement getPixelLevalElement(int pixelNumber) {
		String dynamicXPath = String.format("//div[@aria-label='Basic mixed styles example']//button[%d]", pixelNumber);
		return driver.findElement(By.xpath(dynamicXPath));

	}

	private WebElement copetencyEditButton(int buttonNumber) {
		String dynamicXPath = String.format("//thead//tr//th[%d]//i", buttonNumber);
		return driver.findElement(By.xpath(dynamicXPath));

	}

	@FindBy(xpath = "//p[@class='fw-bold']")
	private WebElement heading;

	@FindBy(xpath = "//div[@class='row dojoAiRole']")
	private WebElement roleMapDiv;

	@FindBy(xpath = "//select[@id='rolePlayId']")
	private WebElement roleMapDropDown;

	@FindBy(xpath = "//a[normalize-space()='Edit deliverable' or normalize-space()='Save deliverable']")
	private WebElement saveAndEditDeliverableButton;

	@FindBy(xpath = "//div[@class='content-area']//strong[1]")
	private WebElement displayName;

	@FindBy(xpath = "//h5[@id='modalTitleId' and normalize-space()='Edit Deliverable']")
	private WebElement editModuleHeading;

	@FindBy(xpath = "//textarea[@id='roleName']")
	private WebElement editDeliverableModuleTextArea;

	@FindBy(xpath = "//input[@value='Update']")
	private WebElement deliverableModuleUpdateButton;

	@FindBy(xpath = "//a[normalize-space()='Create']")
	private WebElement createButton;

	@FindBy(xpath = "//div[@id='createRolePlan']//h5[@id='modalTitleId']")
	private WebElement popUpModuleHeading;

	@FindBy(xpath = "//input[@id='RolePlan']")
	private WebElement rolePlanTextArea;

	@FindBy(xpath = "//textarea[@id='descriptionRole']")
	private WebElement rolePlanDescriptionTextArea;

	@FindBy(xpath = "//form[@class='rolePlanFormData']//input[@id='submit']")
	private WebElement createButtonOnPopupModule;

	@FindBy(xpath = "//span[@id='generateNow' and text()='Generate Now']")
	private WebElement generateButtonOnCreateRoleMapPopupModule;

	@FindBy(xpath = "//div[@class='row col-l2 px-3 competenciesShow']//p")
	private WebElement invalidRoleMapMessageInPopupModule;

	@FindBy(xpath = "//form[@class='rolePlanFormData']//input[@id='submit' and @value='Regenerate']")
	private WebElement regenerateButtonInPopupmoduleOfCreateAIRoleMap;

	@FindBy(xpath = "//form[@class='rolePlanFormData']//input[@id='file']")
	private WebElement fileFieldInCreateRoleMapPopModule;

	@FindBy(xpath = "//span[text()='No Role Map Created, Create a role map ']")
	private WebElement noRoleMapCreateText;

	@FindBy(xpath = "//div[@class='row dojoAiRole']//a[text()='Create']")
	private WebElement firstTimeRoleMapCreateButton;

	@FindBy(xpath = "//div[@id='createRolePlan']")
	private WebElement createRolePlanModule;

	@FindBy(xpath = "//div[@id='EditModel']")
	private WebElement editDeliverableModule;

	@FindBy(xpath = "//form[@class='rolePlanFormData']//button[@type='button'][normalize-space()='Close']")
	private WebElement createAIRoleMapModuleCloseButton;

	@FindBy(xpath = "//div[@id='EditModel']//button[@class='btn btn-secondary']")
	private WebElement editDeleverableModuleCloseButton;

	@FindBy(xpath = "//i[contains(@class, 'bi-info-circle')]")
	private List<WebElement> tooltipElements;

	@FindBy(xpath = "//button[@class='btn border-0 p-2']//i[1]")
	private List<WebElement> iButtons;

	@FindBy(xpath = "//thead//tr[1]//th[1]")
	private WebElement competencyHeadingText;

	@FindBy(xpath = "//tbody//tr[1]//th[1]")
	private WebElement deliverableHeadingText;

	@FindBy(xpath = "//input[@name='competencies_name']")
	private WebElement oldCompitencyNameField;

	@FindBy(xpath = "//input[@name='new_competencies_name']")
	private WebElement newCompitencyNameField;

	@FindBy(xpath = "//div[@id='changeCompetenciesModal']//textarea[1]")
	private WebElement compitencyDescriptionNameField;

	@FindBy(xpath = "//form[@class='ChangeCompetenciesForm']//input[@id='file']")
	private WebElement fileFieldForChangeCompetency;

	@FindBy(xpath = "//form[@class='ChangeCompetenciesForm']//input[@id='submit']")
	private WebElement createNewCompitencyButton;

	@FindBy(xpath = "//button[@id='generate_now']")
	private WebElement generateNewCompetencyButton;

	@FindBy(xpath = "//form[@class='ChangeCompetenciesForm']//input[@id='file']")
	private WebElement fileFieldForChangeCompitency;

	@FindBy(xpath = "//div[@class='airole-header']")
	private WebElement headingElement;

	@FindBy(xpath = "//thead//tr[2]//th")
	private List<WebElement> allCompitency;

	@FindBy(xpath = "//tbody//tr//td")
	private List<WebElement> allDeliverable;
//	
//	public DeliverableProgressPage clickDeliverableByStepDeliverable(String attributeName) {
//	    int row = 2, column = 1;
//
//	    for (WebElement tdElement : allDeliverable) {
//	        for (WebElement aTag : tdElement.findElements(By.tagName("a"))) {
//	            if (attributeName.equalsIgnoreCase(aTag.getDomAttribute("data-bs-original-title"))) {
//	                clickOnDeliverables(row, column);
//	                return new DeliverableProgressPage(driver);
//	            }
//	        }
//
//	        if (++column > 8) {
//	            column = 1;
//	            if (++row > 9) break;
//	        }
//	    }
//	    return new DeliverableProgressPage(driver);
//	}

	public DeliverableProgressPage clickDeliverableByStepDeliverable(String attributeName) {
		int row = 2;
		int column = 1;

		for (WebElement tdElement : allDeliverable) {
			List<WebElement> anchorTags = tdElement.findElements(By.tagName("a"));

			boolean hasMatchingTitle = false;
			for (WebElement aTag : anchorTags) {
				String aTitle = aTag.getDomAttribute("data-bs-original-title");
				if (aTitle != null && aTitle.trim().equalsIgnoreCase(attributeName.trim())) {
					hasMatchingTitle = true;
					break;
				}
			}
			if (hasMatchingTitle) {
				clickOnDeliverables(row, column);
				break;
			}

			if (row == 9 && column == 8) {
				break;
			}

			if (column >= 8) {
				row++;
				column = 1;
				if (row > 9)
					break;
			} else {
				column++;
			}
			System.out.println(column);
			System.out.println(row);
		}

		return new DeliverableProgressPage(driver);
	}

	public DeliverableProgressPage clickOnFreshDeliverable() {
		int row = 2;
		int column = 1;
		for (WebElement tdElement : allDeliverable) {
			List<WebElement> anchorTags = tdElement.findElements(By.tagName("a"));

			if (anchorTags.isEmpty()) {
				clickOnDeliverables(row, column);
				break;
			}
			if (column >= 8) {
				row++;
				column = 1;
				if (row > 9)
					break;
			} else {
				column++;
			}
		}
		return new DeliverableProgressPage(driver);
	}

	public List<String> getCompetencies() {
		return allCompitency.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public List<String> getCompetencyAndItsDeliverable() {
		return allDeliverable.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public boolean isHeadingDisplayed() {
		return headingElement.isDisplayed();
	}

	public DeliverableProgressPage clickOnDeliverables(int row, int column) {
		getDeliverable(row, column).click();
		return new DeliverableProgressPage(driver);
	}

	public boolean isFilePathForChangeCopetencyDisplayed() {
		boolean b = false;
		try {
			b = fileFieldForChangeCompitency.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public void sendFileIntoChangeCompetency(String filePath) {
		fileFieldForChangeCompitency.clear();
		fileFieldForChangeCompitency.sendKeys(filePath);
	}

	public void clickOngenerateNewCompetencyButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", generateNewCompetencyButton);
		generateNewCompetencyButton.click();
	}

	public void clickOnCreateNewCompitencyButton() {
		createNewCompitencyButton.click();
	}

	public String getCompitencyDescriptionNameFieldPlaceholderText(String attributeName) {
		return compitencyDescriptionNameField.getDomAttribute(attributeName);
	}

	public String getOldCompitencyNameFieldPlaceholderText(String attributeName) {
		return oldCompitencyNameField.getDomAttribute(attributeName);
	}

	public String getNewCompitencyNameFieldPlaceholderText(String attributeName) {
		return newCompitencyNameField.getDomAttribute(attributeName);
	}

	public void enterTextIntoCompitencyDescriptionNameField(String copitenciyDescription) {
		compitencyDescriptionNameField.clear();
		compitencyDescriptionNameField.sendKeys(copitenciyDescription);
	}

	public void enterTextIntoNewCompitencyNameField(String newCompitency) {
		newCompitencyNameField.clear();
		newCompitencyNameField.sendKeys(newCompitency);
	}

	public void enterTextIntoOldCompitencyNameField(String oldCompitency) {
		oldCompitencyNameField.clear();
		oldCompitencyNameField.sendKeys(oldCompitency);
	}

	public void clearCompitencyDescriptionNameField() {
		compitencyDescriptionNameField.clear();
	}

	public void clearNewCompitencyNameField() {
		newCompitencyNameField.clear();
	}

	public void clearOldCompitencyNameField() {
		oldCompitencyNameField.clear();
	}

	public void clickRandomlyOnEditCompetencyButton(int randomButtonNumber) {
		copetencyEditButton(randomButtonNumber).click();
	}

	public String getDeliverableHeadingText() {
		return deliverableHeadingText.getText();
	}

	public String getCompetencyHeadingText() {
		return competencyHeadingText.getText();
	}

	public String getIButtonDomAttribute(String attributeName, int iButton) {
		return iButtons.get(iButton).getDomAttribute(attributeName);
	}

	public WebElement getIButtonOfPixelLesson(int iButton) {
		return iButtons.get(iButton);
	}

	public String getTitleOfPixelLessionFormToolTip(int pixelNumber) {
		String title = tooltipElements.get(pixelNumber).getDomAttribute("data-bs-title").replaceAll("<[^>]+>", "")
				.trim().split("\n", 2).length > 0
						? tooltipElements.get(pixelNumber).getDomAttribute("data-bs-title").replaceAll("<[^>]+>", "")
								.trim().split("\n", 2)[0].trim()
						: "Title not found";
		return title;
	}

	public String getDescriptionOfPixelLessionFormToolTip(int pixelNumber) {
		String description = tooltipElements.get(pixelNumber).getDomAttribute("data-bs-title").replaceAll("<[^>]+>", "")
				.trim().split("\n", 2).length > 1
						? tooltipElements.get(pixelNumber).getDomAttribute("data-bs-title").replaceAll("<[^>]+>", "")
								.trim().split("\n", 2)[1].trim()
						: "Description not found";
		return description;
	}

	public void clickOnCloseButtonOnEditDeliverablePopupModule() {
		editDeleverableModuleCloseButton.click();
	}

	public void clickOnCloseButtonOnAIRoleMapCreatePopupModule() {
		createAIRoleMapModuleCloseButton.click();
	}

	public String getDomAttributeForPopupModuleOfEditDeliverable(String attributeName) {
		return editDeliverableModule.getDomAttribute(attributeName);
	}

	public String getDomAttributeForPopupModuleOfCreateAIRoleMap(String attributeName) {
		return createRolePlanModule.getDomAttribute(attributeName);
	}

	public void clickOnFirstTimeRoleMapCreateButton() {
		firstTimeRoleMapCreateButton.click();
	}

	public Boolean isNoRoleMapTextDisplayed() {
		boolean b = false;
		try {
			b = noRoleMapCreateText.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public Boolean isFilePathFieldDisplayed() {
		boolean b = false;
		try {
			b = fileFieldInCreateRoleMapPopModule.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public void sendFielIntoCreateRoleMapSection(String filePath) {
		fileFieldInCreateRoleMapPopModule.sendKeys(filePath);
	}

	public void clearTextFromRoleNameField() {
		rolePlanTextArea.clear();
	}

	public void clearTextFromRoleDescriptionField() {
		rolePlanDescriptionTextArea.clear();
	}

	public void clickOnDeliverableUpdateButtonFromPopupModule() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				deliverableModuleUpdateButton);
		deliverableModuleUpdateButton.click();
	}

	public void clickOnRegenerateButtonOnPopupModule() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				regenerateButtonInPopupmoduleOfCreateAIRoleMap);
		regenerateButtonInPopupmoduleOfCreateAIRoleMap.click();
	}

	public Boolean isInvalidRoleMapMessageDisplayedInPopupModule() {
		boolean b = false;
		try {
			b = regenerateButtonInPopupmoduleOfCreateAIRoleMap.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public void clickOnGenerateButtonOnCreateRoleMapPopUpModule() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				generateButtonOnCreateRoleMapPopupModule);
		generateButtonOnCreateRoleMapPopupModule.click();
	}

	public void clickOnCreateButtonFromPopupmoduleOfCreateRoleMap() {
		createButtonOnPopupModule.click();
	}

	public void enterTextIntoRolePlanDescriptionTextAreaPopupModule(String roleMapDescription) {
		rolePlanDescriptionTextArea.sendKeys(roleMapDescription);
	}

	public void enterTextIntoRolePlanTextAreaPopupModule(String roleMapName) {
		rolePlanTextArea.sendKeys(roleMapName);
	}

	public String getCreatePopupModuleHeadingText() {
		return popUpModuleHeading.getText();
	}

	public void clickOnCreateButton() {
		createButton.click();
	}

	public void enterTextIntoEditDeliverableModuleTextArea(String deliverable) {
		editDeliverableModuleTextArea.sendKeys(deliverable);
	}

	public void clearEditDeliverableModuleTextArea() {
		editDeliverableModuleTextArea.clear();
	}

	public String getModuleHeadingText() {
		return editModuleHeading.getText();
	}

	public String getPixelLevalText(int row) {
		return getPixelLevalElement(row).getText();
	}

	public String getDeliverableText(int row, int column) {
		return getDeliverable(row, column).getText();
	}

	public void clickDeliverableForEditing(int row, int column) {
		getDeliverable(row, column).click();
	}

	public String getDisplayedUserName() {
		return displayName.getText();
	}

	public void clickSaveAndEditDeliverableButton() {
		saveAndEditDeliverableButton.click();
	}

	public String getSaveAndEditDeliverableButtonText() {
		return saveAndEditDeliverableButton.getText();
	}

	public List<String> getValidDropdownOptions() {
		List<String> validOptions = new ArrayList<>();
		Select select = new Select(roleMapDropDown);

		for (WebElement option : select.getOptions()) {
			if (option.getText().trim().isEmpty() || !option.isEnabled()) {
				continue; // Skip null/empty or disabled options
			}
			validOptions.add(option.getText().trim());
		}
		return validOptions;
	}

	public void selectDropdownOption(String optionText) {
		Select select = new Select(roleMapDropDown);
		select.selectByVisibleText(optionText);
	}

	public String getSelectedRoleMapOptionText() {
		Select select = new Select(roleMapDropDown);
		return select.getFirstSelectedOption().getText().trim();
	}

	public String getStyle(String attributeName) {
		return roleMapDiv.getDomAttribute(attributeName);
	}

	public String getHeadingText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(heading));
		return heading.getText();
	}

}
