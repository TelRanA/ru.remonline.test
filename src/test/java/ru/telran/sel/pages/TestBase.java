package ru.telran.sel.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import ru.telran.sel.LoginData;
import ru.telran.sel.util.PropertyLoader;
import ru.telran.sel.util.Browser;
import ru.telran.sel.webdriver.WebDriverFactory;

/*
 * Base class for all the test classes
 * 
 * @author Sebastiano Armeli-Battana
 */

public class TestBase {
	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	protected WebDriver driver;

	protected String gridHubUrl;

	protected String baseUrl;

	protected Browser browser;
	public boolean acceptNextAlert = true;

	@BeforeClass
	public void init() {
		baseUrl = PropertyLoader.loadProperty("site.url");
		gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

		browser = new Browser();
		browser.setName(PropertyLoader.loadProperty("browser.name"));
		browser.setVersion(PropertyLoader.loadProperty("browser.version"));
		browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

		String username = PropertyLoader.loadProperty("user.username");
		String password = PropertyLoader.loadProperty("user.password");
		
		driver = WebDriverFactory.getInstance(gridHubUrl, browser, username,
				password);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected void exitToMain() {
		driver.findElement(By.xpath("//a[@class = 'b-navigation-control__item']")).click();
	}

	protected void clickOnEnter() {
		driver.findElement(By.xpath("//form[@class='b-modal']//button[@class='b-button_type_cta b-button_color_green js-submit']")).click();
	}

	protected void fillLoginForm(LoginData namePass) {
	    driver.findElement(By.id("l-auth-login")).clear();
	    driver.findElement(By.id("l-auth-login")).sendKeys(LoginData.userName);
	    driver.findElement(By.id("l-auth-pass")).clear();
	    driver.findElement(By.id("l-auth-pass")).sendKeys(LoginData.password);
	}

	protected void clickToLogin() {
	    driver.findElement(By.xpath("//li[5]/span")).click();
	}

	protected void openPage() {
		driver.get(baseUrl + "/");
	}

	 public boolean isNotLoggedInInLogin() {
		    return driver.findElements(By.xpath("//button[@class = 'b-button_type_cta b-button_color_green js-submit']")).size() > 0;
		  }
	 
	 public boolean isNotLoggedInGen() {
		    return driver.findElements(By.xpath("//span[@class = 'js-auth-signin b-navbar__exit h-ml-10']")).size() > 0;
		  }
		  
		  public boolean isLoggedIn() {
			    return driver.findElements(By.xpath("//span[@class='js-auth-signin b-navbar__exit h-ml-10']")).size() > 0;
			  }
	
	protected boolean isElementPresent(By by) {
	try {
	driver.findElement (by);
	return true;
	} catch (NoSuchElementException e) {
	return false;
	}
	}

	private String closeAlertAndGetItsText() {
	try {
	Alert alert = driver.switchTo().alert();
	String alertText = alert.getText ();
	if (acceptNextAlert) {
	alert.accept();
	} else {
	alert.dismiss();
	}
	return alertText;
	} finally {
	acceptNextAlert = true;
	}
	}
	
	public String getProperty(String prop){
		String value ="";
		File configFile = new File("config.prop");
		
		try {
		    FileReader reader = new FileReader(configFile);		    
		    Properties props = new Properties();		    
		    props.load(reader);
		 
		    value = props.getProperty(prop);
		    
		    reader.close();
		} catch (FileNotFoundException ex) {
		    JOptionPane.showMessageDialog(new JFrame(), "file does not exist.");
		} catch (IOException ex) {
		    // I/O error
		}
		
		return value;
	}
	
	public void printSpecSymb(int nSymbol) throws Exception{
		Robot robot = new Robot();
		switch (nSymbol) {
		case 1: {
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD0);
			robot.keyRelease(KeyEvent.VK_NUMPAD0);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD0);
			robot.keyRelease(KeyEvent.VK_NUMPAD0);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD9);
			robot.keyRelease(KeyEvent.VK_NUMPAD9);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD2);
			robot.keyRelease(KeyEvent.VK_NUMPAD2);
			robot.delay(20);
			robot.keyRelease(KeyEvent.VK_ALT);
		}
			;
			break;
		case 2: {
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD5);
			robot.keyRelease(KeyEvent.VK_NUMPAD5);
			robot.delay(20);
			robot.keyPress(KeyEvent.VK_NUMPAD8);
			robot.keyRelease(KeyEvent.VK_NUMPAD8);
			robot.delay(20);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(20);
		}
			;
			break;
		case 3: {
			robot.keyPress(KeyEvent.VK_PERIOD);
			robot.keyRelease(KeyEvent.VK_PERIOD);
		}
			;
			break;
		}
	}
	
	
	
	public void printFilePath(String prorerty) throws Exception{
		Robot robot = new Robot();
		
		File file = new File("src");
		String absolutePath = file.getAbsolutePath().toLowerCase();
	
		String iFilePath = absolutePath+getProperty(prorerty);//"d:\\dasha\\client.xls";
		for(int i=0; i<iFilePath.length();i++){
			char symbol=iFilePath.charAt(i);
			int asciiSymbol = (int) symbol;
			if (asciiSymbol > 96 && asciiSymbol < 123) {
				asciiSymbol = asciiSymbol-32;
				robot.keyPress(asciiSymbol);
				robot.keyRelease(asciiSymbol);
			} else {
				if (symbol == '\\') {
					printSpecSymb(1);
				} else {
					if (symbol == ':') {
						printSpecSymb(2);
					} else {
						if (symbol == '.') {
							printSpecSymb(3);
						}
					}
				}
			}
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

//	@AfterMethod
//	public void setScreenshot(ITestResult result) {
//		if (!result.isSuccess()) {
//			try {
//				WebDriver returned = new Augmenter().augment(driver);
//				if (returned != null) {
//					File f = ((TakesScreenshot) returned)
//							.getScreenshotAs(OutputType.FILE);
//					try {
//						FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER
//								+ result.getName() + SCREENSHOT_FORMAT));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			} catch (ScreenshotException se) {
//				se.printStackTrace();
//			}
//		}
//	}
}
