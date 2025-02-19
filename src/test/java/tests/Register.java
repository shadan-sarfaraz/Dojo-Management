package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class Register {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://dev.masterymade.com");
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		driver.findElement(By.xpath("//a[@class='text-primary']")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void navigateToSignUpPageThroughAllLinks() {

		Assert.assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Already have an account?')]")).getText(),
				"Already have an account? Log in here.");
	}

	@Test(priority = 2)
	public void verifyErrorMessageForMendetoryFieldsTest() {
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='The name field is required.']")).getText(),
				"The name field is required.");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='The password field is required.']")).getText(),
				"The password field is required.");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(text(),'Please accept the Terms and Privacy Policy to regi')]"))
				.getText(), "Please accept the Terms and Privacy Policy to register.");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountByProvidingMendetoryFieldsOnly() {
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Shadan Sarfaraz");
		String email = CommonUtilities.generateRandomStringWithNumber().toLowerCase();
		System.out.println(email);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email + "@team133388.testinator.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//input[@id='cpassword']")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//input[@id='termsofservices']")).click();
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Thanks for singing up!']")).getText(),
				"Thanks for singing up!");
	}

	@Test(priority = 4)
	public void verifyEmailAfterSuccessFullSignupTest() {
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Shadan Sarfaraz");
		String email = CommonUtilities.generateRandomStringWithNumber().toLowerCase();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email + "@team133388.testinator.com");
		String password = "Testing@123";
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='cpassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='termsofservices']")).click();
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Thanks for singing up!']")).getText(),
				"Thanks for singing up!");
//		CommonUtilities.verifyEmail(driver, email);
		driver.findElement(By.xpath("//a[normalize-space()='Login Here']")).click();
		driver.findElement(By.xpath("//form[@id='userLoginForm']//input[@id='email']")).clear();
		driver.findElement(By.xpath("//form[@id='userLoginForm']//input[@id='email']"))
				.sendKeys(email + "@team133388.testinator.com");
		driver.findElement(By.xpath("//form[@id='userLoginForm']//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//form[@id='userLoginForm']//input[@id='submit']")).click();
		Assert.assertEquals(driver.findElements(By.xpath("//p[contains(text(),'Hi')]")), "Hi");
	}
}
