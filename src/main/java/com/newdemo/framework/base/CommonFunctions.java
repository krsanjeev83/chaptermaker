package com.newdemo.framework.base;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import java.util.Set;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CommonFunctions
{

	//======================================SPLIT THE PARAMETERS INTO KEYS AND VALUES AND STORE THEM IN HASHMAP FOR HANDLING DATA INPUT=============================
	public HashMap<String, String> SplitNStoreParamsInHashMap(String strParameters)
	{
		HashMap<String, String> objHashMap = new HashMap<String, String>();
		if(strParameters != "" && strParameters != null)
		{
			String[] arrKeysNValues = strParameters.split("--");

			for(int intArrElt = 0; intArrElt < arrKeysNValues.length; intArrElt++)
			{
				if(arrKeysNValues[intArrElt] != "" && arrKeysNValues[intArrElt] != null)
				{
					String[]  arrCurrKeyNValue = arrKeysNValues[intArrElt].split("=>");
					objHashMap.put(arrCurrKeyNValue[0], arrCurrKeyNValue[1]);
				}
			}
		}
		return objHashMap;
	}

	  //==========================1_FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
    public HashMap<String, String> fetchXLDataNStoreInHashMap(String strParametersNValues, String strSheetName, String strDataRow, String strColumnsCommaSeparated) throws Exception
    {
    	GlobalPaths objGLPaths = new GlobalPaths();
    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(objGLPaths.strXLInputDatasheetPath));
    	HSSFSheet objSHInputSheet = objWB.getSheet(strSheetName);

		HashMap<String, String> hmParamsNValues = SplitNStoreParamsInHashMap(strParametersNValues);
		int intParamInputRow = Integer.parseInt((String) hmParamsNValues.get(strDataRow));

		if(strColumnsCommaSeparated.equalsIgnoreCase("All"))
		{
			String strAllColumns = "";
			int intLastCellNum = objSHInputSheet.getRow(0).getLastCellNum();
			for(int intCell = 0; intCell < intLastCellNum; intCell++)
			{
				if(strAllColumns == "")
					strAllColumns = objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
				else
					strAllColumns = strAllColumns + "," + objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
			}
			strColumnsCommaSeparated = strAllColumns;
		}

		String[] arrColumnNames = strColumnsCommaSeparated.split(",");
		HashMap<String, String> hmInputDataSet = new HashMap<String, String>();
		String strCurrCellValue = "";
		for(String strColumnName: arrColumnNames)
		{
			strCurrCellValue = GetCellValueForRowNum(objSHInputSheet, strColumnName, intParamInputRow);
			if(strCurrCellValue.contains("REPDATETIME"))
			{
				strCurrCellValue = strCurrCellValue.replace("REPDATETIME", (GetDateTimeString().replace("-", "")).replace(" ", ""));
			}
			hmInputDataSet.put(strColumnName, strCurrCellValue);
		}

		return hmInputDataSet;
    }

	//=========================================2_GET VALUE FROM A ROW AND COLUMN FROM EXCEL SHEET===============
	public String GetCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception
	{
		intRowNum = intRowNum - 1;
		int intXLColumn;
		intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);

		String strAvailCellValue;
		Cell objXLCell;
		try
		{
			objXLCell = objSH.getRow(intRowNum).getCell(intXLColumn);
			objXLCell.setCellType(1); // to convert cell type to string
			strAvailCellValue = objXLCell.getStringCellValue();
			return strAvailCellValue.trim();
		}
		catch(Exception e)
		{
			//System.out.println("Exception " + e);
			return "";
		}
	}
	
	//========================================3_FIND COLUMN NUMBER IN EXCEL SHEET==============================
		public int FindColumnNoInExcel(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception
		{
			intRowNum = intRowNum - 1;
			HSSFRow objRow = objSH.getRow(intRowNum);

			int intLastCellNum;
			String strAvailCellValue;

			try
			{
				intLastCellNum = objRow.getLastCellNum();
				for(int intCell = 0; intCell < intLastCellNum; intCell++)
				{
					strAvailCellValue = objSH.getRow(intRowNum).getCell(intCell).getStringCellValue();
					if(strAvailCellValue.equalsIgnoreCase(strColumnName))
					{
						return intCell;
					}
				}
			}
			catch(Exception e)
			{
				return -1;
			}
			return -1;
		}
		
	    //==========================FETCH DATA FROM APPLICATION AND STORE IN EXCEL===============================
	    public void storeHashMapDataInExcel(HashMap<String, String> hmOutputDataSet, String strSheetName, boolean blnForceNewLine, int intRowNum) throws Exception
	    {
	    	GlobalPaths objGLPaths = new GlobalPaths();
	    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(objGLPaths.strXLInputDatasheetPath));
	    	HSSFSheet objSHOutputSheet = objWB.getSheet(strSheetName);

	    	String[] arrColumnNames = getHashMapKeysInStringArray(hmOutputDataSet);

	    	int intOUTRowNum = 0;
	    	if(intRowNum <= 0)
	    	{
		    	if(blnForceNewLine == true)
		    	{
		    		intOUTRowNum = objSHOutputSheet.getLastRowNum() + 1;
		    	}
		    	else
		    	{
					intOUTRowNum = FindRowNoForValueInExcel(objSHOutputSheet, arrColumnNames[0], hmOutputDataSet.get(arrColumnNames[0]));
					if(intOUTRowNum < 0)
					{
						intOUTRowNum = objSHOutputSheet.getLastRowNum() + 1;
					}
		    	}
	    	}
	    	else
	    	{
	    		intOUTRowNum = intRowNum - 1;
	    	}

	    	for(String strColumnName: arrColumnNames)
	    	{
	    		SetCellValueForRowNum(objSHOutputSheet, strColumnName, intOUTRowNum, hmOutputDataSet.get(strColumnName));
	    	}
	    	WriteXLOutputStream(objWB, objGLPaths.strXLInputDatasheetPath);

	    }
	    
	    //==========================FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
	    public String[] getHashMapKeysInStringArray(HashMap<String, String> hmOutputDataSet) throws Exception
	    {
	    	String[] arrStrings = new String[hmOutputDataSet.keySet().size()];

	    	Set<String> clxnStrings = hmOutputDataSet.keySet();
	    	int intKey = 0;
			for(String strInput: clxnStrings)
	    	{
	    		arrStrings[intKey] = strInput;
	    		intKey = intKey + 1;
	    	}

	    	return arrStrings;
	    }
	    
	    
	  //=========================================SET VALUE FROM A ROW AND COLUMN FROM EXCEL SHEET===============
		public Boolean SetCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum, String strValue) throws Exception
		{
			//intRowNum = intRowNum - 1;
			int intRowCount, intColumnCount, intColumnNum;
			intRowCount = objSH.getLastRowNum();

			HSSFRow objRow = null;

			intColumnNum = this.FindColumnNoInExcel(objSH, strColumnName, 1);

			if(intRowCount >= intRowNum)
			{
				if(objSH.getRow(intRowNum) != null)
				{
					objRow = objSH.getRow(intRowNum);
				}
				else
				{
					objRow = objSH.createRow(intRowNum);
				}

				if(objRow.getCell(intColumnNum) != null)
				{
					objRow.getCell(intColumnNum).setCellValue(strValue);
				}
				else
				{
					objRow.createCell(intColumnNum).setCellValue(strValue);
				}
				return true;
			}
			else
			{
				if(objSH.getRow(intRowNum) != null)
				{
					objRow = objSH.getRow(intRowNum);
				}
				else
				{
					objRow = objSH.createRow(intRowNum);
				}
				objRow.createCell(intColumnNum).setCellValue(strValue);
				return true;
			}
		}
		
		  //==========================WRITE EXCEL OUTPUT STREAM=======================================================
	    public Boolean WriteXLOutputStream(HSSFWorkbook objWB, String strXLPath) throws Exception
	    {
	    	try
	    	{
	    		OutputStream objOutputStream = new FileOutputStream(strXLPath);
	    		objWB.write(objOutputStream);
	    		objOutputStream.flush();
	    		objOutputStream.close();
	    		return true;
	    	}
	    	catch(Exception objException)
	    	{
	    		System.out.println(GetExceptionNDisplay(objException, true));
	    		return false;
	    	}
	    }


		//=========================================FINDS IN WHICH ROW THE SEARCHED VALUE IS FOUND IN THE COLUMN IN EXCEL===============
		public int FindRowNoForValueInExcel(HSSFSheet objSH, String strColumnName, String strSearchValue) throws Exception
		{
			int intXLColumn, intXLRowCount;
			intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);

			String strAvailCellValue;
			Cell objXLCell;
			try
			{
				intXLRowCount = objSH.getLastRowNum();
				for(int intRow = 0; intRow < intXLRowCount + 1; intRow++)
				{
					objXLCell = objSH.getRow(intRow).getCell(intXLColumn);
					try
					{
						strAvailCellValue = objXLCell.getStringCellValue();
					}
					catch(NullPointerException e)
					{
						strAvailCellValue = "";
					}


					if(strAvailCellValue.equalsIgnoreCase(strSearchValue))
					{
						return intRow;     //or: intRow+1; need to check
					}
				}
			}
			catch(Exception e)
			{
				//System.out.println("Exception " + e);
				return -1;
			}
			return -1;
		}

		
		//========================GET EXCEPTION MESSAGE AND DISPLAY IT===========================================
		public String GetExceptionNDisplay(Exception objException, boolean blnIsDisplay) throws Exception
		{
			String strException = objException.getMessage();
			if(strException != null)
			{
				String[] arrException = strException.split("\n");
				if(blnIsDisplay == true)
				{
					System.out.println("Exception occurred " + arrException[0]);
				}
				return "<font color='blue'>" + arrException[0] + "</font>";
			}
			else
			{
				return "<font color='blue'>No specific error message thrown from driver for the current step. Check error message in previous steps</font>";
			}
		}
		//==========================METHOD TO RETURN CURRENT DATE TIME STRING===============================================
		public String GetDateTimeString()
		{
			SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date objDate = new Date();
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(objDate);
			String strAppendDateTime = objSimpleDtFormat.format(new Date());
			strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
			return strAppendDateTime;
		}
		
		//==========================RETURN CURRENT DATE TIME STRING===============================================
		public String GetDateTimeStringWithSeconds()
		{
			SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			Date objDate = new Date();
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(objDate);
			String strAppendDateTime = objSimpleDtFormat.format(new Date());
			strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
			return strAppendDateTime;
		}
		
		//==========================ROUND FLOAT NUMBERS TO DESIRED DECIMALS========================================
	    public float RoundFloatNumber(float fltInputNo, int intNoOfDecimals) {
	        BigDecimal bd = new BigDecimal(Float.toString(fltInputNo));
	        bd = bd.setScale(intNoOfDecimals, BigDecimal.ROUND_HALF_UP);
	        return bd.floatValue();
	    }

}
