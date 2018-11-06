package com.newdemo.framework.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	//#######################################################################################
	//Media Package Login
	//#######################################################################################
		
	//Login xpath
	@FindBy(xpath=".//*[@id='login-sign-in-link']") public WebElement eltSignInClick;
	@FindBy(xpath="//*[contains(text(),'Log in with okta')]") public WebElement eltSignInOkta;
	
	//Regular Log in 
	@FindBy(xpath=".//*[@id='login-email']") public WebElement edtUserName;
	@FindBy(xpath=".//*[@id='login-password']") public WebElement edtPassword;
	@FindBy(xpath=".//*[@id='login-buttons-password']") public WebElement btnSignIn;
	
	//LotOut Xpath
	@FindBy(xpath=".//*[@id='login-name-link']") public WebElement eltSignOutLink;
	@FindBy(xpath=".//*[@class='btn btn-default navbar-btn saml-logout']") public WebElement eltSignOut;
	
	//#######################################################################################
	//Chapter Marker LogIn
	//#######################################################################################
	//Login xpath
	@FindBy(xpath=".//*[contains(text(),'Log in with okta')]") public WebElement eltSignInChapterMaker;
	@FindBy(xpath=".//*[contains(text(),'Log out')]") public WebElement eltSignOutCM;
	public String eltSignOutChapterMaker = ".//*[contains(text(),'Log out')]";
	
	public String edtCM_UserNamecheck = ".//*[@id='okta-signin-username']";
	@FindBy(xpath=".//*[@id='okta-signin-username']") public WebElement edtCM_UserName;
	@FindBy(xpath=".//*[@id='okta-signin-password']") public WebElement edtCM_Password;
	@FindBy(xpath=".//*[@class='button button-primary']") public WebElement btnCM_SignIn;
	
	//#######################################################################################
	//XML Comparision
	//#######################################################################################
	@FindBy(xpath=".//*[@name='file1']") public WebElement eltBaseFile1;
	@FindBy(xpath=".//*[@name='file2']") public WebElement eltManifestFile2;
	@FindBy(xpath=".//*[@class='btn btn-primary']") public WebElement eltShowdifferences;
	public String eltfirstFile = "(.//*[@class='descColumn'])[1]";
	
	public String eltXMLTagAdded = ".//div[@id='colors']/div[contains(text(),'Added')]";
	public String eltXMLFileDifference = ".//*[@id='numDiffs']";
	public String frmPoppedIFrameddes =".//*[@id='resultsFrame']";
	
	@FindBy(xpath=".//*[@class='sa']") public WebElement eltXMLTagAdded1;
	public String eltXMLTagDeleted = ".//*[@class='sd']";
	public String eltXMLTagChanged = "(.//*[contains(text(),'Changed')])[1]";
	public String eltXMLTagChangedInchanged = "(.//*[contains(text(),'Changed')])[2]";
	public String eltXMLIgnored = ".//*[@class='sig']";
	//Different Text
	@FindBy(xpath=".//*[@id='inspector']/div[1]") public WebElement eltdifferenceText;
	//Different Lines
	public String eltXMLDifferenceAllLines = ".//*[@id='currentDiff']";
	
	@FindBy(xpath=".//*[@id='ui-id-2']") public WebElement eltFileUpload;
	@FindBy(xpath=".//*[@id='AsyncFileUploadControl1_ctl02']") public WebElement eltBaseNumberFile1;
	@FindBy(xpath=".//*[@id='AsyncFileUploadControl2_ctl02']") public WebElement eltBaseNumberFile2;
	@FindBy(xpath=".//*[@id='compareButtonFiles']/span[2]") public WebElement eltCompare;
	
}
