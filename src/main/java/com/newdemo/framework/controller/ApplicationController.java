package com.newdemo.framework.controller;

import org.openqa.selenium.WebDriver;
import com.newdemo.framework.data.LoginData;  // Importing LonginData 
import com.newdemo.framework.data.SearchData;  // Importing SearchData
import com.newdemo.framework.data.PackageBuilderData;  // Importing PackageBuilderData 
import com.newdemo.framework.data.ChapterMarksData;  // Importing PackageBuilderData 

public class ApplicationController {
	WebDriver driver;
	
	public String strMainParametersNValues = "";

	//CONTROLLER OBJECTS DECLARATION
	public LoginController login = null;
	public SearchController search = null;  
	public PackageBuilderController packagebuilder = null;
	public ChapterMarksController chaptermarks = null;
	
	public String strParametersNValues = "";
	public ApplicationController(WebDriver driver) {
		this.driver = driver;
	}
	
	//
	public LoginController LoginPage() throws Exception {
		if (login == null) {
			login = new LoginController(driver);
		}
			login.loginData = new LoginData(this.strParametersNValues);
			return login;
	}

	public SearchController SearchPage() throws Exception {
		if (search == null) {
			search = new SearchController(driver);
		}
			search.searchData = new SearchData(this.strParametersNValues);
			return search;
	}
	
	public PackageBuilderController PackageBuilderPage() throws Exception {
		if (packagebuilder == null) {
			packagebuilder = new PackageBuilderController(driver);
		}
			packagebuilder.packageBuilderData = new PackageBuilderData(this.strParametersNValues);
			return packagebuilder;
	}
	
	public ChapterMarksController ChapterMarksPage() throws Exception {
		if (chaptermarks == null) {
			chaptermarks = new ChapterMarksController(driver);
		}
		chaptermarks.chapterMarksData = new ChapterMarksData(this.strParametersNValues);
			return chaptermarks;
	}
}
