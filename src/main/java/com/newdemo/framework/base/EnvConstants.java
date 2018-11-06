package com.newdemo.framework.base;

import com.newdemo.framework.controller.ApplicationController;

public interface EnvConstants {
	public static long TEST_TIME_OUT = 6000;
	public static long TEST_TIME_OUT_EXTENDED = 9000;
	public String USERNAME_KEY = "username";
	public String PASSWORD_KEY = "password";
	public int SUCCESS = 0;
	public String EMPTY_STRING = "";
	public final static String ENVIRONMENT = "Environment";
	public final static String WAIT_TIME = "waitTime";
	public final static String SHORT_WAIT_TIME = "shortWaitTime";
	public final static String LONG_WAIT_TIME = "longWaitTime";
	public final static String IMPLICIT_WAIT_TIME = "implicitWaitTime";
}
