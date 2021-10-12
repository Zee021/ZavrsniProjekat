package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAdress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement selectCountry = this.driver.findElement(By.id("user_country_id"));
		Select country = new Select(selectCountry);
		return country;
	}

	public Select getState() {
		WebElement selectState = this.driver.findElement(By.id("user_state_id"));
		Select state = new Select(selectState);
		return state;
	}

	public Select getCity() {
		WebElement selectCity = this.driver.findElement(By.id("user_city"));
		Select city = new Select(selectCity);
		return city;
	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public WebElement getUploadButton() {
		return this.driver.findElement(By.xpath("//*[@class = 'upload uploadFile-Js']"));
	}

	public WebElement getUploadInput() {
		return this.driver.findElement(By.xpath("//*[@id = 'form-upload']/input"));
	}

	public void uploadImage(String imgPath) {
		js.executeScript("arguments[0].click();", this.getUploadButton());
		this.getUploadInput().sendKeys(imgPath);
	}

	public WebElement getRemoveImageButton() {
		return this.driver.findElement(By.className("remove"));
	}

	public void removeImage() {
		js.executeScript("arguments[0].click();", this.getRemoveImageButton());
	}

	public void changeBasicInfo(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		Thread.sleep(1000);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		Thread.sleep(1000);
		this.getAdress().clear();
		this.getAdress().sendKeys(address);
		Thread.sleep(1000);
		this.getPhoneNo().clear();
		this.getPhoneNo().sendKeys(phone);
		Thread.sleep(1000);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		Thread.sleep(1000);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(2000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(2000);
		this.getCity().selectByVisibleText(city);
		Thread.sleep(2000);
		this.getSaveButton().click();
	}
}
