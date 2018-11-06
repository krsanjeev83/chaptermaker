package com.newdemo.framework.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class GlobalPaths
{
	public static HashMap hmRuntimeGlobalVariable = new HashMap();
	//=========================OBJECT REPOSITORY PATHS DEFINITION==============================
	public static String strBrowserType = "";
	public static String strGLEnvironment = "";
	public static int intHeaderWidth = 0;
	public static String strXLInputDatasheetPath=""; //DATASHEET PATHS DEFINITION
	public String strXLDriverPath = "";				//CHROME DRIVER PATH
	public String strHTMLResultsPath = ""; 			//RESULT PATH
	//==============================PROPERTIES OBJECT==========================================
	static Properties objProperties = new Properties();
	public int intChromeMenubarHeight = 100;

	//=========================CONSTRUCTOR FOR GLOBAL PATHS====================================
	public GlobalPaths() throws FileNotFoundException, IOException
	{
		intHeaderWidth = Integer.parseInt(objProperties.getProperty("HeaderWidth"));
		objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
		//=====================OBJECT REPOSITORY INITIALIZATION================================
		intChromeMenubarHeight = Integer.parseInt(objProperties.getProperty("ChromeMenubarHeight"));
		strGLEnvironment = objProperties.getProperty("Environment");
		strHTMLResultsPath = objProperties.getProperty("HTMLResultsPath");
	}

	public GlobalPaths(String inputsheet) throws FileNotFoundException, IOException
	{
		objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
		//=====================OBJECT REPOSITORY INITIALIZATION================================
		this.strXLInputDatasheetPath = inputsheet;
		intChromeMenubarHeight = Integer.parseInt(objProperties.getProperty("ChromeMenubarHeight"));
		strGLEnvironment = objProperties.getProperty("Environment");
		strHTMLResultsPath = objProperties.getProperty("HTMLResultsPath");
	}
	public static void main(String[] args)
	{

	}

}
