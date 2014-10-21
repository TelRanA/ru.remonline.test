package ru.telran.sel.pages;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class WorkWithExcel extends TestBase{

	private String absoluteProjectPath;
	public void createExcel(String filePath) {
		try {
			File file = new File("src");
			absoluteProjectPath = file.getAbsolutePath().toLowerCase();
			
			String importFile = absoluteProjectPath + filePath;
			
			WritableWorkbook workbook = Workbook.createWorkbook(new File(importFile));
			WritableSheet s = workbook.createSheet("Sheet1", 0);

			writeDataSheet(s);
			workbook.write();
			workbook.close();     
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    catch (WriteException e)
	    {
	      e.printStackTrace();
	    }
	  }
	 

	  private void writeDataSheet(WritableSheet s) 
	    throws WriteException
	  {
		//setting format for cells
	    WritableFont wf = new WritableFont(WritableFont.ARIAL, 10);
	    WritableCellFormat cf = new WritableCellFormat(wf);
	    cf.setWrap(true);
	    
	    Label l;
	    String labels [] = {"Код","Артикул","Наименование","Остаток","Цена","Категория"};//names of columns
	    
	    for(int i=0;i<labels.length;i++){ // loop for creation of cells with our names
	    	l = new Label(i,0,labels[i], cf);
	    	s.addCell(l);
	    }
	   
	   /* Need to change the format of the cell
	    WritableCellFormat cfloat = new WritableCellFormat(NumberFormats.FLOAT);	 
	    WritableCellFormat ctext = new WritableCellFormat(NumberFormats.TEXT);
	    WritableCellFormat cint = new WritableCellFormat(NumberFormats.INTEGER);
	   */ 
	    
	    FileReader fr;
		try {
			fr = new FileReader(absoluteProjectPath+getProperty("testDataImportProd"));
			BufferedReader bf = new BufferedReader(fr);
			String sCurrentLine;
			int i =1;			
			
			
			while ((sCurrentLine = bf.readLine()) != null) {
				int j =0;
				//Splitting a string into an array of words				
				for (String value : sCurrentLine.trim().split(";")) {
					
					l = new Label(j,i, value, cf);//fill the cell text
					j++;
					s.addCell(l);
				}
				i++;
			}

			bf.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	    

	 
}