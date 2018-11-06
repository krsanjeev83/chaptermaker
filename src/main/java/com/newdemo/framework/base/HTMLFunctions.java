package com.newdemo.framework.base;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class HTMLFunctions 
{
	CommonFunctions objCMNFunctions = new CommonFunctions();
	static String strCurrentExecutionFolderPath;
	static String strResultsFilePath;
	static String strModuleResultsFilePath;
	static String strResultsFolder; 
	static String strOLDModuleName;
	static String strModuleStartDate;
	static String strStepResultsFileName;
	static String strModulePageLoadingTimings;
	static Date dtTestSuiteStartTime;
	static Date dtPrevStepExecTime;
	static Date dtModuleStartDate;
	static int intModuleCount=0;
	static Boolean blnModuleStatus;
	
	WebDriver objWebDriver = null;
	
	//===================================HTML REUSABLE FUNCTIONS CONSTRUCTOR==========================================
	public HTMLFunctions(WebDriver objTempWebDriver)
	{
		objWebDriver = objTempWebDriver;
	}
	
	@SuppressWarnings("static-access")
	public boolean CreateResultFile(String strResultsFolderPath) throws Exception
	{
		//=======================CREATING A HTML REPORT FILE==================
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		String strAppendDateTime = objSimpleDtFormat.format(new Date());
		strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
		String arrDateTime[] = strAppendDateTime.split(" ");
		
		this.strResultsFolder = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_");
		File objFile = new File(strResultsFolderPath + "/" + arrDateTime[0]);
		if (!objFile.exists()) 
		{
			if (objFile.mkdir()) 
			{
				System.out.println("Directory is created!");
			} else 
			{
				System.out.println("Failed to create directory!");
			}
		}
		
		strResultsFolderPath = strResultsFolderPath + "/" + arrDateTime[0];
		this.strCurrentExecutionFolderPath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_");
		
		File objFile1 = new File(strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_"));
		if (!objFile1.exists()) 
		{
			if (objFile1.mkdir()) 
			{
				System.out.println("Directory is created!");
			} else 
			{
				System.out.println("Failed to create directory!");
			}
		}
		
		File objScreenshotFile = new File(strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/Screenshots");
		if (!objScreenshotFile.exists()) 
		{
			if (objScreenshotFile.mkdir()) 
			{
				System.out.println("Screenshots directory is created!");
			} else 
			{
				System.out.println("Failed to create Screenshots directory!");
			}
		}			
		
		String strResultsFilePath, strModuleResultsFilePath;
		strResultsFilePath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/StepRunResult " + strAppendDateTime + ".html";
		this.strStepResultsFileName = "StepRunResult " + strAppendDateTime + ".html";
		strModuleResultsFilePath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/ModuleRunResult " + strAppendDateTime + ".html";
		
		PrintWriter objWriterModules = new PrintWriter(strModuleResultsFilePath, "UTF-8");
		PrintWriter objWriter = new PrintWriter(strResultsFilePath, "UTF-8");
		
		objWriter.println("<html>");
		objWriter.println("<title>" + GlobalPaths.strGLEnvironment + " AUTOMATION STEPWISE RESULT " + strAppendDateTime + "</title>");
		objWriter.println("<body bgcolor = \"WHITE\">");
		objWriter.println("<h3 align = \"CENTER\" style = \"FONT-SIZE:20;COLOR:DARKBLUE\"> " + GlobalPaths.strGLEnvironment + " AUTOMATION SCRIPT EXECUTION: STEPWISE RESULT</h3>");
		objWriter.println("<table border = \"1\" width = \"100%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"BORDER-COLOR:BLACK\">");
		objWriter.println("<tr bgcolor = \"BLACK\">");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"13%\" >MODULE NAME</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"30%\" >TEST CONDITION</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"20%\" >EXPECTED RESULT</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"20%\" >ACTUAL RESULT</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"7%\" >STATUS</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"10%\" >TIME TAKEN</th>");
		objWriter.println("</tr>");	
		objWriter.close();		
		
		objWriterModules.println("<html>");
		objWriterModules.println("<title>" + GlobalPaths.strGLEnvironment + " AUTOMATION MODULEWISE RESULT " + strAppendDateTime + "</title>");
		objWriterModules.println("<head>");
		objWriterModules.println("<script>");
		objWriterModules.println("function displayHideElement(strButton,strID)");
		objWriterModules.println("{");
		objWriterModules.println("strDispText = (document.getElementById(strButton).innerText).trim();");
		objWriterModules.println("if(strDispText == \"+\")");
		objWriterModules.println("{");
		objWriterModules.println("document.getElementById(strID).style.display=\"block\";");
		objWriterModules.println("document.getElementById(strButton).innerText=\" - \";");
		objWriterModules.println("}");
		objWriterModules.println("else");
		objWriterModules.println("{");
		objWriterModules.println("document.getElementById(strID).style.display=\"none\";");
		objWriterModules.println("document.getElementById(strButton).innerText=\" + \";");
		objWriterModules.println("}");
		objWriterModules.println("}");
		objWriterModules.println("</script>");
		objWriterModules.println("</head>");
		objWriterModules.println("<body bgcolor = \"WHITE\">");
		objWriterModules.println("<br><br>");
		objWriterModules.println("<h3 align = \"CENTER\" style = \"FONT-SIZE:20;COLOR:DARKBLUE\">" + GlobalPaths.strGLEnvironment+ " : SMOKE TEST AUTOMATION EXECUTION RESULT</h3>");
		objWriterModules.println("<center><table border = \"1\" width = \"75%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"BORDER-COLOR:BLACK\">");
		objWriterModules.println("<tr bgcolor = \"#787878\">");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"5%\" > </th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"30%\" >MODULE NAME</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"35%\" >MODULE DESCRIPTION</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"12%\" >STATUS</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"18%\" >TIME TAKEN</th>");
		objWriterModules.println("</tr>");	
		objWriterModules.close();
		
		this.strResultsFilePath = strResultsFilePath;
		this.strModuleResultsFilePath = strModuleResultsFilePath;
		
		this.dtPrevStepExecTime = new Date();
		this.dtTestSuiteStartTime = new Date();
		return true;
	}

	public String TakeScreenshot() throws Exception
	{
		String strHTMLResultFolder, strAppendDateTimeUpdate, strScreenshotPath = "";
		
		strAppendDateTimeUpdate = objCMNFunctions.GetDateTimeStringWithSeconds();
		String strResultsFolder = this.strCurrentExecutionFolderPath;
		
		boolean blnAleartExists = false;
	    WebDriverWait objWebDriverWait = new WebDriverWait(objWebDriver, 2);
	    try
	    {
	    	Alert objAlertExists = objWebDriverWait.until(ExpectedConditions.alertIsPresent());
	    	blnAleartExists = true;
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    if(blnAleartExists == true)
	    {
	    	Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	    	BufferedImage capture = new Robot().createScreenCapture(screenRect);
	    	ImageIO.write(capture, "bmp", new File(strResultsFolder + "\\Screenshots\\" + strAppendDateTimeUpdate + ".png"));
	    }
	    else
	    {
	        try
	        {
	            File objScreenshot = ((TakesScreenshot)objWebDriver).
	                                getScreenshotAs(OutputType.FILE);        	
	        	FileUtils.copyFile(objScreenshot, new File(strResultsFolder + "\\Screenshots\\" + 
	        			strAppendDateTimeUpdate + ".png"));
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Exception occured while taking screenshot: " + e);
	        }
	    }
	        		
		return "Screenshots\\" + strAppendDateTimeUpdate + ".png";
	}
	
	@SuppressWarnings("static-access")
	public boolean ReportPassFail(String strStepCondition, String strExpectedResult, String strActualResult) throws Exception
	{
		synchronized (HTMLFunctions.class)
		{	
			SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date objDate = new Date();
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(objDate);
			String strAppendDateTime = objSimpleDtFormat.format(new Date());
			strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
			
			PrintWriter objWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.strResultsFilePath, true)));
			PrintWriter objWriterModule = new PrintWriter(new BufferedWriter(new FileWriter(this.strModuleResultsFilePath, true)));
			
			Date dtCurrDtTime = new Date();
			String strTimeDifferenceMsg = this.f_HTML_FindTimeDifferences(dtCurrDtTime, this.dtPrevStepExecTime);
			//long intTimeDifference = dtCurrDtTime.getTime() - this.dtPrevStepExecTime.getTime();
		
			this.dtPrevStepExecTime = new Date();
			
			objWriter.println("<tr align = \"CENTER\">");
			
			if(strExpectedResult.equalsIgnoreCase("ModuleName") && strActualResult.equalsIgnoreCase("ModuleName"))
			{	
				if(this.strOLDModuleName != null && this.strOLDModuleName!="")
				{
					this.ModuleStatusUpdate(false);
				}
				this.strModulePageLoadingTimings = "";
				this.dtModuleStartDate = new Date();
				
				this.strOLDModuleName = strStepCondition;
				objWriterModule.println("<tr align = \"CENTER\">");
				
				this.strModuleStartDate = objSimpleDtFormat.format(new Date());
				
				this.intModuleCount = this.intModuleCount + 1;
				
				objWriterModule.println("<td id=\"ExpandModule" + this.intModuleCount + "\" bgcolor = \"#A0A0A0\" " +
						"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" onClick=\"displayHideElement('ExpandModule" + this.intModuleCount + "','Module"+ this.intModuleCount + "')\"> + </td>");
				
				ATUReports.add("<font size='5'><b style=\"color:white;background-color:green\">" + strStepCondition.replace("=>", "<br>") + "</b></font>",LogAs.INFO, null);
				if(strStepCondition.contains("=>"))
				{
					String[] arrModuleNameNDesc = strStepCondition.split("=>");
					
					objWriterModule.println("<td bgcolor = \"Tan\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
							"<a href=\"" + this.strStepResultsFileName + "#Module" + this.intModuleCount + "\" target=\"_blank\">" + arrModuleNameNDesc[0] + "</a><br>STARTED AT: " + this.strModuleStartDate + "</td>");	
					
					objWriterModule.println("<td bgcolor = \"PeachPuff\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
										arrModuleNameNDesc[1] + "</td>");
					
					objWriter.println("<td bgcolor = \"DARKGREEN\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" >" + 
							"<a id=\"Module" + this.intModuleCount + "\" >" + arrModuleNameNDesc[0] + "</a><br>STARTED AT: " + this.strModuleStartDate + "</td>");
				}
				else
				{
					objWriterModule.println("<td bgcolor = \"Tan\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
							"<a href=\"" + this.strStepResultsFileName + "#Module" + this.intModuleCount + "\" target=\"_blank\">" + strStepCondition + "</a><br>STARTED AT: " + this.strModuleStartDate + "</td>");	
					
					objWriterModule.println("<td bgcolor = \"PeachPuff\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" > </td>");
					
					objWriter.println("<td bgcolor = \"DARKGREEN\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" >" + 
							"<a id=\"Module" + this.intModuleCount + "\" >" + strStepCondition + "</a><br>STARTED AT: " + this.strModuleStartDate + "</td>");
				}
				
				this.blnModuleStatus = true;
				System.out.println("*****************************" + strStepCondition + "********************************");
			}
			else
			{
				objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >,,</td>");
			}
			
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strStepCondition + "</td>");
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strExpectedResult + "</td>");
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strActualResult + "</td>");
			
			if(strExpectedResult.equalsIgnoreCase("PerformanceCapture") && strActualResult.equalsIgnoreCase("PerformanceCapture"))
			{
				String strScreenshot = this.TakeScreenshot();
				if(this.strModulePageLoadingTimings == "")
				{
					this.strModulePageLoadingTimings = strStepCondition + "--" + strScreenshot;
				}
				else
				{
					this.strModulePageLoadingTimings = this.strModulePageLoadingTimings + "##" + strStepCondition + "--" + strScreenshot;
				}
			}
			
			if(!strExpectedResult.equalsIgnoreCase("ModuleName"))
			{
				System.out.println(strStepCondition.replaceAll("(<b>)|(<B>)|(<i>)|(</B>)|(</i>)|(<i title=)", "") + "\t\"" + strExpectedResult + "\"\t\"" + strActualResult + "\"");
			}
			
			if(strActualResult.equalsIgnoreCase(strExpectedResult))
			{
				if(strExpectedResult.equalsIgnoreCase("ModuleName") && strActualResult.equalsIgnoreCase("ModuleName"))
				{
					objWriter.println("<td bgcolor = \"DARKGRAY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>STARTED</b></td>");
					objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" > </td>");
				}	
				else
				{										//#88D440
					objWriter.println("<td bgcolor = \"DARKGREY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>PASS</b></td>");
					objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
				}
				if(this.blnModuleStatus != false)
						this.blnModuleStatus = true;
			}
			else
			{
				String strScreenshot = this.TakeScreenshot();
				
				objWriter.println("<td bgcolor = \"RED\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + 
						"<b><a target = '_blank' href='" + strScreenshot + "'>FAIL</a></b></td>");
				objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
				
				this.blnModuleStatus = false;
				
			}
			objWriter.println("</tr>");
			
			objWriter.close();
			objWriterModule.close();
			return true;
		}
	}
	
	//===============================REPORT PASS FAIL STATUS TO ATU===========================
	public void reportPassFailToATU(String strStepCondition, String strExpected, String strActual) throws Exception
	{
		ReportPassFail(strStepCondition, strExpected, strActual);
		if(strExpected.equalsIgnoreCase("true") || strExpected.equalsIgnoreCase("false"))
		{
			if(strExpected.equalsIgnoreCase(strActual))
				ATUReports.add(strStepCondition, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add(strStepCondition, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else
		{
			if(strExpected.equalsIgnoreCase(strActual))
				ATUReports.add(strStepCondition, strExpected, strActual, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add(strStepCondition, strExpected, strActual, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void ModuleStatusUpdate(Boolean blnLastModule) throws Exception
	{
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		
		Date dtCurrDtTime = new Date();
		String strTimeDifferenceMsg = this.f_HTML_FindTimeDifferences(dtCurrDtTime, this.dtModuleStartDate);
		
		PrintWriter objWriterModule = new PrintWriter(new BufferedWriter(new FileWriter(this.strModuleResultsFilePath, true)));
				
		if(this.blnModuleStatus==true)
		{			
			objWriterModule.println("<td bgcolor = \"#88D440\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >PASS</td>");
			
		}
		else
		{		
			objWriterModule.println("<td bgcolor = \"RED\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + 
					"FAIL</td>");
		}
		objWriterModule.println("<td bgcolor = \"SLATEGRAY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
		objWriterModule.println("</tr>");
		
		if(this.strModulePageLoadingTimings != "")
		{
			objWriterModule.println("<tr><td colspan=5 style=\"padding: 0px;\">");
			String[] arrModulePageLoadingTimings = (this.strModulePageLoadingTimings).split("##");
			objWriterModule.println("<center><table id=\"Module" + this.intModuleCount + "\" width = \"100%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"DISPLAY:NONE;BORDER-COLOR:BLACK\">");
			for(String strCurrPageLoadingTiming: arrModulePageLoadingTimings)
			{
				String[] arrCurrPageLoadingNScreenshot = strCurrPageLoadingTiming.split("--");
				String[] arrCurrPageLoadingTiming = arrCurrPageLoadingNScreenshot[0].split("=>");
				objWriterModule.println("<tr align = \"CENTER\">");
				objWriterModule.println("<td " +
						"style = \"BORDER:NONE;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"5%\"> </td>");
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" " +
						"style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"30%\">" + arrCurrPageLoadingTiming[0] + "</td>");	
				
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" width = \"35%\"><b>" + arrCurrPageLoadingTiming[1] + "</b></td>");				
				
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"12%\">" + 
						"<b><a target = '_blank' href='" + arrCurrPageLoadingNScreenshot[1] + "'>SCREENSHOT</a></b></td>");
				
				objWriterModule.println("<td " +
						"style = \"BORDER:NONE;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"18%\"> </td>");
				
				objWriterModule.println("</tr>");
			}
			objWriterModule.println("</table>");
			objWriterModule.println("</td>");
			objWriterModule.println("</tr>");			
		}
		
		//============================OVERALL SUITE TIMELINE==============================
		if(blnLastModule==true)
		{
			String strTotalSuiteTime = "TOTAL SUITE TIME => " + this.f_HTML_FindTimeDifferences(dtCurrDtTime, this.dtTestSuiteStartTime);
			objWriterModule.println("<tr align = \"CENTER\">");
			objWriterModule.println("<td colspan=5 bgcolor = \"#A0A0A0\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTotalSuiteTime + "</td>");
			objWriterModule.println("</tr>");
			objWriterModule.println("</table>");
		}
		
		objWriterModule.close();
	}
	
	public String f_HTML_FindTimeDifferences(Date dtLatestDate, Date dtOldDate) throws Exception
	{
		String strTimeTakenMsg="";
		
		double dblMilliSeconds = (dtLatestDate.getTime() - dtOldDate.getTime());
		double dblSeconds = (dtLatestDate.getTime() - dtOldDate.getTime())/1000;
		int intMinutes=0, intHour=0;
		float intSeconds=0;
		
		if(dblSeconds >= 60)
		{
			intMinutes = (int) (dblSeconds/60);
			intSeconds = (float) (dblMilliSeconds % (60*1000));
			intSeconds = intSeconds;
			
			if(intMinutes>=60)
			{
				intHour = intMinutes/60;
				intMinutes = intMinutes % 60;
			}
			
		}
		else
		{
			intSeconds = (float) (dblMilliSeconds % (60*1000));
		}
		intSeconds = objCMNFunctions.RoundFloatNumber((float)(intSeconds/1000), 3);
		
		if(intHour == 0 && intMinutes == 0)
		{
			strTimeTakenMsg = Math.round(intSeconds) + " seconds";
			
		}
		else
		{
			if(intHour == 0)
			{
				strTimeTakenMsg = intMinutes + " minutes " + Math.round(intSeconds) + " seconds";
				System.out.println("Seconds=>"+Math.round(intSeconds));
			}
			else
			{
				strTimeTakenMsg = intHour + " hours " + intMinutes + " minutes " + Math.round(intSeconds) + " seconds";
				System.out.println("Seconds=>"+Math.round(intSeconds));
			}
			
		}

		
		return strTimeTakenMsg;
	}

	public static void main(String[] args) throws Exception
	{
	}


}
