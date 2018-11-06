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
import com.newdemo.framework.data.PackageBuilderData;
import com.newdemo.framework.model.PackageBuilderPage;

public class PackageBuilderController extends ComponentFunctions
{
	
	PackageBuilderPage packagebuilder = null;
	PackageBuilderData packageBuilderData = null;
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

	public PackageBuilderController(WebDriver driver) throws Exception 
	{
		super(driver);
		packagebuilder = PageFactory.initElements(driver, PackageBuilderPage.class);
		this.webDriver = driver;
		
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmmss");
			SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
			
		    Calendar cal = Calendar.getInstance();
		    strTodayDate = formatter.format(cal.getTime()).toString();
		    cal.add(Calendar.DATE, 2);
		    String strMPDueDate = formatter1.format(cal.getTime()).toString();
		    System.out.println(strMPDueDate);
		    
	}
	
	
	public void PackageBuilder_SelectFlow(String TitleName) throws Exception
	{
		String strParam_strVideoAudioFeatureIP = packageBuilderData.strVideoAudioIPFeature;  //Video Audio File Input Feature
		String [] strVideoAudioFeatureIP = strParam_strVideoAudioFeatureIP.split("==>");
		System.out.println(strVideoAudioFeatureIP[1]);
		
		String strParam_strVideoAudioTrailerIP = packageBuilderData.strVideoAudioIPTrailer;  //Video Audio File Input Trailer
		String [] strVideoAudioTrailerIP = strParam_strVideoAudioTrailerIP.split("==>");
		System.out.println(strVideoAudioTrailerIP[1]);
		
		
		//Select Feature and Trailer Video File 
		clickObject(packagebuilder.eltAddVideoDropDown, "Add Feature Video File");
		clickObject(packagebuilder.eltVideoFeature, "Add Feature Video File");
		
		
		if(TitleName.equals("FaultInOurStars")) {
			clickObject(packagebuilder.btnChooseFile, "Choose File");
		//FaultUnOurStart
			Thread.sleep(1000);
			doubleClick(packagebuilder.eltStore2, "eltStore2");
			Thread.sleep(1000);
			doubleClick(packagebuilder.eltWorking, "Working");
			Thread.sleep(3000);
			doubleClick(packagebuilder.eltSundarfolder, "Sundar folder");
			Thread.sleep(3000);
			doubleClick(packagebuilder.eltMPfolder, "MP folder");
			Thread.sleep(3000);
			doubleClick(packagebuilder.eltTitleFolder_FaultInOurstars, "TitleFolder_FaultInOurstars");
			clickObjectUsingJSExecutor(packagebuilder.eltFeatureManifestFeatureFile, "FaultInOurstar MPG File");
			clickObjectUsingJSExecutor(packagebuilder.btnSelect, "Select");
			selectValueFromDropDownList(packagebuilder.lstLanguageVideoFeature,"Feature Language",strVideoAudioFeatureIP[2]);
			sendTABkey();	
		}	
		
				if(TitleName.equals("GulliverTravels")) {
					//Video Feature File
					clickObject(packagebuilder.btnChooseFile, "Choose File");
					//FaultUnOurStart
					Thread.sleep(1000);
					doubleClick(packagebuilder.eltStore2, "eltStore2");
					Thread.sleep(1000);
					doubleClick(packagebuilder.eltWorking, "Working");
					Thread.sleep(3000);
					doubleClick(packagebuilder.eltSundarfolder, "Sundar folder");
					Thread.sleep(3000);
					doubleClick(packagebuilder.eltMPfolder, "MP folder");
					Thread.sleep(3000);
					doubleClick(packagebuilder.eltTitleFolder_GulliverTravels, "GulliverTravels");
					Thread.sleep(3000);
					doubleClick(packagebuilder.eltTitleFolder_Resources, "Resources");
					Thread.sleep(3000);
					clickObjectUsingJSExecutor(packagebuilder.eltFeatureMovFile_GulliverTravels, "Gulliver Travels Feature File");
					clickObject(packagebuilder.btnSelect1, "Select");
					selectValueFromDropDownList(packagebuilder.lstLanguageVideoFeature,"Feature Language",strVideoAudioFeatureIP[2]);
					selectValueFromDropDownList(packagebuilder.lstLanguageVideoFeature,"Feature Language","English");
					
					/*
					//Audio Feature file
					//Select Feature and Trailer Video File 
					Thread.sleep(2000);
					clickObject(packagebuilder.eltAddVideoDropDown, "Add Feature Video File");
					clickObject(packagebuilder.eltVideoTrailer, "Add Trailer Video File");
					clickObject(packagebuilder.btnChooseFile, "Choose File");
					doubleClick(packagebuilder.eltTitleFolder_Resources, "Resources");
					Thread.sleep(3000);
					
					scrollDownTillElementVisibleNClick(packagebuilder.eltTrailverMovFile_GulliverTravels, "Gulliver Travels Trailer File");
					Thread.sleep(1000);
					clickObjectUsingJSExecutor(packagebuilder.btnSelect, "Select");
					selectValueFromDropDownList(packagebuilder.lstLanguageVideoFeature,"TrailerFile",strVideoAudioFeatureIP[2]);
					
					
					//Select Feature and Trailer Video File 
					clickObject(packagebuilder.eltAddVideoDropDown, "Add Feature Video File");
					clickObject(packagebuilder.eltVideoFeature, "Add Feature Video File");
					clickObject(packagebuilder.eltEditVideoFeature, "Upload Video Feature Path");
					clearAndTypeValue(packagebuilder.eltEditVideoFeature, "Upload Video Feature File", strVideoAudioFeatureIP[1]);
					Thread.sleep(1000);
					selectValueFromDropDownList(packagebuilder.lstLanguageVideoFeature,"Feature Language",strVideoAudioFeatureIP[2]);
					sendTABkey();
					Thread.sleep(2000);
					clickObject(packagebuilder.eltAddVideoDropDown, "Add Feature Video File");
					clickObject(packagebuilder.eltVideoTrailer, "Add Trailer Video File");
					clickObject(packagebuilder.eltEditVideoTrailer, "Upload Video Trailer Path");
					clearAndTypeValue(packagebuilder.eltEditVideoTrailer, "Upload Video Trailer File", strVideoAudioTrailerIP[1]);
					Thread.sleep(1000);
					selectValueFromDropDownList(packagebuilder.lstLanguageVideoTrailer,"Trailer Language",strVideoAudioTrailerIP[2]);
					sendTABkey();
					
					
			//		Select Feature and Trailer Audio File
					scrollDownTillElementVisibleNClick(packagebuilder.eltAddAudioDropDown, "Add Audio");
					scrollDownTillElementVisibleNClick(packagebuilder.eltAudioFeature, "Upload Audio Feature File");
					scrollDownTillElementVisibleNClick(packagebuilder.eltEditAudioFeature1, "Upload Audio Feature Path");
					clearAndTypeValue(packagebuilder.eltEditAudioFeature, "Upload Audio Feature File", strVideoAudioTrailerIP[1]);
					Thread.sleep(1000);
					selectValueFromDropDownList(packagebuilder.lstLanguageAudioFeature,"Feature Language",strVideoAudioTrailerIP[2]);
					selectValueFromDropDownList(packagebuilder.lstConfigAudioFeature,"Feature Config",strVideoAudioTrailerIP[3]);
					
					
					scrollDownTillElementVisibleNClick(packagebuilder.eltAddAudioDropDown, "Add Audio");
					scrollDownTillElementVisibleNClick(packagebuilder.eltAudioTrailer, "Upload Audio Trailer File");
					scrollDownTillElementVisibleNClick(packagebuilder.eltEditAudioTrailer1, "Upload Audio Trailer Path");
					clearAndTypeValue(packagebuilder.eltEditAudioFeature, "Upload Audio Feature File", strVideoAudioTrailerIP[1]);
					Thread.sleep(1000);
					selectValueFromDropDownList(packagebuilder.lstLanguageAudioFeature,"Feature Language",strVideoAudioTrailerIP[2]);
					selectValueFromDropDownList(packagebuilder.lstConfigAudioTrailer,"Feature Config",strVideoAudioTrailerIP[3]);
					*/
				}
		
								
		checkObjectExists(packagebuilder.eltDownloadManifest, "Download Manifest");
		clickObjectUsingJSExecutor(packagebuilder.eltDownloadManifest1, "Download Manifest");

		Thread.sleep(1000);
		sendTABkey();
		sendTABkey();
		sendTABkey();
		sendTABkey();
		sendTABkey();
		sendTABkey();	
		sendENTERkey();

		clickObjectUsingJSExecutor(packagebuilder.eltSubmitDelivery1, "Submit Delivery");
		
	}
	
	
	
	
	public void VerifyMPStatus(String strStatus) throws Exception {
/*		int intctr = 1;
		while(Count_No_Of_Element(packagebuilder.eltQManagerComplete)>0){
			intctr++;
			if (intctr>int_WAIT_LIMIT){
				break;
			}
		}
		*/
		
		//Sleep and loop until Status changes
		int intctr = 1;
		int int_Refresh_Limit = 30;
		
		if (strStatus.equals("QAManagerTransferComplete")) {
			while(Count_No_Of_Element(packagebuilder._eltQManagerComplete)<1){
				refreshPage();
				Thread.sleep(10000);
				refreshPage();
				intctr++;
				if (intctr>int_Refresh_Limit){
					objHTMLFunction.ReportPassFail("<B>Aspera Status not changes</B>", "true", "false");
					break;
				}
			}
			
			String strstatustext = getTextFromElement(packagebuilder.eltQManagerTransfer + "--" + packageBuilderData.strPO, "SuccessMsg");
			if(strstatustext.equals("ASPERA COMPLATED")){
				System.out.println("Status Changed QMANAGER_TRANSFER_COMPLETED Sucessfully");
			} else
				System.out.println("Status Not Changed to QManager Completed");

		} else if (strStatus.equals("AsperaComplete")) {
			while(Count_No_Of_Element(packagebuilder._eltQManagerComplete)<1){
				refreshPage();
				Thread.sleep(10000);
				refreshPage();
				intctr++;
				if (intctr>int_Refresh_Limit){
					objHTMLFunction.ReportPassFail("<B>Verification Status changed to ASPERA COMPLATED</B>", "true", "false");
					break;
				}
			}
		}
			List<WebElement> flag = findMultipleElementsNReturn(packagebuilder.eltQManagerTransfer + "--" + packageBuilderData.strPO);

			if (flag.size() > 0) {
				System.out.println("Status Changed ASPERA COMPLETED Sucessfully");
			}
			 else{
				System.out.println("Status Not Changed to AsperaComplete Completed");
			}
		}

/*		String strPONumber = getAttributeFromElement(packagebuilder.eltQManagerTransfer, "PO", "value");
		System.out.println(strPONumber);*/
	
	
	public void PackageBuilderReSubmitFlow() throws Exception
	{
	}
	
	public void PackageBuilderRetryFlow() throws Exception
	{
	
	}
}

	
