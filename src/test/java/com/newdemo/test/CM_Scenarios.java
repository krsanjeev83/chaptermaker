package com.newdemo.test;

import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Test;
import com.newdemo.framework.base.ScriptBase;
import com.sun.java_cup.internal.runtime.Scanner;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class CM_Scenarios extends ScriptBase {
	
	@Test(priority = 1)
	public void PositiveFlow_1_CMAndMPIntegration_ApproveNo() throws Exception {
		atuDescription("Scenario1: CM to MP Integration with Approve Option NO");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoChapterMaker(); 			//Log-In
		Thread.sleep(1000);
		
		//Search and Update Package	
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().SearchPage().SearchTitle_CM("MultiSearch");	//Single - Multiple Criteria Search Page
		
		PPCApplicaton().ChapterMarksPage().ValidateChapterMarks("No");	//Validate Chapter Marks Page
		
		PPCApplicaton().LoginPage().logOutChapterMaker();						//Log Out

	}	

	
public void atuDescription(String strDescription)
	{
		ATUReports.indexPageDescription = "Media Package automation";
		ATUReports.setAuthorInfo("SUNDAR: Digital Media Archives QA Team", Utils.getCurrentTime(), "1.0");
		ATUReports.currentRunDescription = strDescription;
	}
}