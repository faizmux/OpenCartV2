package utilities;

import lombok.Data;
import org.apache.poi.sl.usermodel.Sheet;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //DataProvider
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testdata\\Opencart_loginData.xlsx"; //taking xl file from testData
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String logindata[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {  //1  //read the data from xl storing in two dimensional array
            for (int j = 0; j < totalcols; j++) {  //0   i is  rows and j is column
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);  //1,0
                System.out.println("Row " + (i - 1) + ", Col " + j + ": " + logindata[i - 1][j]);
            }
        }
        return logindata; // return two dimensional array
    }
}
