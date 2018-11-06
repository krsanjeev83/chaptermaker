package com.newdemo.framework.base;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.controller.ApplicationController;
import atu.testrecorder.ATUTestRecorder;
import atu.testng.reports.ATUReports;

public class ScriptBase {
	String OperatingSys = OSDetector ();
	Properties objProperties = new Properties();
	{
		try {
			if (OperatingSys.equals("Windows")){
			objProperties.load(new FileReader(System.getProperty("user.dir") + "\\Config\\CONFIG.properties"));	
			System.setProperty("atu.reporter.config", (System.getProperty("user.dir") + "\\Config\\ATU.properties"));
			}
			else
			objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
			System.setProperty("atu.reporter.config", objProperties.getProperty("ATUPropertiesPath"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//==========================APPLICATION VARIABLES============================================
	public ApplicationController PPCApplicaton = null;
	public static String ParameterNValue = null;
	public static String sessionTimeStamp = null;
	public ComponentFunctions componentFunctions = null;
	public CommonFunctions commonFunctions = new CommonFunctions();
    private String sessionId;
	//==========================BROWSER VARIABLES============================================
	public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	public static final String IE_DRIVER_LOG_FILE_PROPERTY = "webdriver.ie.driver.logfile";
	public static final String IE_DRIVER_LOG_LEVEL_PROPERTY = "webdriver.ie.driver.loglevel";
	public static final String JAVA_TEMP_DIR = "java.io.tmpdir";
	public static WebDriver driver = null;
	public static String browser = null;
	public String strDTParametersNValues = "";
	public static HTMLFunctions objHTMLFunctions= null;
	public static HTMLFunctions htmlFunctions= null;

	GlobalPaths objGLPaths = null;
	public static String ConfigFile = "Config/CONFIG.properties";
	public Properties config;

	public enum BROWSER {
		chrome
	}
	
	@Parameters({"browser", "remoteurl", "inputsheet","Module"})
	@BeforeClass
	
	public void LaunchBrowser(String browser, String strDeviceURL, String inputsheet, String Module) throws Exception{
		System.out.println( System.getProperty("user.dir") );
		sessionTimeStamp = convertToPreferredFormatForTimeStamp(System.currentTimeMillis());
		System.out.println("sessionTimeStamp:  "+sessionTimeStamp);
		
			objGLPaths = new GlobalPaths((System.getProperty("user.dir") + "//Input//")+inputsheet); //Windows
			String ConfigFile = "Config/CONFIG.properties";
			 Properties config;
		
		objGLPaths.strBrowserType = browser;
		BROWSER selectedBrowser = null;
		selectedBrowser = BROWSER.valueOf(browser);
		if (selectedBrowser == null) {
			throw new RuntimeException("Unknown browser variable specified!!!!!!");
		}
		this.browser = browser;
		this.ConfigFile = ConfigFile;
		WebDriver localDriver = null;
		//Open Different Browser
		try {
				switch (selectedBrowser)
				{
				case chrome: {
					ChromeOptions objChromeOptions = new ChromeOptions();
					objChromeOptions.addArguments("--start-maximized");
					System.setProperty(CHROME_DRIVER_KEY, (System.getProperty("user.dir") + "\\Lib\\chromedriver.exe"));
					localDriver = new ChromeDriver(objChromeOptions);
					break;
				}
	
				default: {
				}
			}
		} catch (Throwable exp) {
			Reporter.log("Exception in browser initialization!!! : "
					+ exp.getMessage());
		}

		this.driver = localDriver;
//		ATUReports.setWebDriver(driver);

		objHTMLFunctions= new HTMLFunctions(this.driver);
		htmlFunctions = new HTMLFunctions(this.driver);
		objHTMLFunctions.CreateResultFile(objGLPaths.strHTMLResultsPath);
		htmlFunctions = objHTMLFunctions;
	}

	 public String getSessionId() {
	        return sessionId;
	    }
 
	@Test(enabled = false)
	public ApplicationController PPCApplicaton()
	{
		if(PPCApplicaton ==  null)
		{
			PPCApplicaton = new ApplicationController(driver);
		}
		PPCApplicaton.strParametersNValues = strDTParametersNValues;
		if(PPCApplicaton.strMainParametersNValues == "")
		{
			PPCApplicaton.strMainParametersNValues = this.ParameterNValue;
		}
		return PPCApplicaton;
		
	}


	@Test(enabled = false)
	public static String convertToPreferredFormatForTimeStamp(Long unixtime)
	{
		DateFormat PreferredTimeFormatter2 = new SimpleDateFormat("MMddyyhhmm");
		Date localDate = new java.util.Date(unixtime);
		return "_"+PreferredTimeFormatter2.format(localDate);
	}
	
	//Method to check the OS
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
	 
}