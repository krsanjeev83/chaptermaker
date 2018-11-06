package com.newdemo.framework.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class PackageBuilderPage {

	// STATIC OBJECT 
	
	@FindBy(xpath=".//*[@data-source='title']") public WebElement eltMPTitle;
	
	@FindBy(xpath="//*[contains(text(),'EXTERNALDATA')]//..//*[@id='select']") public WebElement eltStatusSelect;
	
		
	//Video
	@FindBy(xpath=".//*[@id='addVideoDropdown']") public WebElement eltAddVideoDropDown;
	public String eltAddVideoDropDown1 = ".//*[@id='addVideoDropdown']";
	@FindBy(xpath=".//*[@class='addVideoInput']/a[contains(text(),'Feature')]") public WebElement eltVideoFeature;
	@FindBy(xpath=".//*[@class='addVideoInput']/a[contains(text(),'Trailer')]") public WebElement eltVideoTrailer;
	public String eltVideoTrailer1 = ".//*[@class='addVideoInput']/a[contains(text(),'Trailer')]";
	
	@FindBy(xpath=".//*[@id='inputFile_video-0']") public WebElement eltEditVideoFeature;
	@FindBy(xpath=".//*[@id='video-lang-0']") public WebElement lstLanguageVideoFeature;
	
	@FindBy(xpath=".//*[@id='inputFile_video-1']") public WebElement eltEditVideoTrailer;
	public String eltEditVideoTrailer1 = ".//*[@id='inputFile_video-1']";
	@FindBy(xpath=".//*[@id='video-lang-1']") public WebElement lstLanguageVideoTrailer;
	
	
	//complete Path
	@FindBy(xpath=".//*[@class = 'btn btn-secondary btn-browse-file']") public WebElement btnChooseFile;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'Store2')]") public WebElement eltStore2;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'Working')]") public WebElement eltWorking;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'Sundar')]") public WebElement eltSundarfolder;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'MediaPackage')]") public WebElement eltMPfolder;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'FaultInOurStarsThe')]") public WebElement eltTitleFolder_FaultInOurstars;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'resources')]") public WebElement eltTitleFolder_Resources;
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'FaultinOurStarsThe_031571_09152016')]") public WebElement eltTitleFaultInOurStart;
	public String eltTitleFolder_FaultInOurstarManifest = "//*[@id='j1_100_anchor']";
	public String eltFeatureManifestFeatureFile = ".//*[contains(text(),'FaultInOurStarsThe_031571_ORIG_V301125_INT_C_EST_HD50TS1080p_23_ENG_178_ENG-51ST_RT000500_Microsoft_Test_File_8ch.mpg')]";
	@FindBy(xpath=".//*[@class='btn btn-primary btn-select-file']") public WebElement btnSelect1;
	
	//*[@id="fileBrowserModal"]/div/div/div[3]/button[2]
	
	public String btnSelect = ".//*[@class='btn btn-primary btn-select-file']";
	
	//Gulliver Travels
	@FindBy(xpath=".//*[@class='jstree-icon jstree-themeicon']//..//..//*[contains(text(),'94C7-68E2-9333-C6F8-153F-2_20170501_110948')]") public WebElement eltTitleFolder_GulliverTravels;
	public String eltFeatureMovFile_GulliverTravels = ".//*[contains(text(),'GulliversTravels_030957_ORIG_V59869_INT_C_EST_PROHQ1080p_23_ENG_235_ENG-51ST_RT012438.mov')]";
	public String eltTrailverMovFile_GulliverTravels = ".//*[contains(text(),'GulliversTravels_030957_NSVR_V59868_VAM_T_EST_PROHQ1080p_23_ENG_235_ENG-51ST_RT000220_CleanTrailer.mov')]";
	
	
	
	//Audio
	public String eltAddAudioDropDown = ".//*[@id='addAudioDropdown']";
	public String eltAudioFeature = ".//*[@class='addAudioInput']/a[contains(text(),'Feature')]";
	public String eltAudioTrailer = ".//*[@class='addAudioInput']/a[contains(text(),'Trailer')]";
	
	@FindBy(xpath=".//*[@id='inputFile_audio-3']") public WebElement eltEditAudioFeature;
	public String eltEditAudioFeature1 = ".//*[@id='inputFile_audio-3']";
	@FindBy(xpath=".//*[@id='audio-lang-3']") public WebElement lstLanguageAudioFeature;
	@FindBy(xpath=".//*[@id='audio-config-3']") public WebElement lstConfigAudioFeature;
	
	
	@FindBy(xpath=".//*[@id='inputFile_audio-1']") public WebElement eltEditAudioTrailer;
	public String eltEditAudioTrailer1 = ".//*[@id='inputFile_audio-4']";
	@FindBy(xpath=".//*[@id='audio-lang-4']") public WebElement lstLanguageAudioTrailer;
	@FindBy(xpath=".//*[@id='audio-config-4']") public WebElement lstConfigAudioTrailer;
	
	//Subtitle
	@FindBy(xpath=".//*[@id='addSubtitleInput']") public WebElement eltAddSubtitleInputDropDown;
	@FindBy(xpath=".//*[@class='addSubtitleInput']/a[contains(text(),'Feature')]") public WebElement eltAddSubtitleInputFeature;
	@FindBy(xpath=".//*[@class='addSubtitleInput']/a[contains(text(),'Trailer')]") public WebElement eltAddSubtitleInputTrailer;
	
	//Captions
	@FindBy(xpath=".//*[@id='addCaptionsInput']") public WebElement eltAddaddCaptionsInputDropDown;
	@FindBy(xpath=".//*[@class='addCaptionsInput']/a[contains(text(),'Feature')]") public WebElement eltAddCaptionsInputFeature;
	@FindBy(xpath=".//*[@class='addCaptionsInput']/a[contains(text(),'Trailer')]") public WebElement eltAddCaptionsInputTrailer;

	@FindBy(xpath=".//*[@id='downloadManifest']") public WebElement eltDownloadManifest;
	public String eltDownloadManifest1 = ".//*[@id='downloadManifest']";
	@FindBy(xpath=".//*[@id='downloadMetadata']") public WebElement eltDownloadMetadata;
	@FindBy(xpath=".//*[@id='submitDelivery']") public WebElement eltSubmitDelivery;
	public String eltSubmitDelivery1 = ".//*[@id='submitDelivery']";
	
	@FindBy(xpath=".//*[@id='addSubtitleDropdown']") public WebElement eltAddSubtitleDropDown;
	@FindBy(xpath=".//*[@id='addCaptionsDropdown']") public WebElement eltAddCaptionsDropDown;
	
	public String eltSelectTitle = ".//*[@data-action='selectTitle']/*[text()='EXTERNALDATA']";
	public String eltPOStatus = "//*[contains(text(),'EXTERNALDATA')]//..//*[@id='select']";
	
	public String eltQManagerTransfer = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'QMANAGER_TRANSFER_COMPLETED')]";
	@FindBy(xpath="//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'QMANAGER_TRANSFER_COMPLETED')]") public WebElement eltQManagerTransfer1;
	public String _eltQManagerComplete = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'QMANAGER_TRANSFER_COMPLETED')]";
	public String eltAsperaCompleted = "//*[contains(text(),'EXTERNALDATA')]//..//*[contains(text(),'ASPERA_COMPLETED')]";
	
}
