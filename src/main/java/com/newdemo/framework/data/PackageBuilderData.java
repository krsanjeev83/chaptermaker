package com.newdemo.framework.data;

import java.util.HashMap;
import com.newdemo.framework.base.CommonFunctions;
//import com.newdemo.framework.base.ComponentFunctions;

public class PackageBuilderData extends CommonFunctions {
	// ====================FIELD VARIABLES DECLARATION=============================
	public String strTitle = "";
	public String strPO = "";
	public String strMetaDataError = "";
	public String strDueDate = "";
	public String strStatus = "";
	public String strVideoAudioIPFeature = "";
	public String strVideoAudioIPTrailer = "";
	public String strSubtitleIPFeature = "";
	public String strSubtitleIPTrailer = "";
	public String strCaptionIPFeature = "";
	public String strCaptionIPTrailer = "";
	
	

	public PackageBuilderData(String strParametersNValues) throws Exception {
		HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "PackageBuilder","InputDataRow", "All");
		strTitle = hmInputDataSet.get("Title");
		strPO = hmInputDataSet.get("PO");
		strMetaDataError = hmInputDataSet.get("MetaDataError");
		strStatus = hmInputDataSet.get("Status");
		strDueDate = hmInputDataSet.get("DueDate");
		strVideoAudioIPFeature= hmInputDataSet.get("VideoAudioFileInput_Feature");
		strVideoAudioIPTrailer= hmInputDataSet.get("VideoAudioFileInput_Trailer");
		strSubtitleIPFeature= hmInputDataSet.get("SubtitleFileInput_Feature");
		strSubtitleIPTrailer= hmInputDataSet.get("SubtitleFileInput_Trailer");
		strCaptionIPFeature= hmInputDataSet.get("CaptionsFileInput_Feature");
		strCaptionIPTrailer= hmInputDataSet.get("CaptionsFileInput_Trailer");
	}

}
