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

import ru.telran.sel.pages.WorkWithExcel;


public class ImportProductsFileTests extends ru.telran.sel.pages.TestBase {
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer () ;
	
	@Test
	public void testFile_import() throws Exception {
		openPage();
		clickToLogin();
		fillLoginForm(new LoginData("qazwsx", "qazwsx"));
		clickOnEnter();
		goToFileUpload(); // open the dialog window
		
		Thread.sleep(5000);
		printFilePath("i_prod");
		Thread.sleep(10000);
		clickToImport(); //clink on the button for import
		checkImport();
	}

	private void clickToImport() {
		driver.findElement(By.xpath("//button[@class='btn btn-primary js-do-import']")).click();
	}

	private void goToFileUpload() {
		WorkWithExcel excel = new WorkWithExcel();
		String filePath = getProperty("i_prod");
		excel.createExcel(filePath);
		
		driver.findElement(By.xpath("//a[@href='#!/clients']")).click();
		driver.findElement(By.xpath("//button[@class='h-ml-15 pull-left btn btn-sm btn-default js-import js-tooltip']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-default js-upload-btn']")).click();
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