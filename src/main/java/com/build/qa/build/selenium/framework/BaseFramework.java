package com.build.qa.build.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static Properties configuration;



	@FindBy(id = "search_txt")
	public WebElement searchbox;

	@FindBy(className = "inline-block pad-right")
	public WebElement title;

	@FindBy(className = "close-icon")
	public WebElement close;


	@FindBy(xpath = "(//div[@class=\"c-tile-bg-image c-group-item-bg-image \"])[1]")
	public WebElement dropin;


	@FindBy(xpath = "(//a[@href=\"/kohler-k-2609-su/s911329?uid=2285063\"])[2]")
	public  WebElement itemtwo;

	@FindBy(xpath = "//div[contains(text(),'Add to Cart')")
	public WebElement addtocart;

    @FindBy(css = "button[data-original-title='Add to Cart']")
	public WebElement addtocart1;

@FindBy(xpath = "(//button[@data-automation='close'])[2]")
public WebElement popup;

@FindBy(xpath = "//span[@style='left:3px']")
public WebElement cart;

@FindBy(css = "span[class='fw2 lh-title']")
public WebElement verifyorder;

@FindBy(css = "a[onclick=\"s_objectID='1';\"]")
public WebElement cart1;

@FindBy(xpath = "//div[@data-component-type=\"selectBox\"]")
public WebElement selectbox;

@FindBy(xpath = "//li[@onclick=\"s_objectID='Finish select Email Cart';\"]")
public WebElement emailcart;

@FindBy(css = "input[data-automation='your-name-field']")
public WebElement yourname;

@FindBy(css = "input[data-automation='your-email-field']")
public WebElement youremail;

@FindBy(css = "input[data-automation='recipient-name-field']")
public WebElement recipientname;

@FindBy(css = "input[data-automation='recipient-email-field']")
public WebElement recpientemail;

@FindBy(xpath = "//textarea[@id='quoteMessage']")
public WebElement emailbody;

@FindBy(css = "button[data-automation='confirm-email-cart-button']")
public WebElement sendemail;

@FindBy(css = "li[class=\"theme-success list pt f4-ns ma0 tc\"]")
public WebElement titleverify;

@FindBy(css = "li[data-groupname='Colors']")
public WebElement color;

@FindBy(xpath = "//label[@data-facet-value='Chromes']/input")
public WebElement chromecolor;

@FindBy(css = "li[data-groupname='Theme']")
public WebElement theme;

@FindBy(xpath = "(//label[@data-facet-value='Modern']/input)[1]")
public WebElement modern;

@FindBy(xpath = "(//span[@data-automation='limit-facet-item'])[1]")
public WebElement verifychrome;

@FindBy(xpath = "(//span[@data-automation='limit-facet-item'])[2]")
public WebElement verifymodern;




	@Rule
	public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;

		LOG.info("Loading in configuration file.");
		input = new FileInputStream(new File(CONFIG_FILE));
		configuration.loadFromXML(input);
		input.close();
	}

	@Before
	public void setUpBefore() {
		DesiredCapabilities capabilities;
		// Which driver to use?
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.chrome();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(capabilities);
		} else if (DRIVER_FIREFOX.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.firefox();
			driver = new FirefoxDriver(capabilities);
		}
		// Define fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected String getConfiguration(String config) {
		return configuration.getProperty(config);
	}

	@After
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
//		driver.quit();
//		driver = null;
	}
}
