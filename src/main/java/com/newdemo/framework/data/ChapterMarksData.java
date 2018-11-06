package com.newdemo.framework.data;

import java.util.HashMap;
import com.newdemo.framework.base.CommonFunctions;
//import com.newdemo.framework.base.ComponentFunctions;

public class ChapterMarksData extends CommonFunctions {
	// ====================FIELD VARIABLES DECLARATION=============================
	public String strTitle = "";
	public String strWPR = "";
	public String strJDEID = "";
	public String strTimeCodeType = "";
	public String strFrameRate = "";
	public String strThumbnail = "";
	public String strCaption = "";
	public String strDescription = "";
	public String strChapterTimecode = "";
	public String strThumbnailTimeCode = "";
	public String strNotes = "";
	
	
	

	public ChapterMarksData(String strParametersNValues) throws Exception {
		HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "ChapterMaker","InputDataRow", "All");
		strTitle = hmInputDataSet.get("Title");
		strWPR = hmInputDataSet.get("WPR");
		strJDEID = hmInputDataSet.get("JDEID");
		strTimeCodeType = hmInputDataSet.get("TimeCodeType");
		strFrameRate = hmInputDataSet.get("FrameRate");
		strThumbnail = hmInputDataSet.get("Thumbnail");
		strCaption = hmInputDataSet.get("Caption");
		strDescription = hmInputDataSet.get("Description");
		strChapterTimecode = hmInputDataSet.get("ChapterTimecode");
		strThumbnailTimeCode = hmInputDataSet.get("ThumbnailTimecode");
		strNotes = hmInputDataSet.get("Notes");
	}

}
