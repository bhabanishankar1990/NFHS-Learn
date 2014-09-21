package com.NFHS.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.NFHS.listener.TestListenerAdapter;

public class ErrorUtil 
{
	private static Map<ITestResult,List> verificationFailuresMap = new HashMap<ITestResult,List>();
	private static Map<ITestResult,List> skipMap = new HashMap<ITestResult,List>();
	
	public static void addVerificationFailure(Throwable t) 
	{
		//System.out.println("**************addVerficationFailure*********************");
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(t);
		
	}
	
	public static List<Throwable> getVerificationFailures() 
	{
		//System.out.println("**************getVerificationFailure********************");
		List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}
 
	
 
	
	
	
}
