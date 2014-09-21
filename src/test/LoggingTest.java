package test;

import org.apache.log4j.Logger;

public class LoggingTest 

{
	
	
	public static void main(String args[])
	{
		Logger APP_LOGS= Logger.getLogger("rootLogger");
		APP_LOGS.debug("CORRECT");
		APP_LOGS.debug("Executing");
	}

}
