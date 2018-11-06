package com.newdemo.test;

import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Test;
import com.newdemo.framework.base.ScriptBase;
import com.sun.java_cup.internal.runtime.Scanner;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class CMAndMP_IntegrationScenarios extends ScriptBase {
	
	@Test(priority = 1)
	public void PositiveFlow_1_CMAndMPIntegration_ApproveNo() throws Exception {
		atuDescription("Scenario1: CM to MP Integration with Approve Option NO");
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().LoginPage().loginIntoChapterMaker(); 			//Log-In
		Thread.sleep(1000);
		
		//Search and Update Package	
		strDTParametersNValues = "InputDataRow=>5";
		PPCApplicaton().SearchPage().SearchTitle_CM("MultiSearch");	//Single - Multiple Criteria Search Page
		
//		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("Yes");	//Validate Chapter Marks Page
		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("No");	//Validate Chapter Marks Page
		
		PPCApplicaton().LoginPage().logOutChapterMaker();						//Log Out
		
		
		strDTParametersNValues = "InputDataRow=>2";
		//Log in Page
		PPCApplicaton().LoginPage().loginIntoPPC();					//Log-In
		
		strDTParametersNValues = "InputDataRow=>5";
		//Search and Update Package	
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("MultiSearch");	//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();			//Update MP Details
		
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(5);  //Get the PO Number from Application and store in Excel sheet		
		PPCApplicaton().SearchPage().SearchPONumber("CMMPIntegration");
		
		PPCApplicaton().SearchPage().ValidateChapterMakerImport("NotApproved");
		
		PPCApplicaton().LoginPage().logOut();						//Log Out

	}	

	
	@Test(priority = 2)
	public void PositiveFlow_2_CMAndMPIntegration_ApproveYes() throws Exception {
		atuDescription("Scenario2: CM to MP Integration with Approve Option YES");
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().LoginPage().loginIntoChapterMaker(); 			//Log-In
		Thread.sleep(1000);
		
		//Search and Update Package	
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().SearchPage().SearchTitle_CM("MultiSearch");	//Single - Multiple Criteria Search Page
		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("Yes");	//Validate Chapter Marks Page
//		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("No");	//Validate Chapter Marks Page
		PPCApplicaton().LoginPage().logOutChapterMaker();						//Log Out
		
		strDTParametersNValues = "InputDataRow=>2";
		//Log in Page
		PPCApplicaton().LoginPage().loginIntoPPC();					//Log-In
		
		strDTParametersNValues = "InputDataRow=>2";
		//Search and Update Package	
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("MultiSearch");	//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();			//Update MP Details
		
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(2);  //Get the PO Number from Application and store in Excel sheet		
		PPCApplicaton().SearchPage().SearchPONumber("CMMPIntegration");
		
		PPCApplicaton().SearchPage().ValidateChapterMakerImport("Approved");
		
		PPCApplicaton().LoginPage().logOut();						//Log Out
	}	
	
//	@Test(priority = 3)
	public void PositiveFlow_3_CMSingleAndBulkInsert() throws Exception {
		atuDescription("Scenario3: Chapter Mark to Validate Single and Bulk Insert");
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().LoginPage().loginIntoChapterMaker(); 			//Log-In
		Thread.sleep(1000);
		
		//Search and Update Package	
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().SearchPage().SearchTitle_CM("MultiSearch");	//Single - Multiple Criteria Search Page
		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("Yes");	//Validate Chapter Marks Page
//		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("No");	//Validate Chapter Marks Page
		PPCApplicaton().LoginPage().logOutChapterMaker();						//Log Out
		
		strDTParametersNValues = "InputDataRow=>2";
		//Log in Page
		PPCApplicaton().LoginPage().loginIntoPPC();					//Log-In
		
		strDTParametersNValues = "InputDataRow=>2";
		//Search and Update Package	
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("MultiSearch");	//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();			//Update MP Details
		
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(2);  //Get the PO Number from Application and store in Excel sheet		
		PPCApplicaton().SearchPage().SearchPONumber("CMMPIntegration");
		
		PPCApplicaton().SearchPage().ValidateChapterMakerImport("Approved");
		
		PPCApplicaton().LoginPage().logOut();						//Log Out
	}	
public void atuDescription(String strDescription)
	{
		ATUReports.indexPageDescription = "Media Package automation";
		ATUReports.setAuthorInfo("SUNDAR: Digital Media Archives QA Team", Utils.getCurrentTime(), "1.0");
		ATUReports.currentRunDescription = strDescription;
	}
}