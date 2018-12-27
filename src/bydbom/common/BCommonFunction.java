package bydbom.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BCommonFunction {
	private ObjectMapper mapper=new ObjectMapper();
	private Map<String, String> testData=new HashMap<String, String>();
	
	private final String EXCEL_XLS = "xls";  
    private final String EXCEL_XLSX = "xlsx";
    
	public static void main(String[] args)
	{
		BCommonFunction cf=new BCommonFunction();
		//cf.readJasonFile(EnvJsonFile.TESTDATA);
		//cf.getProperty("approver");
		Map<String, String> testData1=new HashMap<String, String>();
		testData1.put("approver3","shenhl");
		testData1.put("test55", "cccc");
		cf.writeJasonFile(EnvJsonFile.TESTDATA, testData1);
		//cf.getProperty("integration");
		
		
	}
	public void readJasonFile(EnvJsonFile ejf) {
		
		try {
			 	String jsonpath;
			 	String jsonname;
			  	jsonname=ejf.getDesc();
			  	jsonpath=this.getProjectPath() + jsonname;
			  	File file=new File(jsonpath);
			  	if(file.exists())
			  		testData=mapper.readValue(file, Map.class); 
			  	else
			  		System.out.println("file doesn't exist");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void writeJasonFile(EnvJsonFile ejf, Map<String, String> jasonData) {
		try {
			String jsonpath;
		 	String jsonname;
		  	jsonname=ejf.getDesc();
			jsonpath=this.getProjectPath() + jsonname;
			File file=new File(jsonpath);
		  	if(file.exists()) {
		  		testData=mapper.readValue(file, Map.class);
		  		testData.putAll(jasonData);
		  		mapper.writeValue(new File(jsonpath), testData);
		  	}
		  	else {
		  		System.out.println("file doesn't exist, will create one to track the test data.");
		  		mapper.writeValue(new File(jsonpath), jasonData);
		  	}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//get the path of the project
	public String getProjectPath()
	{
		String courseFile="";
		try {
	        // 获取项目路径 C:\Users\alans\git\alantest
	        File directory = new File("");// 参数为空
	        courseFile = directory.getCanonicalPath();
	        
		}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return courseFile;
	}
	
	//return the value basing on the name from json file
	public String getProperty(String name) {
		String value="";
		value=testData.get(name);
		System.out.println(name + ":" + value);
		return value;
	}
	
	//return the time stamp 
	public String getTimeStamp()
	{
		String timeStamp="";
		timeStamp=String.valueOf(System.currentTimeMillis() / 1000);
		return timeStamp;
	}
	
	/**
	 * @Function: read the excel file
	 * @param filePath, the file path
	 * @param sheetIndex, the sheet will be read
	 * @param rowStart, the row from which start to fetch the data
	 * @return ArrayList<ArrayList<Object>>, a 2-dimension arrary to  store all the data
	 */
	public ArrayList<ArrayList<Object>> readExcel(String filePath, int sheetIndex, int rowStart) {
		try {
			ArrayList<ArrayList<Object>> rowList=new ArrayList<ArrayList<Object>>();
			
			
            // 同时支持Excel 2003、2007  
            File excelFile = new File(filePath); // 创建文件对象  
            FileInputStream in = new FileInputStream(excelFile); // 文件流  
            checkExcelVaild(excelFile);  
            Workbook workbook = getWorkbook(in,excelFile);  
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的  
            
            /** 
             * 设置当前excel中sheet的下标：0开始 
             */  
            Sheet sheet = workbook.getSheetAt(sheetIndex);   // 遍历第一个Sheet   
            Row row;
            Cell cell;
            //获取总行数
            int lastRow=sheet.getPhysicalNumberOfRows();
            int rowCount;
            for(rowCount=rowStart;rowCount<lastRow;rowCount++)
            {
            	ArrayList<Object> colList=new ArrayList<Object>();
            	row=sheet.getRow(rowCount);
            	if(row == null){  
                    //当读取行为空时  
                  if(rowCount != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
                        rowList.add(colList);  
                    }  
                    continue;  
                }
            	else {
	            	for(int colCount=row.getFirstCellNum();colCount<row.getLastCellNum();colCount++) {
	            		cell=row.getCell(colCount);
	            		Object obj;
	
	                       switch(cell.getCellType()){  
	                        case BOOLEAN:
	            	            obj = cell.getBooleanCellValue(); 
	            	            break;
	            	        case ERROR:
	            	            obj = cell.getErrorCellValue(); 
	            	            break;
	            	        case NUMERIC:
	            	            obj = cell.getNumericCellValue(); 
	            	            break;
	            	        case STRING:
	            	            obj = cell.getStringCellValue(); 
	            	            break;  
	                         default:        
	                            obj = cell.toString();    
	                            }// end switch  
	                    //System.out.println("row: " + rowCount + " - col: " + colCount);
	                    //System.out.println(obj.toString());
	                    colList.add(obj);
	            	}
            	}
            	rowList.add(colList);
            }
            return rowList;
            
		} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
	}
	
	/** 
     * 判断Excel的版本,获取Workbook 
     * @param in 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public Workbook getWorkbook(InputStream in,File file) throws IOException{  
        Workbook wb = null;  
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);  
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(in);  
        }  
        return wb;  
    }  

    /** 
     * 判断文件是否是excel 
     * @throws Exception  
     */  
    public void checkExcelVaild(File file) throws Exception{  
        if(!file.exists()){  
            throw new Exception("文件不存在");  
        }  
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){  
            throw new Exception("文件不是Excel");  
        }  
    }
    
    /**
     * read XML file
     * @param filePath, the XML file path
     * @return Document, contains the XML nodes
     */
    public Document readXML(String filePath) {
    	try {   
    		     File f = new File(filePath);   
    		     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
    		     DocumentBuilder builder = factory.newDocumentBuilder();   
    		     Document doc = builder.parse(f);   
    		     return doc;  
    		    } catch (Exception e) {   
    		     e.printStackTrace();   
    		     return null;
    		    }   
    }
    
    
}

