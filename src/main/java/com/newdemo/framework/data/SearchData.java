package com.newdemo.framework.data;

import java.util.HashMap;
import com.newdemo.framework.base.CommonFunctions;
//import com.newdemo.framework.base.ComponentFunctions;

public class SearchData extends CommonFunctions {
	// ====================FIELD VARIABLES DECLARATION=============================
	public String strPlatform = "";
	public String strTitle = "";
	public String strCountry = "";
	public String strWPR = "";
	public String strJDEID = "";
	public String strLanguage = "";
	public String strPO = "";
	public String strAvailID = "";
	public String strDueDate = "";
	public String strStatus = "";
	public String strPosterType = "";
	public String strFileInput = "";
	public String strComments = "";

	public SearchData(String strParametersNValues) throws Exception {
		HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "SearchPages","InputDataRow", "All");
		strPlatform = hmInputDataSet.get("Platform");
		strTitle = hmInputDataSet.get("Title");
		strCountry = hmInputDataSet.get("Country");
		strWPR = hmInputDataSet.get("WPR");
		strJDEID = hmInputDataSet.get("JDEID");
		strLanguage = hmInputDataSet.get("Language");
		strPO = hmInputDataSet.get("PO");
		strAvailID = hmInputDataSet.get("AvailID");
		strDueDate = hmInputDataSet.get("DueDate");
		strStatus = hmInputDataSet.get("Status");
		strPosterType = hmInputDataSet.get("PosterType");
		strFileInput = hmInputDataSet.get("FileInput");
		strComments = hmInputDataSet.get("Comments");
	}

}
