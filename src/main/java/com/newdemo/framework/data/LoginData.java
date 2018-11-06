package com.newdemo.framework.data;

import java.util.HashMap;
import com.newdemo.framework.base.CommonFunctions;
//import com.newdemo.framework.base.ComponentFunctions;

public class LoginData extends CommonFunctions {
	// ====================FIELD VARIABLES DECLARATION=============================
	public String strDTURL = "";
	public String strDTUserName = "";
	public String strDTPassword = "";
	public String strUIDTimeStamp = "";

	public LoginData(String strParametersNValues) throws Exception {
		HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "Login","InputDataRow", "All");
		strDTURL = hmInputDataSet.get("URL");
		strDTUserName = hmInputDataSet.get("UserName");
		strDTPassword = hmInputDataSet.get("Password");
	}

}
