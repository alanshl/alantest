package bydbom.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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



import java.sql.*;


public class BCommonFunction {
	private ObjectMapper mapper=new ObjectMapper();
	private Map<String, String> testData=new HashMap<String, String>();
	
	private final String EXCEL_XLS = "xls";  
    private final String EXCEL_XLSX = "xlsx";
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
	public static void main(String[] args)
	{
		BCommonFunction cf=new BCommonFunction();
		//cf.readJasonFile(EnvJsonFile.TESTDATA);
		//cf.getProperty("approver");
		//Map<String, String> testData1=new HashMap<String, String>();
		//testData1.put("approver3","shenhl");
		//testData1.put("test55", "cccc");
		//cf.writeJasonFile(EnvJsonFile.TESTDATA, testData1);
		//cf.getProperty("integration");
		//cf.connectDB();
		cf.connectDB(EnvJsonFile.BASICFILE, "integration");
		ResultSet rs=cf.queryData("select * from cust.cust_material_info cmi order by cmi.cust_material_info_id desc ");
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int columnCount;
			columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i=0;i<columnCount;i++) {
					System.out.print(rs.getString(i+1)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cf.closeDB();
		
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
	
	public void writeJasonFile(EnvJsonFile ejf, Map<String, String> jsonData) {
		try {
			String jsonpath;
		 	String jsonname;
		  	jsonname=ejf.getDesc();
			jsonpath=this.getProjectPath() + jsonname;
			File file=new File(jsonpath);
		  	if(file.exists()) {
		  		testData=mapper.readValue(file, Map.class);
		  		testData.putAll(jsonData);
		  		mapper.writeValue(new File(jsonpath), testData);
		  	}
		  	else {
		  		System.out.println("file doesn't exist, will create one to track the test data.");
		  		mapper.writeValue(new File(jsonpath), jsonData);
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
	        // ��ȡ��Ŀ·�� C:\Users\alans\git\alantest
	        File directory = new File("");// ����Ϊ��
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
			
			
            // ͬʱ֧��Excel 2003��2007  
            File excelFile = new File(filePath); // �����ļ�����  
            FileInputStream in = new FileInputStream(excelFile); // �ļ���  
            checkExcelVaild(excelFile);  
            Workbook workbook = getWorkbook(in,excelFile);  
            //Workbook workbook = WorkbookFactory.create(is); // ���ַ�ʽ Excel2003/2007/2010���ǿ��Դ����  
            
            /** 
             * ���õ�ǰexcel��sheet���±꣺0��ʼ 
             */  
            Sheet sheet = workbook.getSheetAt(sheetIndex);   // ������һ��Sheet   
            Row row;
            Cell cell;
            //��ȡ������
            int lastRow=sheet.getPhysicalNumberOfRows();
            int rowCount;
            for(rowCount=rowStart;rowCount<lastRow;rowCount++)
            {
            	ArrayList<Object> colList=new ArrayList<Object>();
            	row=sheet.getRow(rowCount);
            	if(row == null){  
                    //����ȡ��Ϊ��ʱ  
                  if(rowCount != sheet.getPhysicalNumberOfRows()){//�ж��Ƿ������һ��  
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
     * �ж�Excel�İ汾,��ȡWorkbook 
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
     * �ж��ļ��Ƿ���excel 
     * @throws Exception  
     */  
    public void checkExcelVaild(File file) throws Exception{  
        if(!file.exists()){  
            throw new Exception("�ļ�������");  
        }  
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){  
            throw new Exception("�ļ�����Excel");  
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
    
    public void connectDB(EnvJsonFile ejf, String env) {
    	this.readJasonFile(ejf);
    	if(env.equals("integration")) {
    		String url=this.getProperty("integrationDB");
    		String user=this.getProperty("integrationDBUser");
			String password=this.getProperty("integrationDBPWD");
			try {
				this.conn=DriverManager.getConnection(url, user, password);
				this.stmt=this.conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}
    }
    
    public void closeDB() {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ResultSet queryData(String sql) {
    	try {
			this.rs=this.stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }
    
    public int updateData(String sql) {
    	int result=0;
		try {
			result = this.stmt.executeUpdate(sql);
			this.conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    public void connectDB() {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url="jdbc:oracle:thin:@192.168.1.61:1621:xe";
			String user="system";
			String password="admin123";
			Connection conn= DriverManager.getConnection(url,user,password);
			Statement stmt = conn.createStatement();
			String sql="select * from cust.cust_material_info cmi order by cmi.cust_material_info_id desc ";
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount=rsmd.getColumnCount();
			while(rs.next()) {
				for(int i=0;i<columnCount;i++) {
					System.out.print(rs.getString(i+1)+"\t");
				}
				System.out.println();
			}
			
			sql="update CUST.CUST_MATERIAL_INFO\r\n" + 
					"set \r\n" + 
					"CF_STATUS='APPLY_COMPLETE',\r\n" + 
					"CF_NO='P'||(select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"MAT_NO=(select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"mat_desc='mat_desc' || (select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"mat_endesc='mat_endesc' || (select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"ACTIVE_STATUS='CURRENT'\r\n" + 
					"where cust_material_info_id='21'";
			
			System.out.println(stmt.executeUpdate(sql));
			
			conn.commit();
			
			//System.out.println(stmt.execute("commit"));
			
			conn.close();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}

