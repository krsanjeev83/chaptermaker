package com.newdemo.framework.base;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
//import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


public class ComponentFunctions {
	public Properties objCMPProperties = new Properties();
	public CommonFunctions objCMNFunctions = new CommonFunctions();
	public GlobalPaths objGLPaths = null;

	// PAGE LOADING PERFORMANCE
//	WebDriverBackedSelenium objWBBackedSelenium = null;
	JavascriptExecutor objJSExecutor = null;
	public String strErrorMsg = "";
	public WebDriver driver = null;
	String strObjectXPATH = "";
	public int int_WAIT_LIMIT = 30;
	public int int_REFRESH_LIMIT = 10;
	public HTMLFunctions objHTMLFunctions = null;
	public HTMLFunctions objHTMLFunction = null;
	
	// CONSTRUCTOR FOR COMPONENT REUSABLE FUNCTIONS
	public ComponentFunctions(WebDriver objTempWebDriver) throws Exception {
		objHTMLFunctions = new HTMLFunctions(objTempWebDriver);
		objHTMLFunction = new HTMLFunctions(objTempWebDriver);
		objGLPaths = new GlobalPaths();
		// ==========================INITIALIZE THE WEBDRIVER OBJECT INSIDE COMPONENT REUSABLE FUNCTIONS======
		driver = objTempWebDriver;
		objJSExecutor = (JavascriptExecutor) driver;
	}

	
	public boolean captureScreenshotOfElement(WebElement objWebElement, String strObjectName, String strFilePath)
			throws IOException {

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			BufferedImage fullImg = ImageIO.read(screenshot);
			Point point = ((Locatable) objWebElement).getCoordinates().inViewPort();

			int eleWidth = objWebElement.getSize().getWidth();
			int eleHeight = objWebElement.getSize().getHeight();

			System.out.println("Screenshot x:=" + point.getX());
			System.out.println("Screenshot y:=" + point.getY());

			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(),
					point.getY() + objGLPaths.intChromeMenubarHeight + 1, eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);

			FileUtils.copyFile(screenshot, new File(strFilePath + strObjectName + ".png"));

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
//#######################	
//BASIC RESUABLE METHODS
//########################
				
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void switchToAlertAccept() {
		driver.switchTo().alert().accept();
	}

	public void waiting(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

		
// ========================================1_TO LAUNCH THE APPLICATION URL IN ANY BROWSER=======================
	public Boolean launchURL(String strURL) throws Exception {
		try {
			driver.get(strURL);
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			return false;
		}
	}

// ========================================2_TO CLICK ANY OBJECT IN THE APPLICATION =================================
	public Boolean clickObject(WebElement objWebElement, String strObjectName) throws Exception {
		try {
//			scrollElementToView(objWebElement, strObjectName);
			objWebElement.click();
			String strObjectXPATH = "";
				
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked","Event", "Click " + strObjectName + " Link", "" + strObjectName + " clicked Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			objHTMLFunctions.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true", "true");
			System.out.println("clicked:"+ strObjectName+ "     :- Pass");
			return true;
			
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked","Event", "Click " + strObjectName + " Link", "" + strObjectName + " NOT clicked",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			objHTMLFunctions.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg, "true", "false");
			System.out.println("Failed to click:"+ strObjectName+ "     :- Fail");
			return false;
		}
	}
	
	// ========================================2_1TO CLICK ANY OBJECT IN THE APPLICATION USING JAVA SCRIPT=================================
	public Boolean clickObjectUsingJSExecutor(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", objWebElement);
			String strObjectXPATH = "";
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked","Event", "Click " + strObjectName + " Link", "" + strObjectName + " clicked Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			System.out.println("clicked:"+ strObjectName+ "     :- Pass");
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked","Event", "Click " + strObjectName + " Link", "" + strObjectName + " NOT clicked",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			System.out.println("Failed to click:"+ strObjectName+ "     :- Fail");
			return false;
		}
	}

// ========================================3_CLEAR AND TYPE VALUE IN OBJECT=================================================
	public Boolean clearAndTypeValue(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			objWebElement.clear();
			objWebElement.sendKeys(strInputValue);
			String strObjectXPATH = "";
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strInputValue + "</i> Entered", ""+strObjectName+"",  "Enter: " +strInputValue, "" + strObjectName+ ": Value Entered Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			objHTMLFunctions.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true", "true");
			System.out.println("Entered Value:"+ strInputValue + "     :- Pass");
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strInputValue + "</i> Entered", ""+strObjectName+"",  "Enter: " +strInputValue, "" + strObjectName+ ": Value NOT Entered",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			objHTMLFunctions.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true", "false");
			System.out.println("Failed to Enter Value:"+ strInputValue + "     :- Fail");
			return false;
		}
	}

	//========================================3_1_TYPE VALUE IN OBJECT=================================================
		public Boolean TypeValue(String strElementXPATH, String strObjectName, String strInputValue) throws Exception
		{
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try
			{
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				objWebElement.clear();
				Thread.sleep(500);
				objWebElement.sendKeys(strInputValue);
				ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Entered", ""+strInputValue+"",  "Enter: " +strInputValue, "" + strObjectName+ ": Value Entered Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				System.out.println("Entered Value:"+ strInputValue+ "     :- Pass");
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Entered", ""+strInputValue+"",  "Enter: " +strInputValue, "" + strObjectName+ ": Value NOT Entered",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				System.out.println("Failed to Enter Value:"+ strInputValue+ "     :- Fail");
				return false;
			}
		}
		
		//========================================3_2_TYPE VALUE IN OBJECTUSING JAVA SCRIPT EXECUTOR=================================================	
		
		public Boolean typeValueUsingJSExecutor(WebElement objWebElement, String strObjectName, String strInputValue)
				throws Exception {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value=\"" + strInputValue + "\";", objWebElement);

				String strObjectXPATH = "";
				if (objWebElement.toString().contains("->")) {
					strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
					strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
				}
				System.out.println("Entered Value:"+ strInputValue+ "     :- Pass");
				return true;
			} catch (Exception objException) {
				strErrorMsg = GetExceptionNDisplay(objException, true);
				System.out.println("Entered Value:"+ strInputValue+ "     :- Fail");
				return false;
			}
		}
		
// ==================================4_SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
	public Boolean selectValueFromDropDownList(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByVisibleText(strInputValue);
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strInputValue + "</i> Select", ""+strObjectName+"",  "Selected : " +strInputValue, "" + strObjectName+ ": Value Selected Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			System.out.println("Entered Value:"+ strInputValue+ "     :- Pass");
//			objHTMLFunctions.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Selected", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strInputValue + "</i> Select", ""+strObjectName+"",  "Selected: " +strInputValue, "" + strObjectName+ ": Value NOT Selected",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			System.out.println("Entered Value:"+ strInputValue+ "     :- Fail");
//			objHTMLFunctions.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Selected", "true", "false");
			return false;
		}
	}

// ===================================5_CHECK IF ALERT PRESENT AND ACCEPT IT==================================================
	public Boolean checkNAcceptAlert(int intWaitTime) throws Exception {
		Boolean blnAleartExists = false;
		try {
			WebDriverWait objWebDriverWait = new WebDriverWait(driver, intWaitTime);
			try {
				Alert objAlertExists = objWebDriverWait.until(ExpectedConditions.alertIsPresent());
				blnAleartExists = true;
			} catch (Exception e) {

			}
			if (blnAleartExists == true) {
				Alert objAlert = driver.switchTo().alert();
				String strAlertText = objAlert.getText();
				objAlert.accept();

			}
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			return false;
		}
	}
	
//=======================================6_WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public int Count_No_Of_Element(String strElementXPATH) {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		int temp = driver.findElements(By.xpath(strObjectXPATH)).size();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return temp;
	}
	
	
	// =================================7_1_WAIT TILL THE OBJECT IS CLIKABLE USING WEBELEMENT======================================

	public Boolean waitTillElementDisplayed(WebElement objWebElement, String strObjectName, int intWaitTime)
			throws Exception {
		try {
			Thread.sleep(1000);
			(new WebDriverWait(driver, intWaitTime)).until(ExpectedConditions.visibilityOf(objWebElement));
			System.out.println(strObjectName + ": Object Displayed" +"      :- Pass");
			return true;
		} catch (Exception objException) {
			strErrorMsg = GetExceptionNDisplay(objException, true);
			System.out.println(strObjectName + ": Object Not Displayed" +"      :- Failed");
			return false;
		}
	}

	//==================================7_2_WAIT TILL THE OBJECT IS CLIKABLE USING XPATH============================================
		public Boolean waitTillElementEnabled(String strElementXPATH, String strObjectName, int intWaitTime)
				throws Exception {
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try {
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				(new WebDriverWait(driver, intWaitTime)).until(ExpectedConditions.elementToBeClickable(objWebElement));
						System.out.println("Object Displayed"+ strObjectName);
						return true;
			} catch (Exception objException) {
				strErrorMsg = GetExceptionNDisplay(objException, true);
				System.out.println("Object Not Displayed"+ strObjectName);
				return false;
			}
		}
		
	//==================================8_SET RADIO OPTION OR CHECKBOX TO ON/OFF============================================
		public boolean selectRadioOrCheckBox(WebElement objWebElement, final String strObjectName, String strONOFF) throws Exception
		{
			boolean strIsObjectSelected;
			try
			{
				strIsObjectSelected = objWebElement.isSelected();
				if(strONOFF=="OFF")
				{
					strONOFF = "OFF";
				}
				if(strONOFF.equalsIgnoreCase("ON"))
				{
					if(strIsObjectSelected == false)
					{
						objWebElement.click();
					}
				}
				else
				{
					if(strIsObjectSelected == true)
					{
						objWebElement.click();
					}
				}
				ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Select Option ", ""+strONOFF+"",  strONOFF, strONOFF+"Selected Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> Select Option ", ""+strONOFF+"",  strONOFF, strONOFF+"Failed to Selected",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				return false;
			}
		}
		
		//==================================8_1_SET WEBCHECK BOX TO ON/OFF============================================
		public boolean selectRadioOrCheckBox(String strElementXPATH, final String strObjectName, String strONOFF) throws Exception
		{
			boolean strIsObjectSelected;
			try
			{
				strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				scrollElementToView(strObjectXPATH, strObjectName);
				
				strIsObjectSelected = objWebElement.isSelected();
				if(strONOFF=="")
				{
					strONOFF = "OFF";
				}
				if(strONOFF.equalsIgnoreCase("ON"))
				{
					if(strIsObjectSelected == false)
					{
						objWebElement.click();
					}
				}
				else
				{
					if(strIsObjectSelected == true)
					{
						objWebElement.click();
					}
				}
				System.out.println("Object <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName +
						"</i> is set <b>" + strONOFF + "</b>");
	
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
				System.err.println("Unable to set <b>" + strONOFF + "</b> in element " + "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" +
						".<BR>Error message=>" + strErrorMsg);
				return false;
			}
		}
	
		
		//========================================9_CHECK IF OBJECT EXISTS=================================================
		public Boolean checkObjectExists(WebElement objWebElement, String strObjectName) throws Exception
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				Boolean blnObjectExists = waitTillElementDisplayed(objWebElement, strObjectName, 3);
				String strObjectXPATH = "";
				
				
				if(blnObjectExists==true)
				{
					ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", ""+objWebElement+"",  "Display" +objWebElement, "" + strObjectName+ ": Displayed Sucessfully",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else
				{
					ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", ""+objWebElement+"",  "Display" +objWebElement, "" + strObjectName+ ": Displayed Sucessfully",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				System.out.println("Object Displayed : "+ strObjectName);
				return true;   
			}
			catch(Exception objException)
			{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				strErrorMsg = GetExceptionNDisplay(objException, true);
				ATUReports.add("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", ""+objWebElement+"",  "Display" +objWebElement, "" + strObjectName+ ": Displayed Sucessfully",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				System.out.println("Object Not Displayed : "+ strObjectName);
				return false;
			}
		}
		
	
		//========================================10_CHECK IF OBJECT EXISTS=================================================
		public Boolean selectNTypeValue(String strElementXPATH, String strObjectName, String strInputValue)
				throws Exception {
			try {
				strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				objWebElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), strInputValue);

			
				return true;
			} catch (Exception objException) {
				strErrorMsg = GetExceptionNDisplay(objException, true);
				
				return false;
			}
		}
		
		//=================================11_SCROLL DOWN TILL ELEMENT VISIBLE AND CLICK======================================
		public boolean scrollDownTillElementVisibleNClick(String strElementXPATH, String strObjectName) throws Exception
		{
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try
			{
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				
				Robot robot1 = new Robot();
				
				int intElementY = 0;
				try
				{
					objWebElement.click();
					intElementY = 900;
				}
				catch(Exception e)
				{
					
					intElementY = 1500;
				}
				System.out.println(intElementY);
				while(intElementY > 1000)
				{
					robot1.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(1000);
					robot1.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					try
					{
						objWebElement.click();
						intElementY = 900;
					}
					catch(Exception e)
					{
						
						intElementY = 1500;
					}
					System.out.println(intElementY);
				}
				
				int intX = ((Locatable)objWebElement).getCoordinates().inViewPort().getX();
				int intY = ((Locatable)objWebElement).getCoordinates().inViewPort().getY();
				
				robot1.mouseMove(intX + 5, intY + 5 + objGLPaths.intChromeMenubarHeight);
				robot1.mousePress(InputEvent.BUTTON1_MASK);
				robot1.mouseRelease(InputEvent.BUTTON1_MASK);
				
				String strObjectXPATH = "";
				if(objWebElement.toString().contains("->"))
				{
					strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
					strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
				}

				return true;
			}
			catch(Exception objException)
			{
				
				return false;
			}
		}
		
		//=================================12_1_METHOD TO GET TEXT FROM ELEMENT OF THE APPLICATION AT RUN TIME======================================
	
		public String getTextFromElement(String strElementXPATH, String strObjectName) throws Exception
		{
			String strDisplayedText = "";
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try
			{
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

				return strDisplayedText;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				return "";
			}
		}
		
		//==================================12_2_METHOD TO CAPTURE THE LIST OF ATTRIBUTES FROM ELEMENT============================================
		public String getAttributeFromElement(WebElement objWebElement, String strObjectName, String strAttributeName) throws Exception
		{
			String strDisplayedText = "";
			try
			{
				
				strDisplayedText = objWebElement.getAttribute(strAttributeName).trim().replaceAll("\\s+", " ");
				String strObjectXPATH = "";
				if(objWebElement.toString().contains("->"))
				{
					strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
					strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
				}
				return strDisplayedText;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				return "";
			}
		}
		
		//==================================13_1_METHOD TO SWITCH TO NEW FRAME============================================
		
		public boolean switchToFrame(String strElementXPATH, String strObjectName) throws Exception
		{
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try
			{
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				driver.switchTo().frame(objWebElement);
				String strObjectXPATH = "";
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				return false;
			}
		}
		
		//==================================13_2_METHOD TO SWITCH BACK TO WINDOW PAGE FROM AN FRAME==========================
		public boolean switchToDefaultPage() throws Exception
		{
			try
			{
				driver.switchTo().defaultContent();
				String strDefaultPageTitle = driver.getTitle();
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = GetExceptionNDisplay(objException, true);
				return false;
			}
		}

		//=======================================14_SWITCH TO NEW WINDOW================================================================
		public Boolean switchToNewWindow() throws Exception
		{
			try
			{
				if(driver.getWindowHandles().size() > 1)
				{
					for(String objWindowHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(objWindowHandle);
					}
					String strNewWindowTitle = driver.getTitle();

				}
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
				return false;
			}
		}
		
		//=======================================15_SWITCH BACK TO PARENT WINDOW================================================================
		
		public Boolean switchToParentWindow() throws Exception
		{
			try
			{
					for(String objWindowHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(objWindowHandle);
						break;
					}
					String strNewWindowTitle = driver.getTitle();

				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
				return false;
			}
		}
		
		//=======================================16_TO DOUBLE CLICK ANY OBJECT IN THE APPLICATION================================================================
		public boolean doubleClick(WebElement objWebElement, String strObjectName) throws Exception
		{
			try
			{
				Actions builder = new Actions(driver);
				builder.doubleClick(objWebElement);
				Action objAction = builder.build();
				objAction.perform();
				String strObjectXPATH = "";
				if(objWebElement.toString().contains("->"))
				{
					strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
					strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
				}

				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
				return false;
			}
		}
		
		//=======================================17_ENTER PAST AND FUTURE DATE================================================================
		
		public String EnterDate(int DateDiff) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, DateDiff); 

			return dateFormat.format(cal.getTime());
		}
		
		
		public Boolean scrollElementToView(String strElementXPATH, String strObjectName) throws Exception
		{
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		
			try
			{
				WebElement objWebElement = findElementNReturn(strObjectXPATH);
				JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", objWebElement);
				return true;
			}
			catch(Exception objException)
			{
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
				return false;
			}
		}
		
		


	//#######################
	//OTHER DEPENDANT METHODS
	//#######################
	public String replaceExternalDataInObject(String strObjectNameNXPATH) {
		String strObjXPATH = "";
		String[] arrObjectNameNRepString = strObjectNameNXPATH.split("--");
		String strTempXPATH = arrObjectNameNRepString[0];
		strObjXPATH = strTempXPATH;

		if (arrObjectNameNRepString.length == 2) {
			strObjXPATH = strTempXPATH.replaceAll("EXTERNALDATA", arrObjectNameNRepString[1]);
		} else if (arrObjectNameNRepString.length > 2) {
			for (int intExtData = 1; intExtData < arrObjectNameNRepString.length; intExtData++) {
				strObjXPATH = strObjXPATH.replaceAll("EXTERNALDATA" + intExtData, arrObjectNameNRepString[intExtData]);
			}
		}
		return strObjXPATH;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}
	
	
	///------------------GET OS Info-----------------------		
	 public static String OSDetector () {
         String os = System.getProperty("os.name").toLowerCase();
         if (os.contains("win")) {
                         return "Windows";
         } else if (os.contains("nux") || os.contains("nix")) {
                         return "Linux";
         }else if (os.contains("mac")) {
                         return "Mac";
         }
         return os;
        
}
	// ========================GET EXCEPTION MESSAGE AND DISPLAY IT===========================================
	public String GetExceptionNDisplay(Exception objException, boolean blnIsDisplay) throws Exception {
		String strException = objException.getMessage();
		if (strException != null) {
			String[] arrException = strException.split("\n");
			if (blnIsDisplay == true) {
				System.out.println("Exception occurred " + arrException[0]);
			}
			return "<font color='blue'>" + arrException[0] + "</font>";
		} else {
			return "<font color='blue'>No specific error message thrown from driver for the current step. Check error message in previous steps</font>";
		}
	}
	
	//==========================FIND ELEMENT USING XPATH AND RETURN IT=======================
	public WebElement findElementNReturn(String strObjDetails) throws Exception
	{
		try
		{
			WebElement objWebElement = driver.findElement(By.xpath(strObjDetails));
			return objWebElement;
		}
		catch(Exception objException)
		{
			System.out.println(GetExceptionNDisplay(objException, true));
			return null;
		}
	}
	
	public Boolean scrollElementToView(WebElement objWebElement, String strObjectName) throws Exception
	{
		try
		{
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", objWebElement);
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			return false;
		}
	}
	
	public int count(String strElementXPATH) {
		try {
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			int intCount = driver.findElements(By.xpath(strObjectXPATH)).size();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return intCount;
		} catch (Exception objException) {
			return 0;
		}
	}

	
	//for comparing 2 xml file--in progress
	public static class TestXml {

	    public static void main(String[] args) throws Exception {
	        String result = "<abc             attr=\"value1\"                title=\"something\">            </abc>";
	        // will be ok
	        assertXMLEquals("<abc attr=\"value1\" title=\"something\"></abc>", result);
	    }

	    public static void assertXMLEquals(String expectedXML, String actualXML) throws Exception {
	        XMLUnit.setIgnoreWhitespace(true);
	        XMLUnit.setIgnoreAttributeOrder(true);

	        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

	        List<?> allDifferences = diff.getAllDifferences();
	        Assert.assertEquals(0, allDifferences.size(), "Differences found: "+ diff.toString());
	    }

	}
	
	//========================================FIND MULTIPLE ELEMENTS USING XPATH AND RETURN THE COLLECTION=======================
		public List<WebElement> findMultipleElementsNReturn(String strObjectName) throws Exception
		{
			strObjectXPATH = replaceExternalDataInObject(strObjectName);
			try
			{
				List<WebElement> objCLXNWebElements = driver.findElements(By.xpath(strObjectXPATH));
				return objCLXNWebElements;
			}
			catch(Exception objException)
			{
				System.out.println(GetExceptionNDisplay(objException, true));
				return null;
			}
		}
	
//=======================Wait Till the Element Displayed / Enabled in any page=====================
	public void LoopAndWait(String strElementXPATH) throws Exception {

		// Wait and Loop
		int intctr = 1;
		while (count(strElementXPATH) >0) {
			Thread.sleep(2000);
			intctr++;
			if (intctr > int_WAIT_LIMIT) {
				break;
			}
		}
	}
	
	public void LoopTillObjectEnable(String strElementXPATH) throws Exception {

		int intctr;
								
		Thread.sleep(1000);
		
		intctr=1;
		while(Count_No_Of_Element(strElementXPATH)>0){
			Thread.sleep(1000);
			intctr++;
			if (intctr>int_WAIT_LIMIT){
				break;
			}
		}
}
	
	//=======================Wait Till the Element Disable in any page=====================	
	public void LoopTillObjectDisable(String strElementXPATH) throws Exception {

			int intctr;
			Thread.sleep(1000);
			
			intctr=1;
			while(Count_No_Of_Element(strElementXPATH)>0){
				Thread.sleep(1000);
				intctr++;
				if (intctr>int_WAIT_LIMIT){
					break;
				}
			}
	}


	public void waitUntilSleepLimit(String strElementXPATH, String strObjectName, int intWaitLimit) throws Exception
	{
		Thread.sleep(1000);
		int intctr = 1;
		while(Count_No_Of_Element(strElementXPATH)<1){
			Thread.sleep(1000);
			intctr++;
			if (intctr>intWaitLimit){
				break;
			}
		}
	}
	public void sendTABkey() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(500);
	    robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void sendENTERkey() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	
	//////////////////////////////
	
	//==================================CLICK ON CO-ORDINATES============================================
	public boolean clickOnCoordinates(String strInputValue) throws Exception
	{
		Robot objRobot = new Robot();
		String[] arrCoordinates = strInputValue.split("--");

		try
		{
			objRobot.mouseMove(Integer.parseInt(arrCoordinates[0]), Integer.parseInt(arrCoordinates[1]));
			objRobot.mousePress(InputEvent.BUTTON1_MASK);
			objRobot.mouseRelease(InputEvent.BUTTON1_MASK);
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			return false;
		}
	}
	
	//==================================CLICK ON CO-ORDINATES============================================
	public boolean clickOnCoordinatesUsingActionBuilder(int intX, int intY) throws Exception
	{
//		String[] arrCoordinates = strInputValue.split("--");

		try
		{
			Actions actions = new Actions(driver);
			actions.moveByOffset(intX, intY).click();
			
			Action action = actions.build();
			action.perform();
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			return false;
		}
	}
	
}
