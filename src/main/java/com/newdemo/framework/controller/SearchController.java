package com.newdemo.framework.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.newdemo.framework.base.ComponentFunctions;
import com.newdemo.framework.base.CommonFunctions;
import com.newdemo.framework.data.SearchData;
import com.newdemo.framework.model.SearchPage;
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

//import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

public class SearchController extends ComponentFunctions
{
	SearchPage search = null;
	SearchData searchData = null;
	WebDriver webDriver;
	HashMap<String, String> hmOutputData = new HashMap<String, String>();
	CommonFunctions commonFunctions = new CommonFunctions();
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
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
	
	public SearchController(WebDriver driver) throws Exception 
	{
		super(driver);
		search = PageFactory.initElements(driver, SearchPage.class);
		this.webDriver = driver;
		
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmmss");
			SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
		
		    Calendar cal = Calendar.getInstance();
		    strTodayDate = formatter.format(cal.getTime()).toString();
		    cal.add(Calendar.DATE, 2);
		    String strMPDueDate = formatter1.format(cal.getTime()).toString();
		    System.out.println(strMPDueDate);	
		    
		if (OperatingSys.equals("Windows"))
	        strConfigFileUploadPath = System.getProperty("user.dir")+("\\FilesToUpload\\");
			else
		   strConfigFileUploadPath = System.getProperty("user.dir")+("/FilesToUpload/");
	}
	
	
	public void SearchTitle(String strSearch) throws Exception{
		String strParam_Language = searchData.strLanguage;
		String [] objParam_Language = strParam_Language.split(";");
		Thread.sleep(1000);
		if (strSearch.equals("Title")){
			clearAndTypeValue(search.eltTitle, "Title", searchData.strTitle);
			selectValueFromDropDownList(search.lstCountry,"Country", searchData.strCountry);
		}
		else if (strSearch.equals("WPRSearch")){
				clearAndTypeValue(search.eltTitle, "Title", searchData.strTitle);
				selectValueFromDropDownList(search.lstCountry,"Country", searchData.strCountry);
				clearAndTypeValue(search.eltWPR, "OR -- WPR", searchData.strWPR);
		}
				else if (strSearch.equals("JDEVersionSearch")){
					clearAndTypeValue(search.eltTitle, "Title", searchData.strTitle);
					Thread.sleep(1000);
					selectValueFromDropDownList(search.lstCountry,"Country", searchData.strCountry);
					clearAndTypeValue(search.eltWPR, "OR -- WPR", searchData.strWPR);
					clearAndTypeValue(search.eltJDEVersionID, "OR -- JDE(Version) ID", searchData.strJDEID);
					selectValueFromDropDownList(search.lstLanguages,"Languages", objParam_Language[0]);
				}
		
				clickObject(search.btnLookUpTitle, "Look Up Title");
				Thread.sleep(2000);
				
				List<WebElement> flag = findMultipleElementsNReturn(search.eltSelectWPR+"--"+searchData.strWPR);
				if(flag.size()>0){
					clickObjectUsingJSExecutor(search.eltSelectWPR+"--"+searchData.strWPR, "Select WPR");
				}
				
				List<WebElement> reviewSynopsis = findMultipleElementsNReturn(search.eltSelectVersion+"--"+searchData.strJDEID);
				if(reviewSynopsis.size()>0){
					clickObjectUsingJSExecutor(search.eltSelectVersion+"--"+searchData.strJDEID, "Select OriginalTitle");
				}
				
				Thread.sleep(1000);
//				clickObjectUsingJSExecutor(search.eltSelectVersion+"--"+searchData.strJDEID, "Select OriginalTitle");
				waitTillElementDisplayed(search.btnReviewSynopsisTitle, "Review Synopsis Title", 50);
				checkObjectExists(search.btnReviewSynopsisTitle, "Review Synopsis Title");
				clickObject(search.btnReviewSynopsis, "Review Synopsis");
				waitTillElementDisplayed(search.btnReviewGenre, "Review Genre", 50);
				checkObjectExists(search.btnReviewGenre, "Review Genre");
				clickObject(search.btnReviewGenre, "Review Genre");
				waitTillElementDisplayed(search.btnReviewRatingTitle, "Tile Genre", 50);
				checkObjectExists(search.btnReviewRating, "Review Rating");
				clickObject(search.btnReviewRating, "Review Rating");
				waitTillElementDisplayed(search.btnReviewTalentTitle, "Version Rating", 50);
				checkObjectExists(search.btnReviewTalent, "Review Talent");
				clickObject(search.btnReviewTalent, "Review Talent");
				waitTillElementDisplayed(search.btnTitleTalent, "Title Talent", 50);
				checkObjectExists(search.btnTitleTalent, "Title Talent");
				Thread.sleep(1000);
				clickObject(search.btnContinue, "Continue");
				waitTillElementDisplayed(search.btnPlatformTitle, "PlatForm Title", 50);
	}
	

	public void UpdateMediaPackage() throws Exception
	{

		String strParam_Language = searchData.strLanguage;
		String [] objParam_Language = strParam_Language.split(";");
//		String DueDate = EnterDate(5);
			Date myDate = new Date();
		String MPDueDate = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
		String PO_Number = "Automation_"+strTodayDate;
		checkObjectExists(search.lstPlatform, "Platform");
		selectValueFromDropDownList(search.lstPlatform,"Platform", searchData.strPlatform);
		checkObjectExists(search.eltMPTitle, "MP Title");
		clearAndTypeValue(search.eltMPTitle, "MP Update Title", searchData.strTitle);
		checkObjectExists(search.eltWPRID, "WPR ID");
		clearAndTypeValue(search.eltWPRID, "WPR ID", searchData.strWPR);
		checkObjectExists(search.eltJDEID, "JDE ID");
		clearAndTypeValue(search.eltJDEID, "JDE ID", searchData.strJDEID);
		checkObjectExists(search.eltLanguage, "Language");
		clearAndTypeValue(search.eltLanguage, "Language", objParam_Language[1]);
		checkObjectExists(search.eltPO, "PO Number");
		clearAndTypeValue(search.eltPO, "PO Number", PO_Number);
		checkObjectExists(search.eltAvailID, "Avail ID");
		clearAndTypeValue(search.eltAvailID, "Avail ID", searchData.strAvailID);
		checkObjectExists(search.eltDueDateVerify, "Due Date");
		typeValueUsingJSExecutor(search.eltDueDate1, "Due Date", "2017-06-28");
		
//		typeValueUsingJSExecutor(search.eltDueDate1, "Due Date", MPDueDate);
		checkObjectExists(search.lstStatus, "Status");
								
		selectValueFromDropDownList(search.lstStatus, "Status", searchData.strStatus);
		checkObjectExists(search.eltComments, "Comments");
		clearAndTypeValue(search.eltComments, "Comments", "MP Testing Purpose");
		
	}
	
	public void getValueAndSaveInExcelFile (int rowNum) throws Exception
	{
		String strPONumber = getAttributeFromElement(search.eltPO, "PO", "value");
		System.out.println(strPONumber);
		hmOutputData.put("PO", strPONumber);
		commonFunctions.storeHashMapDataInExcel(hmOutputData, "SearchPages", false, rowNum);
	}

	public void UploadPoster() throws Exception
	{
			
				String strParam_PosterType = searchData.strPosterType;
				String [] objParam_PosterType = strParam_PosterType.split("==>");
								
				String strParam_strFileInput = searchData.strFileInput;
				String [] objParam_strFileInput = strParam_strFileInput.split("==>");
				
				if(objParam_PosterType[0].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					waitTillElementDisplayed(search.lstPosterType, "Poster Type: Key Art", 10);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type",objParam_PosterType[0]);
					clearAndTypeValue(search.btnBrowse, "Upload Key Art", objParam_strFileInput[0] + "/" + objParam_strFileInput[1]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				if(objParam_PosterType[1].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					waitTillElementEnabled(search.eltKeyArtVerify, "Key ArtVerify", 50);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type: Title Treatment",objParam_PosterType[1]);
					clearAndTypeValue(search.btnBrowse, "Title Treatment",  objParam_strFileInput[0] + "/" + objParam_strFileInput[2]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				if(objParam_PosterType[2].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type: Hero 3:1",objParam_PosterType[2]);
					clearAndTypeValue(search.btnBrowse, "Hero 3:1", objParam_strFileInput[0] + "/" + objParam_strFileInput[3]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				if(objParam_PosterType[3].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type",objParam_PosterType[3]);
					clearAndTypeValue(search.btnBrowse, "Upload Hero 16:9", objParam_strFileInput[0] + "/" + objParam_strFileInput[4]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				if(objParam_PosterType[4].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type",objParam_PosterType[4]);
					clearAndTypeValue(search.btnBrowse, "Upload Hero 4:3", objParam_strFileInput[0] + "/" + objParam_strFileInput[5]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				if(objParam_PosterType[5].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type",objParam_PosterType[5]);
					clearAndTypeValue(search.btnBrowse, "Upload Hero 1:1", objParam_strFileInput[0] + "/" + objParam_strFileInput[6]);
					clickObject(search.btnUpdate, "Upload");
				}	
				
				if(objParam_PosterType[6].equals("Null")) {
					System.out.println("NoPosterType to Upload");
				}
				else{
					LoopTillObjectDisable(search.eltSpin);
					selectValueFromDropDownList(search.lstPosterType,"Poster Type",objParam_PosterType[6]);
					clearAndTypeValue(search.btnBrowse, "Trailer Poster", objParam_strFileInput[0] + "/" + objParam_strFileInput[7]);
					clickObject(search.btnUpdate, "Upload");
				}
				
				LoopTillObjectDisable(search.eltSpin);
										
				clickObjectUsingJSExecutor(search.UpdatePackage1, "Update Package");
	
										
										
										
										
				LoopTillObjectEnable(search.eltPackageUpdateSuccess);							
				
			
				/*Thread.sleep(1000);
				List<WebElement> PackageUpdatedMessage = driver.findElements(By.xpath("//*[@id='toast-container']/div/div[1]"));
				if(PackageUpdatedMessage.size()>0)
				{
				System.out.println("Package Updated Sucessfully"); }
				else{
				System.out.println("Package Not Updated");
				}*/
	}

	public void SearchPONumber(String IntegrationFlag) throws Exception {
		
		if (IntegrationFlag.equals("CMMPIntegration")){
			clickObject(search.UpdatePackage, "Update Package");
		}
		Thread.sleep(2000);
		checkObjectExists(search.eltMediaPackages, "Media Packages");
		clickObject(search.eltMediaPackages, "Media Packages");
		checkObjectExists(search.eltMPPO, "PO Number");
		clearAndTypeValue(search.eltMPPO, "PO Number", searchData.strPO);
		Thread.sleep(1000);
		
			clickObjectUsingJSExecutor(search.eltPOStatus + "--" + searchData.strPO, "Select PO Number");
			checkObjectExists(search.eltSlectMediaFiles, "Select Media Files");
	}

	public void SearchPONumberE2E(String IntegrationFlag) throws Exception {
		
		if (IntegrationFlag.equals("CMMPIntegration")){
			clickObject(search.UpdatePackage, "Update Package");
		}
		Thread.sleep(2000);
		checkObjectExists(search.eltMediaPackages, "Media Packages");
		clickObject(search.eltMediaPackages, "Media Packages");
		checkObjectExists(search.eltMPPO, "PO Number");
		clearAndTypeValue(search.eltMPPO, "PO Number", searchData.strPO);

	}
	public void SearchPONumber_MedaError(String IntegrationFlag) throws Exception {
		
		if (IntegrationFlag.equals("CMMPIntegration")){
			clickObject(search.UpdatePackage, "Update Package");
		}
		Thread.sleep(2000);
		checkObjectExists(search.eltMediaPackages, "Media Packages");
		clickObject(search.eltMediaPackages, "Media Packages");
		checkObjectExists(search.eltMPPO, "PO Number");
		clearAndTypeValue(search.eltMPPO, "PO Number", searchData.strPO);
	}
	
	public void EnterPONumber() throws Exception {
		checkObjectExists(search.eltMediaPackages, "Media Packages");
		clickObject(search.eltMediaPackages, "Media Packages");
		clickObject(search.btnClearSearchFields, "Clear Search Fields");
		checkObjectExists(search.eltMPPO, "PO Number");
		clearAndTypeValue(search.eltMPPO, "PO Number", searchData.strPO);
	}
	public void EnterPONumberAndValidateStatus() throws Exception {
		
		//Sleep and loop until Status changes
		int intctr = 1;
		int int_Refresh_Limit = 5;
		
		checkObjectExists(search.eltMediaPackages, "Media Packages");
		clickObject(search.eltMediaPackages, "Media Packages");
		clickObject(search.btnClearSearchFields, "Clear Search Fields");
		checkObjectExists(search.eltMPPO, "PO Number");
		clearAndTypeValue(search.eltMPPO, "PONumber", searchData.strPO);
		
		while(Count_No_Of_Element(search.eltAsperaCompleted + "--" + searchData.strPO)<1){
			Thread.sleep(5000);
			refreshPage();
			clickObject(search.btnClearSearchFields, "Clear Search Fields");
			checkObjectExists(search.eltMPPO, "PO Number");
			clearAndTypeValue(search.eltMPPO, "PO Number", searchData.strPO);
			
			intctr++;
			if (intctr>int_Refresh_Limit){
				break;
			}
			
			String strstatustext = getTextFromElement(search.eltAsperaCompleted + "--" + searchData.strPO, "Package Status");
			if(strstatustext.equals("ASPERA_COMPLETED")){
				System.out.println("Status Changed to ASPERA COMPELETED Sucessfully");
				break;
			} 
		}
	}
	
	
	public void VerifyMPStatus(String strStatus) throws Exception {
		waitUntilSleepLimit(search.eltAsperaCompleted + "--" + searchData.strPO, "Aspera Completed", 2);
		int intctr = 1;
		while(Count_No_Of_Element(search.eltAsperaCompleted + "--" + searchData.strPO)>0){
			intctr++;
			if (intctr>int_WAIT_LIMIT){
				break;
			}
		}
		
		if (strStatus.equals("QAManagerTransferComplete")) {

			List<WebElement> flag = findMultipleElementsNReturn(search.eltQManagerTransfer + "--" + searchData.strPO);
			if (flag.size() > 0) {
				System.out.println("Status Changed QMANAGER_TRANSFER_COMPLETED Sucessfully");
			} else
				System.out.println("Status Not Changed to QManager Completed");
		} else if (strStatus.equals("AsperaComplete")) {
			List<WebElement> flag = findMultipleElementsNReturn(search.eltQManagerTransfer + "--" + searchData.strPO);
			if (flag.size() > 0) {
				System.out.println("Status Changed ASPERA COMPLETED Sucessfully");
			} else
				System.out.println("Status Not Changed to AsperaComplete Completed");
		}
		
	}

	
	
	public void ValidateMetaDetaError(String stretaDataError) throws Exception {

		try {

			int switchQuestion = 1;

			if (stretaDataError.equalsIgnoreCase("EIDR")) {
				switchQuestion = 1;
			} else if (stretaDataError.equalsIgnoreCase("Synopsis")) {
				switchQuestion = 2;
			} else if (stretaDataError.equalsIgnoreCase("Genre")) {
				switchQuestion = 3;
			} else if (stretaDataError.equalsIgnoreCase("Rating")) {
				switchQuestion = 4;
			} else if (stretaDataError.equalsIgnoreCase("Talent")) {
				switchQuestion = 5;
			} else if (stretaDataError.equalsIgnoreCase("Copyright")) {
				switchQuestion = 6;
			} else if (stretaDataError.equalsIgnoreCase("test Place Holder")) {
				switchQuestion = 7;
			
			} else {
				switchQuestion = 99;// Invalid Question Type
			}

			switch (switchQuestion) {
			case 1: // EIDR
				waitTillElementEnabled(search.eltMetaData_EIDR, "EIDR Error", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_EIDR, "EIDR Error");
				waitTillElementEnabled(search.eltMetaData_EIDR_Msg, "EIDR Error Message", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_EIDR_Msg, "EIDR Error Message");
				waitTillElementDisplayed(search.btnUpdateAll, "Update All", 50);
				clickObject(search.btnUpdateAll, "Update All");
				waitTillElementDisplayed(search.btnClearSearch, "Clear Search", 50);
				clickObject(search.btnClearSearch, "Clear Search");
				break;

			case 2: // Synopsis
				waitTillElementEnabled(search.eltMetaData_Synopsis, "Synopsis Error", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Synopsis, "Synopsis Error");
				waitTillElementEnabled(search.eltMetaData_Synopsis_Msg, "Synopsis Error Message", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Synopsis_Msg, "Synopsis Error Message");
				waitTillElementDisplayed(search.btnUpdateAll, "Update All", 50);
				clickObject(search.btnUpdateAll, "Update All");
				waitTillElementDisplayed(search.btnClearSearch, "Clear Search", 50);
				clickObject(search.btnClearSearch, "Clear Search");
				break;

			case 3: // Genre
				waitTillElementEnabled(search.eltMetaData_Genre, "Genre Error", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Genre, "Genre Error");
				waitTillElementEnabled(search.eltMetaData_Genre_Msg, "Genre Error Message", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Genre_Msg, "Genre Error Message");
				waitTillElementDisplayed(search.btnUpdateAll, "Update All", 50);
				clickObject(search.btnUpdateAll, "Update All");
				waitTillElementDisplayed(search.btnClearSearch, "Clear Search", 50);
				clickObject(search.btnClearSearch, "Clear Search");
				break;

			case 4: // Rating
				waitTillElementEnabled(search.eltMetaData_Rating, "Rating Error", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Rating, "Rating Error");
				waitTillElementEnabled(search.eltMetaData_Rating_Msg, "Rating Error Message", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Rating_Msg, "Rating Error Message");
				waitTillElementDisplayed(search.btnUpdateAll, "Update All", 50);
				clickObject(search.btnUpdateAll, "Update All");
				waitTillElementDisplayed(search.btnClearSearch, "Clear Search", 50);
				clickObject(search.btnClearSearch, "Clear Search");
				break;

			case 5: // Talent
				waitTillElementEnabled(search.eltMetaData_Talent, "Talent Error", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Talent, "Talent Error");
				waitTillElementEnabled(search.eltMetaData_Talent_Msg, "Talent Error Message", 50);
				clickObjectUsingJSExecutor(search.eltMetaData_Talent_Msg, "Talent Error Message");
				waitTillElementDisplayed(search.btnUpdateAll, "Update All", 50);
				clickObject(search.btnUpdateAll, "Update All");
				waitTillElementDisplayed(search.btnClearSearch, "Clear Search", 50);
				clickObject(search.btnClearSearch, "Clear Search");
				break;

			case 6: // CopyRight
				waitTillElementEnabled(search.eltMetaData_Copyright, "CopyRight Error", 50);
				break;

			}

			
		} catch (Exception e) {
			
		}
	}

	
	
//################CAPTER MAKER FUNCTIONS#############################################################################
public void SearchTitle_CM(String strSearch) throws Exception{		
		
			checkObjectExists(search.eltSearch, "Search");
			clickObject(search.eltSearch, "Search");
		if (strSearch.equals("SingleSearch")){
				checkObjectExists(search.eltTitle, "Title");
				clearAndTypeValue(search.eltTitle, "Title", searchData.strTitle);
				checkObjectExists(search.eltWPR, "WPR");
				clearAndTypeValue(search.eltWPR, "OR -- WPR", searchData.strWPR);
		}
				else if (strSearch.equals("MultiSearch")){
					checkObjectExists(search.eltTitle, "Title");
					clearAndTypeValue(search.eltTitle, "Title", searchData.strTitle);
					checkObjectExists(search.eltWPR, "WPR");
					clearAndTypeValue(search.eltWPR, "WPR", searchData.strWPR);	
					checkObjectExists(search.eltJDEVersionID, "JDE(Version) ID");
					clearAndTypeValue(search.eltJDEVersionID, "JDE ID", searchData.strJDEID);
				}
				clickObject(search.btnLookUpTitle, "Look Up Title");
				Thread.sleep(2000);
				
			/*	List<WebElement> flag = findMultipleElementsNReturn(search.eltSelectWPR+"--"+searchData.strWPR);
				if(flag.size()>0){
					clickObjectUsingJSExecutor(search.eltSelectWPR+"--"+searchData.strWPR, "Select WPR");
					clickObjectUsingJSExecutor(search.eltSelectVersion+"--"+searchData.strJDEID, "Select OriginalTitle");
				}*/
				waitTillElementDisplayed(search.eltCreateChapterMarks, "Create Chapter Marks", 10);
				
	}
	

public void ValidateChapterMakerImport(String strApprovedStatus) throws Exception {
	
	if (strApprovedStatus.equals("NotApproved")){
		Thread.sleep(1000);
		List<WebElement> flag = findMultipleElementsNReturn(search.eltChapterMarkNotApprovedMsg);
		if(flag.size()>0){
				System.out.println("Passed: No Chapter Marks Found");
		}
			else{
				System.out.println("Failed: No Chapter Marks Found Not Displayed");
			}
		}
	
	if (strApprovedStatus.equals("Approved")){
		Thread.sleep(1000);
		List<WebElement> flag = findMultipleElementsNReturn(search.eltChapterMarkApprovedMsg);
		if(flag.size()>0){
			System.out.println("Passed:Retrieved 1 Chapter Marks");
		}
			else{
				System.out.println("Failed: Retrieved 1 Chapter Marks Not Displayed");
			}
		}
	
	}
	
}

	
