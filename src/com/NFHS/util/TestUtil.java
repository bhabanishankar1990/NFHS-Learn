package com.NFHS.util;



import java.util.ArrayList;
import java.util.List;

public class TestUtil 
{
   
	public static boolean isSuiteRunnable(String path,String workbookname,String sheetname,String suitename)
	
	{
		Xls_Reader xls=new Xls_Reader();
		int rownum=xls.getRowNumByColName(path, workbookname, sheetname, suitename);
		int colnum=xls.getColNumByColName(path, workbookname, sheetname, "Runmode");
		String data=xls.getDataOfParticularCell(path, workbookname, sheetname, colnum, rownum);
		if(data.equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}
	
	public static boolean isTestCaseRunnable(String path,String workbookname,String sheetname,String testcasename)
	{
		Xls_Reader xls=new Xls_Reader();
		int rownum=xls.getRowNumByColName(path, workbookname, sheetname, testcasename);
		int colnum=xls.getColNumByColName(path, workbookname, sheetname,"Runmode");
		String data=xls.getDataOfParticularCell(path, workbookname, sheetname, colnum, rownum);
		//System.out.println(data);
		if(data.equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}
	
	public static boolean isSheetExists(String path,String workbookname,String sheetname)
	{
		Xls_Reader xls=new Xls_Reader();
		if(xls.isSheetExists(path, workbookname, sheetname))
			return true;
		else 
			return false;
	}
	
	public static Object[][] getData(String path,String workbookname,String sheetname)
	{
		Xls_Reader xls=new Xls_Reader();
		int cols=xls.getNumberOfCols(path, workbookname, sheetname);
		//System.out.println(cols);
		int rows=xls.getNumberOfRows(path, workbookname, sheetname);
		//System.out.println(rows);
		Object data[][]=new Object[rows-1][cols-3];
		if(!isSheetExists(path, workbookname, sheetname))
			return new Object[1][0];
		for(int i=2;i<=rows;i++)
		{
			for(int j=1;j<=cols-3;j++)
			{
	
				//System.out.println(xls.getDataOfParticularCell(path, workbookname, sheetname,j,i));
				data[i-2][j-1]=xls.getDataOfParticularCell(path, workbookname, sheetname,j,i);
			}
		}
		return data;
		 
	}
	
	public static String[] getRunmodes(String path,String workbookname,String sheetname)
	{
		List<String> runmodes=new ArrayList<String>();
		Xls_Reader xls=new Xls_Reader();
		runmodes=xls.getDataOfParticularCol(path, workbookname, sheetname,"Runmode");
		String[] str=new String[runmodes.size()];
		for(int i=1;i<runmodes.size();i++)
		{
			//System.out.println(str);
			str=runmodes.toArray(new String[runmodes.size()]);
		}
		return str;
		
	}
	public static void updateResult(String path,String workbookname,String sheetname,int rownum,String colname,String data)
	{
		Xls_Reader xls=new Xls_Reader();
		xls.addDataToCell(path, workbookname, sheetname, colname, rownum+1, data);
	}
	
	public static int getRowNumber(String path,String workbookname,String sheetname,String colname)
	{
		Xls_Reader xls=new Xls_Reader();
		int rowno=xls.getRowNumByColName(path, workbookname, sheetname, colname);
		return rowno;
	}
	public static void main(String args[])
	{
		//TestUtil.isSuiteRunnable(System.getProperty("user.dir")+"\\src\\test\\resources","suite","Test Suites","Selfservices");
		//TestUtil.isTestCaseRunnable(System.getProperty("user.dir")+"\\src\\test\\resources","Checkout","Test Cases","Delivery_Address_Check");
		//TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\test\\resources","Registration","Test Cases","Results",2,"Pass");
		//TestUtil.getData(System.getProperty("user.dir")+"\\src\\test\\resources","Checkout","Delivery_Address_Check");
		 System.out.println(TestUtil.getRowNumber(System.getProperty("user.dir")+"\\src\\com\\Framework\\xls","Registration","Test Cases","Registration_Of_New_User"));
		//TestUtil.getRunmodes(System.getProperty("user.dir")+"\\src\\com\\Framework\\xls","Registration","Registration_Of_New_User");

	}
	

}
