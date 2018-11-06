package com.newdemo.test;

import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Test;
import com.newdemo.framework.base.ScriptBase;
import com.sun.java_cup.internal.runtime.Scanner;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class SmokeScript_MediaPackage extends ScriptBase {
	
	@Test(priority = 1)																		//Platform: MoviAnywhere
	public void SmokeTest1_MoviAnywhere()  throws Exception {
		
		atuDescription("Smoke Scenario1: Media Package Basic Smoke Flow");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();											//Log-In
		
		strDTParametersNValues = "InputDataRow=>4";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("WPRSearch");								//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();									//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(4); 						//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();										//Upload Multipe Poster
		PPCApplicaton().SearchPage().SearchPONumber("");									//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature, Trailer, Subtitle and Caption

		strDTParametersNValues = "InputDataRow=>4";
		PPCApplicaton().SearchPage().EnterPONumber();										//Search PO Number from Packages Page
		
		//Verify Status is getting updated to 	QMANAGER_TRANSFER_COMPLETED
		PPCApplicaton().PackageBuilderPage().VerifyMPStatus("QAManagerTransferComplete");   //Verify the Status QMANAGER_TRANSFER_COMPLETED
		
		//Verify Status is getting updated to ASPERA_COMPLETED
		PPCApplicaton().PackageBuilderPage().VerifyMPStatus("AsperaComplete");   			//Verify the Status ASPERA_COMPLETE 
		
		PPCApplicaton().LoginPage().logOut();												//Log Out
	}
	
	@Test(priority = 2)																		//Platform: MoviAnywhere
	public void SmokeTest2_Microsoft()  throws Exception {
		
		atuDescription("Smoke Scenario1: Media Package Basic Smoke Flow");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();											//Log-In
		
		strDTParametersNValues = "InputDataRow=>6";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("WPRSearch");								//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();									//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(4); 						//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();										//Upload Multipe Poster
		PPCApplicaton().SearchPage().SearchPONumber("");									//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>4";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("In America");  		//Select the Feature, Trailer, Subtitle and Caption

		strDTParametersNValues = "InputDataRow=>6";
		PPCApplicaton().SearchPage().EnterPONumber();										//Search PO Number from Packages Page
		
		//Verify Status is getting updated to 	QMANAGER_TRANSFER_COMPLETED
		PPCApplicaton().PackageBuilderPage().VerifyMPStatus("QAManagerTransferComplete");   //Verify the Status QMANAGER_TRANSFER_COMPLETED
		
		//Verify Status is getting updated to ASPERA_COMPLETED
		PPCApplicaton().PackageBuilderPage().VerifyMPStatus("AsperaComplete");   			//Verify the Status ASPERA_COMPLETE
		
		PPCApplicaton().LoginPage().logOut();												//Log Out
	}
public void atuDescription(String strDescription)
	{
		ATUReports.indexPageDescription = "Media Package automation";
		ATUReports.setAuthorInfo("SUNDAR: Digital Media Archives QA Team", Utils.getCurrentTime(), "1.0");
		ATUReports.currentRunDescription = strDescription;
	}
}