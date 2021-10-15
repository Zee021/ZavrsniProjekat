package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	String firstName = "Simbad";
	String lastName = "Moreplovac";
	String address = "Donjeobalska 19";
	String phone = "0631234567";
	String zipCode = "12345";
	String country = "United States";
	String state = "Iowa";
	String city = "Ames";

	String loginMessage = "Login Successfull";
	String loginMessageFail = "[ERROR] The Login Successfull message is not visible.";
	String setupMessage = "Setup Successful";
	String setupMessageFail = "[ERROR] The Setup Successfull message is not visible.";
	String logoutMessage = "Logout Successfull!";
	String logoutMessageFail = "[ERROR] The Logout Successfull message is not visible.";
	String imageMessage = "Profile Image Uploaded Successfully";
	String imageMessageFail = "[ERROR] Image Upload failed";
	String imageDelete = "Profile Image Deleted Successfully";
	String imageDeleteFail = "[ERROR] Profile Image Deleted Failed";

	@Test(priority = 1)
	public void editProfile() throws InterruptedException {

		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		Thread.sleep(2000);
		this.popUpPage.closePopUp();
		Thread.sleep(2000);
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(loginMessage), loginMessageFail);
		this.driver.navigate().to(baseUrl + "member/profile");
		this.profilePage.changeBasicInfo(firstName, lastName, address, phone, zipCode, country, state, city);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(setupMessage), setupMessageFail);
		this.authPage.logout();
		Assert.assertTrue(this.notificationPage.getMessageText().contains(logoutMessage), logoutMessageFail);
	}

	@Test(priority = 2)
	public void changeImg() throws IOException, InterruptedException {

		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		Thread.sleep(2000);
		this.popUpPage.closePopUp();
		Thread.sleep(2000);
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(loginMessage), this.loginMessageFail);
		this.notificationPage.waitUntilNoticeDisappear();

		this.driver.navigate().to(baseUrl + "/member/profile");
		String imgPath = new File("img/profilePhoto.jpg").getCanonicalPath();
		this.profilePage.uploadImage(imgPath);
		Assert.assertTrue(this.notificationPage.getMessageText().contains(imageMessage), this.imageMessageFail);
		this.notificationPage.waitUntilNoticeDisappear();

		this.profilePage.removeImage();
		Assert.assertTrue(this.notificationPage.getMessageText().contains(imageDelete), this.imageDeleteFail);
		this.notificationPage.waitUntilNoticeDisappear();

		this.authPage.logout();
		Assert.assertTrue(this.notificationPage.getMessageText().contains(logoutMessage), this.logoutMessageFail);
		this.notificationPage.waitUntilNoticeDisappear();
	}
}
