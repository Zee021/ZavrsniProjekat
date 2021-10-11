package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);

	}

	public WebElement getLocationHeader() {
		return this.driver.findElement(By.xpath("//*[@class = 'location-selector']"));

	}

	public WebElement getCloseElement() {
		return this.driver.findElement(By.xpath("//*[@class = 'close-btn close-btn-white']"));
	}
	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	public WebElement getLocationInput() {
		return this.driver.findElement(By.id("location_id"));
	}
	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));

	}
	public void openPopup() {
		this.getLocationHeader().click();
	}
	
	
	public void setLocation() {
		
	}
	
	public void closePopup() {
		this.getCloseElement().click();
	}
	
}
