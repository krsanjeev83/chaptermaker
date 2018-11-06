/*package com.newdemo.framework.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.sun.mail.smtp.SMTPTransport;

public class MailHTMLReports 
{
	//============================SENDS THE EMAIL===================================
	public MailHTMLReports(String strToMailingList) throws Exception 
	{
		CommonFunctions objCMNFunctions = new CommonFunctions();
		//==================READ MODULEWISE REPORT HTML AND STORE ALL THE LINES IN STRING BUFFER=============================
		String strModuleFilePath = new HTMLFunctions(null).strModuleResultsFilePath;
		String strStepResultsFilePath = new HTMLFunctions(null).strResultsFilePath;
		String strResultsFolderPath = new HTMLFunctions(null).strCurrentExecutionFolderPath;
		new ZipUtils();		
		
		//===================ZIP THE EXECUTION REPORT FOLDER========================================
		
		BufferedReader objBFRReader = new BufferedReader(new FileReader(strModuleFilePath));
		
		String strCurrentLine = "";
		StringBuffer objStringBuffer = new StringBuffer();
		while ((strCurrentLine = objBFRReader.readLine()) != null) 
		{
			objStringBuffer.append(strCurrentLine);
		}
		objBFRReader.close();
		
		//===================SEND HTML MAIL==========================================================
		strToMailingList = "sundar.babu@fox.com";		
		Properties objMailProperties = System.getProperties();
		objMailProperties.put("mail.smtps.host", "smtp.gmail.com");
		objMailProperties.put("mail.smtps.auth", "true");
		
		Session objSession = Session.getInstance(objMailProperties, null);
		Message objMessage = new MimeMessage(objSession);
		
		objMessage.setFrom(new InternetAddress("dmaqa@gmail.com"));
		objMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strToMailingList, false));
		objMessage.setSubject("["+ GlobalPaths.strGLEnvironment + "] AUTOMATION EXECUTION REPORT " + objCMNFunctions.GetDateTimeString());
		
		MimeBodyPart objMIMEPart1 = new MimeBodyPart();
		objMIMEPart1.setDataHandler(new DataHandler(new ByteArrayDataSource(objStringBuffer.toString(), "text/html")));
		MimeBodyPart objMIMEPart2 = new MimeBodyPart();
		objMIMEPart2.attachFile(strResultsFolderPath + ".zip");
		MimeMultipart objMultiPart = new MimeMultipart();
		objMultiPart.addBodyPart(objMIMEPart1);
		objMultiPart.addBodyPart(objMIMEPart2);
		objMessage.setContent(objMultiPart);
				
		objMessage.setHeader("X-Mailer", "smtpsend");
		objMessage.setSentDate(new Date());
		
		SMTPTransport objTransport = (SMTPTransport)objSession.getTransport("smtps");
		objTransport.connect("smtp.gmail.com", "dmaqa@gmail.com", "dmaqa");
		objTransport.sendMessage(objMessage, objMessage.getAllRecipients());
		objTransport.close();
	}

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
*/