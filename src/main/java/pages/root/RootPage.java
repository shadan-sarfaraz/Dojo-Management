package pages.root;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtilities;

public class RootPage {
	WebDriver driver;
	public ElementUtilities elementUtilities;
	WebDriverWait wait;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(id = "fullScreenLoader")
	private WebElement loader;

	@FindBy(xpath = "//div[@class='noty_body']")
	private WebElement popupModuleForSucess;

	@FindBy(xpath = "//div[@class='noty_close_button']")
	private WebElement crossButton;

	@FindBy(xpath = "//span[@class='text-strong d-block text-danger error']")
	private List<WebElement> errorMessageForPopupModule;

	@FindBy(xpath = "//div[@class='modal fade show']//button[@class='btn-close']")
	private WebElement crossButtonForPopupModule;

	@FindBy(xpath = "//video[@id='myVideo']")
	private WebElement videoElement;

	public void playAndWaitForVideoToComplete() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", videoElement);
		// Play the video (if not already playing)
		js.executeScript("if (arguments[0].paused) { arguments[0].play(); }", videoElement);

		// Ensure the video doesn't loop
		js.executeScript("arguments[0].loop = false;", videoElement);

		// Wait until the video has finished playing and exit immediately
		new WebDriverWait(driver, Duration.ofSeconds(1800))
				.until(driver -> (Boolean) js.executeScript("return arguments[0].ended;", videoElement));

		// Video has finished, exit the method immediately after this line
		System.out.println("Video has finished playing.");
	}

	public List<String> getErrorMessage() {
		List<String> errorMessages = new ArrayList<>();
		for (WebElement error : errorMessageForPopupModule) {
			if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
				errorMessages.add(error.getText().trim());
			}
		}

		return errorMessages;
	}

	public void closePopupModule() {
		crossButtonForPopupModule.click();
	}

	public void closePopupMessage() {
		crossButton.click();
	}

	public String getPopupMessage() {
		return popupModuleForSucess.getText();
	}

	public void waitUntillLoaderToDisappear() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(loader));
	}

	public void closeNewWindows(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();
		driver.getWindowHandles().stream().filter(w -> !w.equals(mainWindow)).forEach(w -> {
			driver.switchTo().window(w);
			driver.close();
		});
		driver.switchTo().window(mainWindow);
	}

}
