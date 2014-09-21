package com.NFHS.base;

import org.apache.log4j.Logger;

import com.NFHS.listener.JyperionListener;

public class TestBase 
{

	public static Logger APP_LOGS=null;
	public static boolean iSinitialize=false;
	
	//initializing the Tests
	public void initialize()
	{
		if(!iSinitialize)
		{
		//logs
			
		APP_LOGS=Logger.getLogger("rootLogger");
		
		}
		iSinitialize=true;
		
	 }
	
	
}
