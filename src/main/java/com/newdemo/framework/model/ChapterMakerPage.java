package com.newdemo.framework.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ChapterMakerPage {

	// STATIC OBJECT 
	
	@FindBy(xpath=".//*[contains(text(),'Title')]//..//*[contains(text(),'EXTERNALDATA')]") public WebElement eltCMTitle1;
	@FindBy(xpath="//*[contains(text(),'Title')]") public WebElement eltCMTitleName;
	public String eltCMTitle = ".//*[contains(text(),'Title')]//..//*[contains(text(),'EXTERNALDATA')]";
	@FindBy(xpath=".//*[contains(text(),'WPR')]//..//*[contains(text(),'EXTERNALDATA')]") public WebElement eltWPR;
	@FindBy(xpath="//*[contains(text(),'WPR')]") public WebElement eltCMWPRName;
	@FindBy(xpath=".//*[contains(text(),'JDE ID')]//..//*[contains(text(),'EXTERNALDATA')]") public WebElement eltJDEID;
	@FindBy(xpath="//*[contains(text(),'JDE (Version) ID')]") public WebElement eltCMJDEIDName;
	@FindBy(xpath=".//*[contains(text(),'Timecode Type')]//..//*[contains(text(),'EXTERNALDATA')]") public WebElement eltTimecode;
	@FindBy(xpath=".//*[@class='form-control selectTimeCodeType']") public WebElement eltTimecodeVal;
	@FindBy(xpath=".//*[contains(text(),'Frame Rate')]//..//*[contains(text(),'EXTERNALDATA')]") public WebElement eltFrameRate;
	@FindBy(xpath=".//*[@class='form-control selectFramerate']") public WebElement eltFrameRateVal;
	@FindBy(xpath=".//*[@id='insertOne']") public WebElement eltSingleInsert;
	@FindBy(xpath=".//*[@id='insertBulk']") public WebElement eltBulkInsert;
	@FindBy(xpath=".//*[@id='validateTimeCodes']") public WebElement eltDuplicateTimecodeCheck;
	@FindBy(xpath=".//*[@data-name='cropInitButton']") public WebElement eltSetCropDefault;
	public String eltOK = ".//*[@class='modal-footer']//..//*[@id='okButton']";
	public String eltOKButton = "//*[@id='okButton']";
	@FindBy(xpath=".//*[@id='insertOne']") public WebElement btnSingleInsert;
	@FindBy(xpath="(.//*[@class='btn btn-default'])[2]") public WebElement btnCloseSingle;
	@FindBy(xpath="(.//*[@class='btn btn-default'])[4]") public WebElement btnCloseBulk;
	@FindBy(xpath="(.//*[@class='btn btn-default'])[1]") public WebElement btnCloseCropValues;
	@FindBy(xpath=".//*[@class='btn btn-primary active']") public WebElement btnCloseCreateCSV;
	
	
	@FindBy(xpath=".//*[@id='insertBulk']") public WebElement btnBulkInsert;
	@FindBy(xpath=".//*[@id='validateTimeCodes']") public WebElement btnDuplicateTimeCode;
	@FindBy(xpath="//*[contains(text(),'Create CSV')]") public WebElement btnCreateCSV;
	@FindBy(xpath="//*[contains(text(),'Set Crop Defaults')]") public WebElement btnSetCropDefaults;
	@FindBy(xpath=".//*[@class='form-control selectTimeCodeType']") public WebElement lstTimeCodeType;
	@FindBy(xpath=".//*[@class='form-control selectFramerate']") public WebElement lstFrameRate;
	
	
	
//	public String eltOK = "//*[contains(text(),'Yes')]";	
	@FindBy(xpath=".//*[@id='approvalBtn']") public WebElement btnAprovedYESNO;
	@FindBy(xpath=".//*[@class='toggle-group']") public WebElement btnAprovedYESNOvalue;
	public String btnAprovedYES1 = ".//*[@class='toggle-group']";
	
	
	@FindBy(xpath=".//*[@class='btn btn-default btn-sm active toggle-off']") public WebElement btnAprovedNO1;
	
	public String btnAprovedYES = ".//*[@class='btn btn-success btn-sm toggle-on']";
	public String btnAprovedNO = ".//*[@class='btn btn-default btn-sm active toggle-off']";
	
	
//	public String btnAprovedYESTest = ".//*[@class='toggle btn btn-default off btn-sm']";
	
	@FindBy(xpath=".//*[@class='toggle-group']//*[contains(text(),'No')]") public WebElement btnAprovedYESTest;
	@FindBy(xpath=".//*[@class='toggle-group']//*[contains(text(),'Yes')]") public WebElement btnAprovedYESNOTest;
	
	@FindBy(xpath=".//*[@id='versionId']") public WebElement eltJDEVersionID;
	
	
	
}
