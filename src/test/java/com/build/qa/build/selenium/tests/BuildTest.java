package com.build.qa.build.selenium.tests;

import org.junit.Assert;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.temporal.TemporalUnit;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BuildTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("HOMEPAGE"));
		driver.manage().window().fullscreen();

		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		HomePage h = new HomePage(driver,wait);
		h.close.click();
		h.searchbox.sendKeys("Quoizel MY1613"+ Keys.ENTER);
		String title = h.title.getText();
		Assert.assertEquals("Quoizel MY1613",title);
		//there is aptha on this website i cannot go forward it is not letting me go forward and we cannot automate captcha
}

	/**
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 *
	 */
	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException, AWTException {
// TODO: Implement this test
		driver.get(getConfiguration("SINKPAGE"));
		driver.manage().window().fullscreen();

		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		HomePage h = new HomePage(driver,wait);

h.popup.click();


		h.dropin.click();

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",h.itemtwo);
		h.itemtwo.click();
		h.addtocart1.click();
		h.cart1.click();
		String verifyorder = h.verifyorder.getText();

		System.out.println(verifyorder);
		Assert.assertEquals("Bachata 17-1/8\" Luster Stainless Steel Drop-in / Undermount Bathroom Sink With Overflow",verifyorder);
}



	/**
	 * Add a product to the cart and email the cart to yourself, also to my email address: test.automation+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() throws InterruptedException {
		//TODO: Implement this test
		driver.get(getConfiguration("SINKPAGE"));
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		HomePage h = new HomePage(driver,wait);
		h.popup.click();
		h.dropin.click();



		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",h.itemtwo);
		h.itemtwo.click();
		h.addtocart1.click();
		h.cart1.click();
		h.selectbox.click();
		h.emailcart.click();
		Thread.sleep(5000);

		h.yourname.sendKeys("moh");
		h.youremail.sendKeys("hamedjan819@gmail.com");
		h.recipientname.sendKeys("moh1");
		h.recpientemail.sendKeys("test.automation+SeleniumTest@build.com");
		h.emailbody.sendKeys("This is {yourName}, sending you a cart from my automation!");

		h.sendemail.click();

		String title = h.titleverify.getText();
		Assert.assertEquals(title,"Cart Sent! The cart has been submitted to the recipient via email.");
}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() throws InterruptedException {
		driver.get(getConfiguration("HOMEPAGE"));
		driver.manage().window().fullscreen();

		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		HomePage h = new HomePage(driver,wait);
		h.close.click();
		h.searchbox.sendKeys("Bathroom Faucets"+ Keys.ENTER);
        h.color.click();
        h.chromecolor.click();
		System.out.println(h.chromecolor.isDisplayed());

		Thread.sleep(4000);

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",h.theme);
        h.theme.click();
        h.modern.click();

		System.out.println(h.modern.isDisplayed());

		// TODO: Implement this test
}

}
