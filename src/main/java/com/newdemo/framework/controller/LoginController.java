package com.newdemo.framework.controller;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.newdemo.framework.base.ComponentFunctions;
import com.newdemo.framework.data.LoginData;
import com.newdemo.framework.model.LoginPage;
import org.openqa.selenium.security.UserAndPassword;

public class LoginController extends ComponentFunctions
{
	LoginPage login = null;
	LoginData loginData = null;
	HashMap<String, String> hmOutputData = new HashMap<String, String>();
	public String strParam = "";
	public String [] objParam;
	public LoginController(WebDriver driver) throws Exception 
	{
		super(driver);
		login = PageFactory.initElements(driver, LoginPage.class);
	}
	
	//Reusable function  to Enter Log In Credentials to the application
	public void loginIntoPPC() throws Exception
	{
		launchURL(loginData.strDTURL);
		
		Thread.sleep(1000);
		if (Count_No_Of_Element(login.eltSignOutChapterMaker)>0){
		
			}
		else{
			waitTillElementDisplayed(login.eltSignInClick, "Sign In", 50);
			clickObject(login.eltSignInClick, "Sign In");
			clearAndTypeValue(login.edtUserName, "userName", loginData.strDTUserName);
			Thread.sleep(1000);
			clearAndTypeValue(login.edtPassword, "password", loginData.strDTPassword);
			Thread.sleep(1000);
			clickObject(login.btnSignIn, "SignInChapterMaker");
//			clickObject(login.eltSignInOkta, "Sign In Okta");
			/*sendTABkey();	
			switchToNewWindow();
			clearAndTypeValue(login.edtCM_UserName, "userName", loginData.strDTUserName);
			clearAndTypeValue(login.edtCM_Password, "password", loginData.strDTPassword);
			clickObject(login.btnCM_SignIn, "SignInChapterMaker");
			switchToParentWindow();*/
		}
		
		int intctr = 1;
		while(Count_No_Of_Element(login.eltSignOutChapterMaker)==0){
			intctr++;
			if (intctr>int_WAIT_LIMIT){
				break;
			}
		}
		System.out.println("Logged In to Chapter Mark Sucessfully");
		
	}

	//Reusable function  to Enter Log Out Credentials to the application
	public void logOut() throws Exception
	{
//		clickObject(login.eltSignOutLink, "SignOutLink");
		clickObject(login.eltSignOut, "SignOut");
		deleteAllCookies();
//		driver.quit();
	}
	
	
	//ChapterMaker LogIn Changes
	//05-19-2017
	//Reusable function  to Enter Log In Credentials to the application
		public void loginIntoChapterMaker() throws Exception
		{
			launchURL(loginData.strDTURL);
			Thread.sleep(2000);
//			clickObject(login.eltSignInChapterMaker, "SignInChapterMaker");
			
			Thread.sleep(1000);
			if (Count_No_Of_Element(login.eltSignOutChapterMaker)>0){
				}
			else{
				waitTillElementDisplayed(login.eltSignInClick, "Sign In", 50);
				clickObject(login.eltSignInClick, "Sign In");
				clearAndTypeValue(login.edtUserName, "userName", loginData.strDTUserName);
				clearAndTypeValue(login.edtPassword, "password", loginData.strDTPassword);
				clickObject(login.btnSignIn, "SignInChapterMaker");
//				clickObject(login.eltSignInOkta, "Sign In Okta");
				/*sendTABkey();	
				switchToNewWindow();
				clearAndTypeValue(login.edtCM_UserName, "userName", loginData.strDTUserName);
				clearAndTypeValue(login.edtCM_Password, "password", loginData.strDTPassword);
				clickObject(login.btnCM_SignIn, "SignInChapterMaker");
				switchToParentWindow();*/
			}
			
			int intctr = 1;
			while(Count_No_Of_Element(login.eltSignOutChapterMaker)==0){
				intctr++;
				if (intctr>int_WAIT_LIMIT){
					break;
				}
			}
			System.out.println("Logged In to Chapter Mark Sucessfully");
		}

		public void logOutChapterMaker() throws Exception
		{
			clickObject(login.eltSignOutCM, "SignOutLink");
			deleteAllCookies();
		}
		
		
		
		//Log in to Foxipedia
		public void loginIntoFoxipedia() throws Exception
		{
			/*	
			//Code to handle Basic Browser Authentication in Selenium.
            Alert aa = driver.switchTo().alert();

            Robot a = new Robot();
            aa.sendKeys("host"+"\\"+"user");  

            a.keyPress(KeyEvent.VK_TAB);
            a.keyRelease(KeyEvent.VK_TAB);
            a.keyRelease(KeyEvent.VK_ADD);                

            setClipboardData("password");

            a.keyPress(KeyEvent.VK_CONTROL);
            a.keyPress(KeyEvent.VK_V);
            a.keyRelease(KeyEvent.VK_V);
            a.keyRelease(KeyEvent.VK_CONTROL);
            //Thread.sleep(5000);
            aa.accept();        

            
			driver.switchTo().alert();
			UserAndPassword UP = new UserAndPassword("sundarb","Vadivelan_9631");
			driver.switchTo().alert().authenticateUsing(UP);
			System.out.println("loginSucessfully");
			var alert = driver.switchTo().alert();
			alert.SetAuthenticationCredentials("username", "password");
			alert.Accept();*/
		}
		
		
	
	//Reusable function  to Enter Log In Credentials to the application
		public void LaunchURL() throws Exception
		{
			launchURL(loginData.strDTURL);
		}
		
		
		
		
		
	
		/*
		public void XMLComparision_DifferenceNumbers() throws Exception
		{
			clickObject(login.eltFileUpload, "File Upload");
			clearAndTypeValue(login.eltBaseNumberFile1, "File 1",  "C:/PPCMediaPackageAutomation/XMLComparision/1_MediaPackager_SmokeTest.xml");
			Thread.sleep(1000);
			clearAndTypeValue(login.eltBaseNumberFile2, "File 2",  "C:/PPCMediaPackageAutomation/XMLComparision/2_MediaPackagerScenarioEndToEnd.xml");
			Thread.sleep(1000);
			clickObject(login.eltCompare, "Compare");
			
			/*String strText = getAttributeFromElement(login.eltdifferenceText, "xml Text", "xml Text");
			System.out.println(strText);
			
			String strAdded = getAttributeFromElement(login.eltXMLTagAdded1, "xml Tag Added","");
			System.out.println(strAdded);

			String strDeleted = getTextFromElement(login.eltXMLTagDeleted, "xml Tag Delete");
			System.out.println(strDeleted);

			String strChanged = getTextFromElement(login.eltXMLTagChanged, "xml Tag Changed");
			System.out.println(strChanged);

			String strChangedinChanged = getTextFromElement(login.eltXMLTagChangedInchanged, "xml Tag Changed In Changed");
			System.out.println(strChangedinChanged);

			String strIgnored = getTextFromElement(login.eltXMLIgnored, "xml Tag Ignored");
			System.out.println(strIgnored);
			switchToDefaultPage();
		}
		
		
		public void getXMLDiffValueFromApplication () throws Exception
		{
			switchToFrame(login.frmPoppedIFrameddes, "Switch Frame");
			
			String ManifestFileDiff = getTextFromElement(login.eltXMLFileDifference, "value");
			System.out.println(ManifestFileDiff);
			
			String strAdded = getTextFromElement(login.eltXMLTagAdded, "value");
			System.out.println(strAdded);
			
			String strDeleted = getTextFromElement(login.eltXMLTagDeleted, "xml Tag Delete");
			System.out.println(strDeleted);

			String strChanged = getTextFromElement(login.eltXMLTagChanged, "xml Tag Changed");
			System.out.println(strChanged);

			String strChangedinChanged = getTextFromElement(login.eltXMLTagChangedInchanged, "xml Tag Changed In Changed");
			System.out.println(strChangedinChanged);

			String strIgnored = getTextFromElement(login.eltXMLIgnored, "xml Tag Ignored");
			System.out.println(strIgnored);
			
			
			List<WebElement> objCount = findMultipleElementsNReturn(login.eltXMLDifferenceAllLines);

				for (int itemIterate = 0; itemIterate < objCount.size(); itemIterate++) {
					String strChangesAllLines = getTextFromElement(login.eltXMLDifferenceAllLines, "All Lines difference");
					System.out.println(strChangesAllLines);
			}
			
			switchToDefaultPage();
				
		}
		
		public void XMLComparision() throws Exception
		{
			clearAndTypeValue(login.eltBaseFile1, "File 1",  "C:/PPCMediaPackageAutomation/XMLComparision/1_MediaPackager_SmokeTest.xml");
			Thread.sleep(1000);
			clearAndTypeValue(login.eltManifestFile2, "File 2",  "C:/PPCMediaPackageAutomation/XMLComparision/2_MediaPackagerScenarioEndToEnd.xml");
			clickObject(login.eltShowdifferences, "Show Differences");
			String strPONumber = getAttributeFromElement(login.eltxmlDiffAllText, "xml Diff", "value");
			
			System.out.println(strPONumber);
		}
	*/
		
		
}
