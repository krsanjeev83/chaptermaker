package com.newdemo.test;

import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Test;
import com.newdemo.framework.base.ScriptBase;
import com.sun.java_cup.internal.runtime.Scanner;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class E2E_Foxipedia_CM_MP_QManager_AsperaConsole_Flow extends ScriptBase {
	
	@Test(priority = 1)																		//Complete E2E WORK FLOW
	public void SmokeTest1_MoviAnywhere()  throws Exception {
		
		//Log in to Foxipedia and fetch WPR, JDE(Verion ID), Language, etc. and store in data sheet 
		atuDescription("E2E Scenario1: Foxipedia Chapter Mark MediaPakage QManager AsperaConsole Flow");
		strDTParametersNValues = "InputDataRow=>5";
		PPCApplicaton().LoginPage().LaunchURL();
		PPCApplicaton().LoginPage().loginIntoFoxipedia();	
		
		
		
		
		/*atuDescription("E2E Scenario1: Foxipedia Chapter Mark MediaPakage QManager AsperaConsole Flow");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();											//Log-In
		
		strDTParametersNValues = "InputDataRow=>4";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");						//Multiple Criteria Search Page
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
		
		PPCApplicaton().LoginPage().logOut();												//Log Out*/
	}
	
	
public void atuDescription(String strDescription)
	{
		ATUReports.indexPageDescription = "Media Package automation";
		ATUReports.setAuthorInfo("SUNDAR: Digital Media Archives QA Team", Utils.getCurrentTime(), "1.0");
		ATUReports.currentRunDescription = strDescription;
	}
}