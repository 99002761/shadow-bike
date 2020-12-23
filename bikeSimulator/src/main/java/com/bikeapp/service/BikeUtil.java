package com.bikeapp.service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.bikeapp.bean.Bike;
import com.bikeapp.bean.Location;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
@Component
public class BikeUtil {
	@Value("${excelFile.url}") 
	private String excelFileUrl;
	@Autowired
	Bike bike;
	
	public static int id=0;
	public static int rowcount=0;
	
	
	public Integer getRandomNumber(int min, int max) {
		Integer randomNumber = (int)(Math.random() * (max - min + 1) + min);
		return randomNumber;
	}
	public Double getRandomNumberDouble(int min, int max) {
		DecimalFormat df = new DecimalFormat("###.##");
		Double randomNumber = (Math.random() * (max - min + 1) + min);
		return Double.parseDouble(df.format(randomNumber));
	}
	
	public Long getRandomNumberLong(int min , int max)
	{
		
		Random rd = new Random(); // creating Random object
		Long randomNumber = (long) (Math.random() * (max - min + 1) + min);
	     return randomNumber ;
		
	}
	public String getRandomElement() 
    { 
		String[] engine_status = new String[]{"ON" ,"OFF"};
        Random rand = new Random(); 
	return engine_status[rand.nextInt(engine_status.length)]; 
    } 
	public Integer getEventId() 
    { 
      
        id = id + 1;
        return id;
    } 
	
	public static Date NewDate()
    {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = new Date();
         return date;
         
    }
	public Integer calculateSpeedFromRpm(Integer rpm) {
        Integer diameter =getRandomNumber(29,31) ;   
      int speed= (int) (((Math.PI ) * (rpm )* (diameter)/1056) * 1.609344);
      if(speed<=200)
      {
          return speed;
      }
      else
          return getRandomNumber(150,200);
    }
	
	/**
	 * readDataFromExcel Method read location data from ExcelFile
	 */
	 public Double readDataFromExcel(Location location) throws IOException
	    {
	       
	        try {
	         InputStream file= (InputStream) this.getClass().getResourceAsStream(excelFileUrl);
	          XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
	           XSSFSheet myExcelSheet =myExcelBook.getSheetAt(0);
	           if(rowcount<98)
				{ 
					Row row = myExcelSheet.getRow(rowcount); 
					if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
						double latitude = row.getCell(0).getNumericCellValue(); 
						location.setLatitude(latitude);
						
						
					}
					if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						double longitude= row.getCell(1).getNumericCellValue(); 
						location.setLongitude(longitude);
					
					}
				}
				else
				{
					Row row = myExcelSheet.getRow(rowcount); 
					if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
						double latitude = row.getCell(0).getNumericCellValue(); 
						location.setLatitude(latitude);
					}
					if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						double longitude= row.getCell(1).getNumericCellValue(); 
						location.setLongitude(longitude);
					}
					rowcount=0;	
				}
				rowcount++;
			}catch(FileNotFoundException e) {
				System.out.println("Exception while reading Excel "+e);
			}
			return null;
	    }
}
