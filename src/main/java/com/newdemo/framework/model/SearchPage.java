package com.newdemo.framework.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SearchPage {

	// STATIC OBJECT 
	@FindBy(xpath="//*[@id='title']") public WebElement eltTitle;
	@FindBy(xpath="//*[@id='wpr']") public WebElement eltWPR;
	@FindBy(xpath=".//*[@id='versionId']") public WebElement eltJDEVersionID;
//	@FindBy(xpath="//*[@id='ENG']") public WebElement chkENGLanguages;
//	@FindBy(xpath="//*[@id='FR']") public WebElement chkFRLanguages;
//	@FindBy(xpath="//*[@id='LAS']") public WebElement chkLASLanguages;
	@FindBy(xpath=".//*[@id='language-select']") public WebElement lstLanguages;
	
	@FindBy(xpath="//*[@id='country-select']") public WebElement lstCountry;
	@FindBy(xpath="//*[contains(text(),'Look up Title')]") public WebElement btnLookUpTitle;
	
	public String btnLookUpTitleView = "//*[@id='collapse1']/form/button";
	@FindBy(xpath=".//*[text()='Query Foxipedia For a Title']") public String eltQueryFoxipediaTitle;
	public String eltSelectTitle = ".//*[@data-action='selectTitle']/*[text()='EXTERNALDATA']";
	public String eltSelectWPR = ".//*[@data-action='selectTitle']/*[text()='EXTERNALDATA']";
	public String eltSelectVersion = ".//*[@data-action='selectVersion']/*[text()='EXTERNALDATA']";
	
	@FindBy(xpath=".//*[@class='container-fluid']/*[text()='Select a Version']") public String btnSelectVersion;
	@FindBy(xpath=".//*[@data-action='selectVersion']/*[text()='Original']") public WebElement btnSelectOriginalTitle;
	@FindBy(xpath=".//*[contains(text(),'Please Verify Version Metadata is Complete')]") public WebElement btnReviewSynopsisTitle;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnReviewSynopsis;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnUpdateAll;
	@FindBy(xpath=".//*[contains(text(),'Version')]") public WebElement btnReviewGenreTitle;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnReviewGenre;
	@FindBy(xpath=".//*[contains(text(),'Title Genre')]") public WebElement btnReviewRatingTitle;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnReviewRating;
	@FindBy(xpath=".//*[contains(text(),'Version Rating')]") public WebElement btnReviewTalentTitle;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnReviewTalent;
	@FindBy(xpath=".//*[contains(text(),'Title Talent')]") public WebElement btnTitleTalent;
	@FindBy(xpath=".//*[@class='btn']") public WebElement btnContinue;
	@FindBy(xpath=".//*[@class = 'control-label']") public WebElement btnPlatformTitle;
	@FindBy(xpath="//*[@id='clear-button']") public WebElement btnClearSearch;
	
	
	@FindBy(xpath=".//*[@id='upload-poster-art']") public WebElement btnUpdate;
	@FindBy(xpath=".//*[@class='btn btn-primary']") public String btnUpdatePackage;
	@FindBy(xpath="//button[contains(text(),'Update Package')]") public WebElement UpdatePackage;
	public String UpdatePackage1 = "//button[contains(text(),'Update Package')]";
//	@FindBy(xpath=".//*[@class='col-md-12 col-xs-12']") public WebElement UpdatePackage;
	
	//*[@id="afUpdatePackage"]/div[2]/div
	public String eltKeyArtVerify = "//*[contains(text(),'Key Art')]";
	public String eltSpin = ".//*[@id='spin']/div";
	public String eltPackageUpdateSuccess = "//*[@id='toast-container']/div/div[1]";

	//Update Meidia Package Page
	@FindBy(xpath=".//*[@id='updateModal']//*[contains(text(),'Update Media Package')]") public WebElement eltUpdateMPtitle;
	@FindBy(xpath=".//*[@id='platformId']") public WebElement lstPlatform;
	@FindBy(xpath="//*[@name='title']") public WebElement eltMPTitle;
	@FindBy(xpath="//*[@name='wpr']") public WebElement eltWPRID;
	@FindBy(xpath="//*[@name='jdeId']") public WebElement eltJDEID;
	@FindBy(xpath="//*[@name='language']") public WebElement eltLanguage;
	@FindBy(xpath="//*[@name='po']") public WebElement eltPO;
	@FindBy(xpath="//*[@name='avail.availId']") public WebElement eltAvailID;
	@FindBy(xpath="//*[@data-schema-key='dueDate']") public String eltDueDate;
	@FindBy(xpath="//*[@data-schema-key='dueDate']") public WebElement eltDueDate1;
	@FindBy(xpath="//*[@id='EeSyTCri8abCjDS3Z']") public WebElement eltDueDate2;
	
	
	@FindBy(xpath="//*[@name='dueDate']") public WebElement eltDueDateVerify;
	@FindBy(xpath="//*[@name='status']") public WebElement lstStatus;
	@FindBy(xpath="//*[@name='comments']") public WebElement eltComments;
	
	
//	[contains(text(),'Please Verify Version Metadata is Complete ')]
	
	///Upload Files
	@FindBy(xpath=".//*[@id='select-poster-type']") public WebElement lstPosterType;
	public String UpdatePackages = ".//*[@class='btn btn-primary']";
	public String lstPosterTypes = ".//*[@id='select-poster-type']";
	@FindBy(xpath=".//*[@id='poster-art-file']") public WebElement btnBrowse;
	@FindBy(xpath=".//*[@id='clear-button']") public WebElement btnClearSearchFields;
	@FindBy(xpath=".//a[contains(text(),'Media Packages')]") public WebElement eltMediaPackages;
	@FindBy(xpath=".//*[@data-source='po']") public WebElement eltMPPO;
	public String eltPOStatus = "//*[contains(text(),'EXTERNALDATA')]//..//*[@id='select']";
	@FindBy(xpath=".//*[@class='panel-title']") public WebElement eltSlectMediaFiles;
	
	
	public String eltXMLTagAdded = ".//*[@class='sa']";
	@FindBy(xpath=".//*[@class='sa']") public WebElement eltXMLTagAdded1;
	
	public String eltQManagerTransfer = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'QMANAGER_TRANSFER_COMPLETED')]";
	public String eltAsperaCompleted = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'ASPERA_COMPLETED')]";
	
	//MetaData Errors
	public String eltMetaData_EIDR = ".//*[@data-meta='eidr']";
	public String eltMetaData_Synopsis = ".//*[@data-meta='synopsis']";
	public String eltMetaData_Genre = ".//*[@data-meta='genre']";
	public String eltMetaData_Rating = ".//*[@data-meta='rating']";
	public String eltMetaData_Talent = ".//*[@data-meta='talent']";
	public String eltMetaData_Copyright = ".//*[@data-meta='copyright']";
	
	public String eltMetaData_EIDR_Msg = "//*[contains(text(),'EIDR crossref No records found')]";
	public String eltMetaData_Synopsis_Msg = "//*[contains(text(),'synopsis No records found')]";
	public String eltMetaData_Genre_Msg = "//*[contains(text(),'genre No records found')]";
	public String eltMetaData_Rating_Msg = "//*[contains(text(),'rating No records found')]";
	public String eltMetaData_Talent_Msg= "//*[contains(text(),'talent No records found')]";
	
	public String _eltQManagerComplete = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'QMANAGER_TRANSFER_COMPLETED')]";
	
	
	//############CAPTER MARK XPATH##########################################################################################################
	@FindBy(xpath=".//*[@id='bs-example-navbar-collapse-1']//*[contains(text(),'Search')]") public WebElement eltSearch;
	@FindBy(xpath=".//*[contains(text(),'Create Chapter Marks')]") public WebElement eltCreateChapterMarks;
	
	public String eltChapterMarkNotApprovedMsg = "//*[@class='toast-message']";
	public String eltChapterMarkApprovedMsg = "//*[@class='toast toast-success']";
//	@FindBy(xpath="//*[@class='toast-message']") public WebElement eltChapterMarkNotApprovedMsg;
//	@FindBy(xpath="//*[@class='toast toast-success']") public WebElement eltChapterMarkApprovedMsg;
}
