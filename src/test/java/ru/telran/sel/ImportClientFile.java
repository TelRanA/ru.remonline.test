package ru.telran.sel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class ImportClientFile extends ru.telran.sel.pages.TestBase {
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer () ;
	
	@Test
	public void testFile_import() throws Exception {
		openPage();
		clickToLogin();
		fillLoginForm(new LoginData("qazwsx", "qazwsx"));
		clickOnEnter();
		driver.findElement(By.xpath("//a[@href='#!/warehouse']")).click();   //<a class="b-sidebar__item" href="#!/warehouse">
		driver.findElement(By.xpath("//button[@class='pull-left btn btn-sm btn-default js-import js-tooltip']")).click();  
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-default js-upload-btn']")).click(); // open the dialog window
		//((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].style.display='block';"); //javascript for changing the stype of element
		//((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].value='D:\\dasha\\client.xls';"); // javascript but it is not working, cuz element 'input' is invisible
		
		/* 
		 * driver.switchTo().activeElement().sendKeys("D:/dasha/client.xls");	//switch to active element	
		 * driver.switchTo().activeElement().sendKeys(Keys.ENTER); // press "enter"
		*/
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		printFilePath("i_client");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary js-do-import']")).click(); //clink on the button for import
		checkImport();
	}
	
	
	
	public void checkImport(){
	    try {
	        assertTrue(isElementPresent(By.xpath("//i[@class=\"h-mt-20 b-file-import__icon b-file-import__icon_type_success\"]")));
	      } catch (Error e) {
	    	  verificationErrors.append(e.toString());
	      }
	     
	    try {
	        assertEquals("Импорт клиентов завершен успешно", driver.findElement(By.xpath("//h3[@class=\"col-xs-12 h-p-0 h-mt-20 h-ta-c\"]")).getText());
	      } catch (Error e) {
	    	  verificationErrors.append(e.toString());
	      }
	}
}