package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="logintest")
	public String[][] getLoginData() throws IOException
	{
		String path=".\\testData\\logindata.xlsx";
	    ExcelUtility xl=new ExcelUtility(path);
	    int rowcount=xl.getRowCount("Sheet1");
	    int colcount=xl.getCellCount("Sheet1", 1);
	    
	    String logindata[][]=new String[rowcount][colcount];
	    
	    for (int i=1;i<=rowcount;i++)
	    {
	    	for (int j=0;j<colcount;j++)
	    	{
	    		logindata[i-1][j]=xl.getCellData("Sheet1", i, j);
	    	}
	    }
	      return logindata;
	}
 }
