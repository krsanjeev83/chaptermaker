package com.newdemo.test;

import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.Test;
import com.newdemo.framework.base.ScriptBase;
import com.sun.java_cup.internal.runtime.Scanner;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class MediaPackageScenarios extends ScriptBase {
	


//	@Test(priority = 1)  																					//Basic flow to validate MP details and upload Multiple poster
	public void PositiveFlow_1_UploadFilesMediaPackageUpdate() throws Exception {
		atuDescription("E2E Scenario 1: Basic flow to validate MP details and upload Multiple poster");
		htmlFunctions.ReportPassFail("Scenario=>"+ "GETTING SYSTEM TIME AND SAVING IN EXCEL SHEET", "ModuleName", "ModuleName");
		strDTParametersNValues = "InputDataRow=>2";
		//Log in Page
		htmlFunctions.ReportPassFail("Scenario=>"+ "LOG IN TO MEDIA PACKAGE", "ModuleName", "ModuleName");
		PPCApplicaton().LoginPage().loginIntoPPC();															//Log-In
		
		strDTParametersNValues = "InputDataRow=>2";
		//Search and Update Package	
		Thread.sleep(1000);
		htmlFunctions.ReportPassFail("Scenario=>"+ "SEARCH WITH WPR", "ModuleName", "ModuleName");
		PPCApplicaton().SearchPage().SearchTitle("WPRSearch");												//WPR Criteria Search Page
		htmlFunctions.ReportPassFail("Scenario=>"+ "UPDATE MEDIA PACKAGE DETAILS", "ModuleName", "ModuleName");
		PPCApplicaton().SearchPage().UpdateMediaPackage();													//Update MP Details
		htmlFunctions.ReportPassFail("Scenario=>"+ "UPDATE MULTIPLE POSTERS", "ModuleName", "ModuleName");
		PPCApplicaton().SearchPage().UploadPoster();														//Upload Multiple Poster
		htmlFunctions.ReportPassFail("Scenario=>"+ "LOG OUT", "ModuleName", "ModuleName");
		PPCApplicaton().LoginPage().logOut();																//Log Out
	}
	
	
	@Test(priority = 2)   																					//E2E Basic flow to validate Platform Type MoviesAnywhere
	public void PositiveFlow2_Platform_MoviesAnywhere()  throws Exception {
		
		atuDescription("E2E Scenario 2: Basic flow to validate Platform Type MoviesAnywhere");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In
		
		strDTParametersNValues = "InputDataRow=>4";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("WPRSearch");					//WPR Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(4);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multiple Poster
		PPCApplicaton().SearchPage().SearchPONumber("");						//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>3";							
		
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature

		//Search and Verify Status is getting updated to ASPERA_COMPLETED
		strDTParametersNValues = "InputDataRow=>4";
//		PPCApplicaton().SearchPage().EnterPONumber();							//Search PO Number from Packages Page
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();									//Log Out
	}
	
	
//	@Test(priority = 3)   														//E2E Basic flow to validate Platform Type Microsoft
	public void PositiveFlow3_Platform_Microsoft()  throws Exception {
		
		atuDescription("E2E Scenario 3: Basic flow to validate Platform Type Microsoft");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In
		
		strDTParametersNValues = "InputDataRow=>6";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(6);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multipe Poster
		PPCApplicaton().SearchPage().SearchPONumber("");						//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature, Trailer, Subtitle and Caption

		
		//Search and Verify Status is getting updated to ASPERA_COMPLETED
		strDTParametersNValues = "InputDataRow=>6";
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();									//Log Out
	}
	
//	@Test(priority = 4)   														//E2E Basic flow to validate Platform Type SONYSNEI
	public void PositiveFlow4_Platform_SONYSNEI()  throws Exception {
		
		atuDescription("E2E Scenario 4: Basic flow to validate Platform Type SONYSNEI");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In
		
		strDTParametersNValues = "InputDataRow=>6";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(6);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multipe Poster
		PPCApplicaton().SearchPage().SearchPONumber("");						//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature, Trailer, Subtitle and Caption

		//Search and Verify Status is getting updated to ASPERA_COMPLETED
		strDTParametersNValues = "InputDataRow=>6";
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();									//Log Out
	}
	
//	@Test(priority = 5) 														//E2E Basic flow to validate all MetaDeta Error and Its Navigation 
	public void PositiveFlow_5_CreateMPAndValidateMetaDataError() throws Exception {
		atuDescription("E2E Scenario 4: E2E Basic flow to validate all MetaDeta Error and Its Navigation ");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In
		
		strDTParametersNValues = "InputDataRow=>7";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Search
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(7);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multipe Poster
		PPCApplicaton().SearchPage().SearchPONumber_MedaError("7");
		PPCApplicaton().SearchPage().ValidateMetaDetaError("EIDR");				//Validate all MetaDeta Errors - EIDR navigation Flow
		
		PPCApplicaton().SearchPage().SearchPONumber_MedaError("7");
		PPCApplicaton().SearchPage().ValidateMetaDetaError("Synopsis");			//Validate all MetaDeta Errors - Synopsis navigation Flow
		PPCApplicaton().SearchPage().SearchPONumber_MedaError("7");
		PPCApplicaton().SearchPage().ValidateMetaDetaError("Genre");			//Validate all MetaDeta Errors - Genre navigation Flow
		PPCApplicaton().SearchPage().SearchPONumber_MedaError("7");
		PPCApplicaton().SearchPage().ValidateMetaDetaError("Rating");			//Validate all MetaDeta Errors - Rating navigation Flow
		PPCApplicaton().SearchPage().SearchPONumber_MedaError("7");
		PPCApplicaton().SearchPage().ValidateMetaDetaError("Talent");			//Validate all MetaDeta Errors - Talent navigation Flow
		PPCApplicaton().LoginPage().logOut();									//Log Out
	}
	
	
//	@Test(priority = 6)  
	public void PositiveFlow_6_CreateMPWithAllPosterType_Submit() throws Exception {
		atuDescription("Scenario5: Media Package Basic Smoke Flow");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In
		
		strDTParametersNValues = "InputDataRow=>10";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Version Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(10);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().SearchPONumber("");
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("GulliverTravels");    // Adding Video, Audio, Subtitle and Caption for delivery

		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().SearchPage().SearchPONumberE2E("");
		
		//Verify Status is getting updated to 	QMANAGER_TRANSFER_COMPLETED-->ASPERA_COMPLETED
//		PPCApplicaton().PackageBuilderPage().VerifyMPStatus("AsperaComplete");	//Verify the Status ASPERA COMPLATE
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();									//Log Out
		
	}
	
//	@Test(priority = 7)   														//E2E Basic flow to validate Platform Type Microsoft
	public void PositiveFlow_7_GDS_CSR_CREATE_And_FMS_Operator_SUBMIT()  throws Exception {
		
		atuDescription("E2E Scenario 6: Basic flow to validate Platform Type Microsoft");
		strDTParametersNValues = "InputDataRow=>6";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In as GDS Operator
		
		strDTParametersNValues = "InputDataRow=>8";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(8);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multipe Poster
		PPCApplicaton().LoginPage().logOut();									//Log Out GDS_Operator
		
		//Log in FMS Operator and Submit the package
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In as FMS Operator
		
		strDTParametersNValues = "InputDataRow=>8";
		PPCApplicaton().SearchPage().SearchPONumber("8");						//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>3";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature, Trailer, Subtitle and Caption							
		
		//Search and Verify Status is getting updated to ASPERA_COMPLETED
		strDTParametersNValues = "InputDataRow=>8";
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();	
	}

//	@Test(priority = 8)   														//E2E Basic flow to validate Platform Type Microsoft
	public void PositiveFlow_8_GDS_ADMIN_CREATE_And_FMS_ADMIN_SUBMIT()  throws Exception {
		
		atuDescription("E2E Scenario 6: Basic flow to validate Platform Type Microsoft");
		strDTParametersNValues = "InputDataRow=>8";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In as GDS Operator
		
		strDTParametersNValues = "InputDataRow=>9";
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("JDEVersionSearch");			//JDE Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();						//Update MP Details
		PPCApplicaton().SearchPage().getValueAndSaveInExcelFile(9);  			//Get the PO Number from Application and store in Excel sheet
		PPCApplicaton().SearchPage().UploadPoster();							//Upload Multipe Poster
		PPCApplicaton().LoginPage().logOut();									//Log Out GDS_Operator
		
		//Log in FMS Operator and Submit the package
		strDTParametersNValues = "InputDataRow=>9";
		PPCApplicaton().LoginPage().loginIntoPPC();								//Log-In as FMS Operator
		
		
		strDTParametersNValues = "InputDataRow=>9";
		PPCApplicaton().SearchPage().SearchPONumber("9");						//Search PO Number from Packages Page
		
		//Package Builder
		strDTParametersNValues = "InputDataRow=>4";
		PPCApplicaton().PackageBuilderPage().PackageBuilder_SelectFlow("FaultInOurStars");  //Select the Feature, Trailer, Subtitle and Caption							
		
		//Search and Verify Status is getting updated to ASPERA_COMPLETED
		strDTParametersNValues = "InputDataRow=>9";
		PPCApplicaton().SearchPage().EnterPONumberAndValidateStatus();			//Search PO Number from Packages Page and Verify the Status ASPERA COMPLATE
		
		PPCApplicaton().LoginPage().logOut();	
	}
public void atuDescription(String strDescription)
	{
		ATUReports.indexPageDescription = "Media Package automation";
		ATUReports.setAuthorInfo("sundar babu: Digital Media Archives QA Team", Utils.getCurrentTime(), "1.0");
		ATUReports.currentRunDescription = strDescription;
	}
}






/*	@Test(priority = 3)

public void CompareTwoXML() throws Exception {
	PPCApplicaton().SearchPage().ComparisonTest();
        }
        //	@Test(priority = 1)
	public void PositiveFlow_1_MediaPackageSearchAndUpdate() throws Exception {
		atuDescription("Scenario1: Media Package Basic Smoke Flow to Search ");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC(); 				//Log-In
		Thread.sleep(1000);
		strDTParametersNValues = "InputDataRow=>2";
		//Search and Update Package	
		PPCApplicaton().SearchPage().SearchTitle("SingleSearch");	//Single Criteria Search Page
		PPCApplicaton().LoginPage().logOut();						//Log Out Page
		
		atuDescription("Scenario2: Media Package Basic Search with 2 or more Criteria");
		strDTParametersNValues = "InputDataRow=>2";
		PPCApplicaton().LoginPage().loginIntoPPC();					//Log-In
		Thread.sleep(1000);
		//Search and Update Package	
		PPCApplicaton().SearchPage().SearchTitle("MultiSearch");  	//Multiple Criteria Search Page
		PPCApplicaton().LoginPage().logOut();						//Log Out
	}	
	
		@Test(priority = 2)
	public void PositiveFlow_2_CreateMPWithAllPosterType() throws Exception {
		atuDescription("Scenario3: Media Package Basic Smoke Flow");
		strDTParametersNValues = "InputDataRow=>2";
		//Log in Page
		PPCApplicaton().LoginPage().loginIntoPPC();					//Log-In
		
		strDTParametersNValues = "InputDataRow=>3";
		//Search and Update Package	
		Thread.sleep(1000);
		PPCApplicaton().SearchPage().SearchTitle("SingleSearch");	//Single Criteria Search Page
		PPCApplicaton().SearchPage().UpdateMediaPackage();			//Update MP Details
		PPCApplicaton().SearchPage().UploadPoster();				//Upload Multipe Poster
		PPCApplicaton().LoginPage().logOut();						//Log Out
	}
        *
        */

/*//	@Test(priority = 5)
public void CompareManifestAndMetaDataXML() throws Exception {
	atuDescription("Scenario3: Media Package Basic Smoke Flow");
	
	strDTParametersNValues = "InputDataRow=>6";
	PPCApplicaton().LoginPage().LaunchURL();					//Log-In
	PPCApplicaton().LoginPage().XMLComparision_DifferenceNumbers();				
	PPCApplicaton().LoginPage().getXMLDiffValueFromApplication();
	
	
	strDTParametersNValues = "InputDataRow=>7";
	PPCApplicaton().LoginPage().LaunchURL();					//Log-In
	PPCApplicaton().LoginPage().XMLComparision();					//Log-In

}*/
