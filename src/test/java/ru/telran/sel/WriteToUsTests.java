package ru.telran.sel;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.testng.annotations.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
public class WriteToUsTests extends ru.telran.sel.pages.TestBase {
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer () ;
@Test(groups = "positive")
public void testWU1 () throws Exception {    
	openPage();
    waitForElement();
    isElementPresent();
    clickOnLinkWriteToUs();
    fillForm();
    clickOnButtonWriteToUs();
    verifyIfSended();
}

private void verifyIfSended() throws InterruptedException {
	driver.findElement(By.cssSelector("div.reveal-modal-bg")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.b-modal.h-ta-c"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertTrue(isElementPresent(By.cssSelector("div.b-modal.h-ta-c")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
}

private void clickOnButtonWriteToUs() {
	driver.findElement(By.xpath("//div[@class='h-ta-c h-mt-30']/button[@class='b-button_type_cta b-button_color_green js-submit']")).click();
}

private void fillForm() {
	driver.findElement(By.id("l-auth-name")).clear();
    driver.findElement(By.id("l-auth-name")).sendKeys("demo30");
    driver.findElement(By.id("l-auth-email")).clear();
    driver.findElement(By.id("l-auth-email")).sendKeys("eva0687@gmail.com");
    driver.findElement(By.id("l-auth-message")).clear();
    driver.findElement(By.id("l-auth-message")).sendKeys("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
}

private void clickOnLinkWriteToUs() {
	driver.findElement(By.xpath("//ul[@class='h-mt-15']/li[@class='h-mt-5']/span[@class='js-auth-feedback h-dashed-link']")).click();
}

private void isElementPresent() {
	try {
      assertTrue(isElementPresent(By.cssSelector("span.js-auth-feedback.h-dashed-link")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
}

private void waitForElement() throws InterruptedException {
	for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("span.js-auth-feedback.h-dashed-link"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
}

public boolean isElementPresent (By by) {
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


}
