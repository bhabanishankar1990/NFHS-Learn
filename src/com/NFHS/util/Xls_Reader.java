package com.NFHS.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader 
{
	FileInputStream fis;
	FileOutputStream fos;
	Workbook wb;
	Sheet sh;
	
	public void createNewWorkbook(String path,String workbookname,String sheetname)
	{
		try
		{
			fos=new FileOutputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook();
			sh=wb.createSheet(sheetname);
			wb.write(fos);
			fos.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void addSheetToExistingWorkbook(String path,String workbookname,String sheetname)
	{
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			fos=new FileOutputStream(new File(path+"\\"+workbookname+".xlsx"));
			sh=wb.createSheet(sheetname);
			wb.write(fos);
			fos.close();
			fis.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getSheetInformation(String path,String workbookname,String sheetname)
	{
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
			Iterator<Row> rows=sh.rowIterator();
			while(rows.hasNext())
			{
				Row row=rows.next();
				Iterator<Cell> cells=row.cellIterator();
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					/*if(row.getCell(cell.getColumnIndex())==null)
					{
						System.out.println("printing");
						System.out.println(cell.toString());
					}*/
					switch(cell.getCellType())
					{
					case Cell.CELL_TYPE_STRING:
						 System.out.println(cell.getStringCellValue());
						 break;
					case Cell.CELL_TYPE_NUMERIC:
						 double d=cell.getNumericCellValue();
						 System.out.println(String.valueOf(d));
						 break;
				
					}
					
				}
			}
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getColNumByColName(String path,String workbookname,String sheetname,String colname)
	{
		int colno=0;
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
			Iterator<Row> rows=sh.rowIterator();
			while(rows.hasNext())
			{
				Row row=rows.next();
				Iterator<Cell> cells=row.cellIterator();
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					if(cell.getStringCellValue().equals(colname))
					{
						
					   colno=cell.getColumnIndex()+1;
						 
					}
				}
			}
			
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return colno;
		
	}
	public void addDataToCell(String path,String workbookname,String sheetname,String colname,int rowno,String data)
	{
		int colno=getColNumByColName(path, workbookname, sheetname, colname);
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			fos=new FileOutputStream(new File(path+"\\"+workbookname+".xlsx"));
			sh=wb.getSheet(sheetname);
			
			if(sh.getRow(rowno-1)!=null)
			{
				
				Cell cell=sh.getRow(rowno-1).createCell(colno-1);
				cell.setCellValue(data);
			}
			else
			{
			  sh.createRow(rowno-1).createCell(colno-1).setCellValue(data);	
			}
			 wb.write(fos);
			 fos.close();
			 fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getDataOfParticularCell(String path,String workbookname,String sheetname,int colnum,int rownum)
	{
		String cellvalue="";
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
			double d;
			if(sh.getRow(rownum-1)!=null)
			{
				Cell cell=sh.getRow(rownum-1).getCell(colnum-1);
				
				if(cell==null)
				{
					return cellvalue;
				}
			    switch(cell.getCellType())
			    {
			    case Cell.CELL_TYPE_STRING:
			    	 cellvalue=cell.getStringCellValue();
			    	 break;
			    	 
			    case Cell.CELL_TYPE_NUMERIC:
			    	 d=cell.getNumericCellValue();
			    	 cellvalue=String.valueOf(d);
			    	 break;
			    	 
			    	 
			    }
			 fis.close();
			
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cellvalue;
	}
	
	public List<String> getDataOfParticularCol(String path,String workbookname,String sheetname,String colname)
	{
		List<String> cellvalues = new ArrayList<String>();
	   try
	   {
		 double d;
		  String cellval = null;
		fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetname);
		int colno=getColNumByColName(path, workbookname, sheetname, colname);
		System.out.println(colno);
		if(colno==-1)
		{
			throw new IllegalArgumentException();
		}
		
		Iterator<Row> rows=sh.rowIterator();
		while(rows.hasNext())
		{
			Row row=rows.next();
			Cell cell=row.getCell(colno-1);
			if(cell!=null)
			{
			switch(cell.getCellType())
			{
			case Cell.CELL_TYPE_STRING:
				 cellval=cell.getStringCellValue();
				 cellvalues.add(cellval);
				 break;
			case Cell.CELL_TYPE_NUMERIC:
				 d=cell.getNumericCellValue();
				 cellval=String.valueOf(d);
				 cellvalues.add(cellval);
				 break;	 
			}
			
			}
			else
			{
				cellval=" ";
				cellvalues.add(cellval);
			}
		}
		fis.close();
	   }
	   catch(IllegalArgumentException t)
	   {
		   System.out.println("No such column exists");
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	  
	   return cellvalues;	
	}
	
	public List<String> getDataOfParticularRow(String path,String workbookname,String sheetname,int rownum)
	{
		List<String> cellvalues=new ArrayList<String>();
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
			String cellvalue;
			double d;
			if(sh.getRow(rownum-1)==null)
				throw new NullPointerException();
			if(sh.getRow(rownum-1)!=null)
			{
				Row row=sh.getRow(rownum-1);
				Iterator<Cell> cells=row.cellIterator();
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					switch(cell.getCellType())
					{
					case Cell.CELL_TYPE_STRING:
						 cellvalue=cell.getStringCellValue();
						 cellvalues.add(cellvalue);
						 break;
					case Cell.CELL_TYPE_NUMERIC:
						 d=cell.getNumericCellValue();
						 cellvalue=String.valueOf(d);
						 cellvalues.add(cellvalue);
						 break;
						 
					}
					}	
				}
		
		}
		catch(NullPointerException e)
		{
			System.out.println("Row doesn't exists");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cellvalues;
	}
	public int getRowNumByColName(String path,String workbookname,String sheetname,String colname)
	{
		int rownum=0;
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
			String value = null;
		    Iterator<Row> rows=sh.rowIterator();
		    while(rows.hasNext())
		    {
		    	Row row=rows.next();
		    	Iterator<Cell> cells=row.cellIterator();
		    	while(cells.hasNext())
		    	{
		    		Cell cell=cells.next();
		    		switch(cell.getCellType())
		    		{
		    		case Cell.CELL_TYPE_STRING:
		    			 value=cell.getStringCellValue();
		    			 break;
		    		case Cell.CELL_TYPE_NUMERIC:
		    			 double d=cell.getNumericCellValue();
		    			 value=String.valueOf(d);
		    			 break;
		    		}
		    		//System.out.println(value);
		    		if(value.equalsIgnoreCase(colname))
				     {
				    	 rownum=cell.getRowIndex()+1;
				     }
		    	}
		    }
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rownum;
		
	}
	public boolean isSheetExists(String path,String workbookname,String sheetname)
	{
		boolean issheetexists=false;
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			int no_of_sheets=wb.getNumberOfSheets();
			for(int i=0;i<no_of_sheets;i++)
			{
				if(wb.getSheetAt(i).getSheetName().equals(sheetname))
				{
					issheetexists=true;
				}
			
			}
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return issheetexists;
	}
	
	public int getNumberOfRows(String path,String workbookname,String sheetname)
	{
		int no_of_rows = 0;
		try
		{
			fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetname);
		    no_of_rows=sh.getLastRowNum()+1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return no_of_rows;
	}
	
	public int getNumberOfCols(String path,String workbookname,String sheetname)
	{
		int colnum=0;
		try
	{
		fis=new FileInputStream(new File(path+"\\"+workbookname+".xlsx"));
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetname);
		int rowno=sh.getFirstRowNum();
		colnum=sh.getRow(rowno).getLastCellNum();
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return colnum;
		
	}
	
	

	public static void main(String[] args) 
	{
		Xls_Reader xls=new Xls_Reader();
		//xls.createNewWorkbook(System.getProperty("user.dir")+"\\src\\test\\resources","sample","testcases");
		//xls.addSheetToExistingWorkbook(System.getProperty("user.dir")+"\\src\\test\\resources","sample","testcase2");
		//System.out.println(xls.getColNumByColName(System.getProperty("user.dir")+"\\src\\test\\resources","Registration","Test Cases","Runmode"));
         //System.out.println(xls.getDataOfParticularCell(System.getProperty("user.dir")+"\\src\\test\\resources","Registration","Registration_Case2", 2, 4));
	   /* List<String> cellvals=xls.getDataOfParticularCol(System.getProperty("user.dir")+"\\src\\test\\resources","Registration","Test Cases","Runmode");
	      for(String val:cellvals)
	      {
	    	  System.out.println(val);
	      }*/
	      
	     /* List<String> cellvals=xls.getDataOfParticularRow(System.getProperty("user.dir")+"\\src\\test\\resources","Registration","Test Cases",3);
	      for(String val:cellvals)
	      {
	    	  System.out.println(val);
	      }*/
		
		   //xls.getSheetInformation(System.getProperty("user.dir")+"\\src\\test\\resources","Registration", "Registration_Case1");
		//System.out.println(xls.getRowNumByColName(System.getProperty("user.dir")+"\\src\\test\\resources","Checkout", "Test Cases","Delivery_Address_Check"));
	   // System.out.println(xls.isSheetExists(System.getProperty("user.dir")+"\\src\\test\\resources","Registration", "Registration_Case1"));
	    //System.out.println(xls.getNumberOfRows(System.getProperty("user.dir")+"\\src\\test\\resources","Registration", "Registration_Case1"));
	      //System.out.println(xls.getNumberOfCols(System.getProperty("user.dir")+"\\src\\test\\resources","Registration", "Registration_Case1"));
	        System.out.println(xls.getRowNumByColName(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password","Test Cases","Forgot_Password2"));
	}

}
