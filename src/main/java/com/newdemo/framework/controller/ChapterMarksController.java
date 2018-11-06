package com.newdemo.framework.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.newdemo.framework.base.ComponentFunctions;
import com.newdemo.framework.data.SearchData;
import com.newdemo.framework.model.SearchPage;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;
import com.newdemo.framework.data.ChapterMarksData;
import com.newdemo.framework.model.ChapterMakerPage;

public class ChapterMarksController extends ComponentFunctions
{
	
	ChapterMakerPage chaptermarks = null;
	ChapterMarksData chapterMarksData = null;
	
	
	WebDriver webDriver;
	HashMap<String, String> hmOutputData = new HashMap<String, String>();
	public String strParam = "";
	public String [] objParam;
	String strConfigFileUploadPath ;
	String OperatingSys = OSDetector ();
    private boolean nodeTypeDiff = true;
    private boolean nodeValueDiff = true;
	public int int_REFRESH_LIMIT = 10;
	public String strTodayDate,strMPDueDate;
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
	Robot rb =new Robot();

	public ChapterMarksController(WebDriver driver) throws Exception 
	{
		super(driver);
		chaptermarks = PageFactory.initElements(driver, ChapterMakerPage.class);
		this.webDriver = driver;
		
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmmss");
			SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
			
		    Calendar cal = Calendar.getInstance();
		    strTodayDate = formatter.format(cal.getTime()).toString();
		    cal.add(Calendar.DATE, 2);
		    String strMPDueDate = formatter1.format(cal.getTime()).toString();
		    System.out.println(strMPDueDate);
		    
	}

	
	public void ValidateChapterMarks(String strApproved) throws Exception
	{
		checkObjectExists(chaptermarks.eltCMTitleName, "Title");
		checkObjectExists(chaptermarks.eltCMWPRName, "WPR");
		checkObjectExists(chaptermarks.eltCMJDEIDName, "JDEID");
		checkObjectExists(chaptermarks.lstTimeCodeType, "Timecode");
		clickObject(chaptermarks.lstTimeCodeType, "Timecode List");
		Thread.sleep(1000);
		clickObject(chaptermarks.lstTimeCodeType, "Timecode List");
		selectValueFromDropDownList(chaptermarks.eltTimecodeVal,"Timecode Type", chapterMarksData.strTimeCodeType);
		Thread.sleep(1000);
		if(Count_No_Of_Element(chaptermarks.eltOK)>0){
			clickObjectUsingJSExecutor(chaptermarks.eltOK, "OK");
		}
		checkObjectExists(chaptermarks.lstFrameRate, "FrameRate");
		clickObject(chaptermarks.lstFrameRate, "FrameRate List");
		Thread.sleep(1000);
		clickObject(chaptermarks.lstFrameRate, "FrameRate List");
		Thread.sleep(1000);
		selectValueFromDropDownList(chaptermarks.eltFrameRateVal,"Frame Rate", chapterMarksData.strFrameRate);
		
		if(Count_No_Of_Element(chaptermarks.eltOK)>0){
			clickObjectUsingJSExecutor(chaptermarks.eltOK, "OK");
		}

		
		
		checkObjectExists(chaptermarks.btnAprovedYESNOvalue, "AprovedYES1");
		List<WebElement> flag = findMultipleElementsNReturn(chaptermarks.btnAprovedYES1);
		if(flag.size()>0){
			 System.out.println("Approve Button Displayed");	
		}
		else{
			System.out.println("Approve Button NOT Displayed");
		}
		
		checkObjectExists(chaptermarks.btnSingleInsert, "Single Insert");
		clickObject(chaptermarks.btnSingleInsert, "Single Insert");
		Thread.sleep(1000);
		clickObject(chaptermarks.btnCloseSingle, "Close Single Insert");
		checkObjectExists(chaptermarks.btnBulkInsert, "Bulk Insert");
		clickObject(chaptermarks.btnBulkInsert, "Bulk Insert");
		Thread.sleep(1000);
		clickObject(chaptermarks.btnCloseBulk, "Close Bulk Insert");
		checkObjectExists(chaptermarks.btnDuplicateTimeCode, "Duplicate TimeCode");
		clickObject(chaptermarks.btnDuplicateTimeCode, "Duplicate TimeCode");
		checkObjectExists(chaptermarks.btnCreateCSV, "Create CSV");
		clickObject(chaptermarks.btnCreateCSV, "Create CSV");
		Thread.sleep(1000);
		clickObject(chaptermarks.btnCloseCreateCSV, "Close CSV");
		checkObjectExists(chaptermarks.btnSetCropDefaults, "Set Crop Defaults");
		clickObject(chaptermarks.btnSetCropDefaults, "Set Crop Defaults");
		Thread.sleep(1000);
		clickObject(chaptermarks.btnCloseCropValues, "Close Crop Value");
		Thread.sleep(2000);
		/*
		String strApprovedStatus = getTextFromElement(chaptermarks.btnAprovedYES1, "Approved value");
		System.out.println(strApprovedStatus);
		
		List<WebElement> switchElement = driver.findElements(By.cssSelector("btn.btn-success.btn-sm.toggle-on"));
		((Object) switchElement).bootstrapToggle();
		
		
		if (strApproved.equals("Yes")) {
			
			List<WebElement> switchElement = driver.findElements(By.cssSelector("btn.btn-success.btn-sm.toggle-on"));
		    System.out.println(switchElement.size() + " : Switch Size");
		    // Check its on, if its on then switch it off
		    if (switchElement.size() != 0) {

		        switchElement.get(0).findElement(By.cssSelector("btn btn-default btn-sm active toggle-off")).click();

		    } else
		        System.out.println("Switch  is already off");
		    
		    List<WebElement> switchElementOff = driver.findElements(By.cssSelector("btn btn-default btn-sm active toggle-off"));
		    System.out.println(switchElementOff.size() + " : Switch Size");
		 
		    
		    

			selectRadioOrCheckBox(chaptermarks.btnAprovedYESNO, "ApprovedChapterMarks", "ON");
			clickObject(chaptermarks.btnAprovedYESNO, "Approved YES");
			Thread.sleep(1000);
			if(Count_No_Of_Element(chaptermarks.eltOK)>0){
				clickObjectUsingJSExecutor(chaptermarks.eltOK, "OK");
			}
		}
		if (strApproved.equals("No")){
			
			

		       
			List<WebElement> switchElement = driver.findElements(By.cssSelector(".btn.btn-success.btn-sm.toggle-on"));
		    System.out.println(switchElement.size() + " : Switch Size");
		    
		    List<WebElement> switchElementOff = driver.findElements(By.cssSelector("btn btn-default btn-sm active toggle-off"));
		    System.out.println(switchElementOff.size() + " : SwitchSize");
		    // Check its on, if its on then switch it off
		    if (switchElement.size() != 0) {
		    	clickObjectUsingJSExecutor(chaptermarks.btnAprovedNO, "Approved NO");

		    } else
		        System.out.println("Switch  is alreadyoff");
		    
			clickOnCoordinatesUsingActionBuilder(505,143);
//			clickObject(chaptermarks.btnAprovedYESNO, "Approved NO");
				selectRadioOrCheckBox(chaptermarks.btnAprovedYESNO, "Approved Chapter Marks", "OFF");
//				clickObjectUsingJSExecutor(chaptermarks.btnAprovedNO, "Approved NO");
				Thread.sleep(1000);
				if(Count_No_Of_Element(chaptermarks.eltOK)>0){
					clickObjectUsingJSExecutor(chaptermarks.eltOK, "OK");
				}
		   }*/
	}
	
	public void ThumbnailInsert(String Thumbnail) throws Exception
	{
		
	}
	
	
	
	
}

	
