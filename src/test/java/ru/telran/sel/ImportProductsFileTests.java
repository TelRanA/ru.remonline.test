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
		//((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].style.display='block';"); //javascript for changing the stype of element
		//((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].value='D:\\dasha\\client.xls';"); // javascript but it is not working, cuz element 'input' is invisible
		
		/* 
		 * driver.switchTo().activeElement().sendKeys("D:/dasha/client.xls");	//switch to active element	
		 * driver.switchTo().activeElement().sendKeys(Keys.ENTER); // press "enter"
		*/
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		printFilePath();
		Thread.sleep(10000);
		clickToImport(); //clink on the button for import
		checkImport();
	}

	private void clickToImport() {
		driver.findElement(By.xpath("//button[@class='btn btn-primary js-do-import']")).click();
	}

	private void goToFileUpload() {
		driver.findElement(By.xpath("//a[@href='#!/clients']")).click();
		driver.findElement(By.xpath("//button[@class='h-ml-15 pull-left btn btn-sm btn-default js-import js-tooltip']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-default js-upload-btn']")).click();
	}
	
	public String loadFilePath(String prop){
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
	
	
	
	public void printFilePath() throws Exception{
		Robot robot = new Robot();
		
		File file = new File("src");
		String absolutePath = file.getAbsolutePath().toLowerCase();
	
		/*String internalPath = this.getClass().getName().replace(".", File.separator);
		String externalPath = System.getProperty("user.dir")+File.separator+"src";
		String workDir = externalPath+File.separator+internalPath.substring(0, internalPath.lastIndexOf(File.separator));
		System.out.println(workDir);*/
		
		String iFilePath = absolutePath+loadFilePath("i_file");//"d:\\dasha\\client.xls";
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