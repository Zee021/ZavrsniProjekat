package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {
	String locationMessage1 = "The Following Errors Occurred:";
	String locationMessage2 = "Please Select Location";
	String locationMessageFail = "[ERROR] No message displayed";
	String cartMessage = "Meal Added To Cart";
	String cartMessageFail = "[ERROR] Adding to cart was unsuccessful";
	String favoriteMessage = "Please login first!";
	String favoriteMessageFail = "[ERROR]: 'Please login first!' message was not displayed.";
	String favoriteMealMessage = "Product has been added to your favorites";
	String favoriteMealMessageFail = "[ERROR] Product has not been added to your favorites";
	String mealMessage = "Meal Added To Cart";
	String mealMessageFail = "[ERROR] The Meal Added To Cart message is not visible.";
	String cartRemoveMessage = "All meals removed from Cart successfully";
	String cartRemoveMessageError = "[ERROR] Items have not been removed";

	@Test(priority = 1)
	public void mealItemTest() throws InterruptedException {
		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(2000);
		this.popUpPage.closePopUp();
		Thread.sleep(2000);
		this.mealPage.addMealToCart("1");
		Thread.sleep(2000);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(locationMessage1), locationMessageFail);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(locationMessage2), locationMessageFail);
		this.notificationPage.waitUntilNoticeDisappear();
		Thread.sleep(2000);
		this.popUpPage.openPopUp();
		Thread.sleep(2000);
		this.popUpPage.setLocation(locationName);
		Thread.sleep(2000);
		this.mealPage.addMealToCart("1");
		Assert.assertTrue(notificationPage.getMessageText().contains(cartMessage), cartMessageFail);

	}

	@Test(priority = 2)
	public void addMealToFavourite() throws InterruptedException {
		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(2000);
		this.popUpPage.closePopUp();
		Thread.sleep(2000);
		this.mealPage.addToFavourite();
		Thread.sleep(2000);
		Assert.assertTrue(notificationPage.getMessageText().contains(favoriteMessage), favoriteMessageFail);
		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		Thread.sleep(2000);
		this.loginPage.login(email, password);
		Thread.sleep(2000);
		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.mealPage.addToFavourite();
		Thread.sleep(2000);
		Assert.assertTrue(notificationPage.getMessageText().contains(favoriteMealMessage), favoriteMealMessageFail);
		this.notificationPage.waitUntilNoticeDisappear();
	}

	@Test(priority = 3)
	public void clearCart() throws InterruptedException, IOException {

		this.driver.navigate().to(baseUrl + "meals");
		Thread.sleep(2000);
		this.popUpPage.setLocation(locationName);
		Thread.sleep(2000);
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheetMeal = wb.getSheet("Meals");
		SoftAssert sa = new SoftAssert();
		for (int i = 1; i <= sheetMeal.getLastRowNum(); i++) {
			String mealCell = sheetMeal.getRow(i).getCell(0).getStringCellValue();
			driver.navigate().to(mealCell);
			Thread.sleep(2000);
			this.mealPage.addMealToCart("1");
			Thread.sleep(3000);
			Assert.assertTrue(notificationPage.getMessageText().contains(mealMessage), mealMessageFail);
		}

		this.summaryPage.clearAllinCart();
		Assert.assertTrue(notificationPage.getMessageText().contains(cartRemoveMessage), cartRemoveMessageError);
		sa.assertAll();
	}
}