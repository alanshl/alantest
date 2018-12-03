package com.selenium.test;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.json.*;

import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;   
   
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;   



public class HelloWorld {
	
	private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";
	
	public static void main(String[] args) {
		//System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		
		//WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();


        //驱动的网址
        //driver.get("http://www.baidu.com/");
		
		try {
			 /* 
			JsonNode node;
			 ObjectMapper mapper=new ObjectMapper();
			  	
			  	
			  	String jsonpath="C:/Users/alans/git/alantest/src/configuration/";
			  	String jsonname="alantest.json";
			  	System.out.println(jsonpath + jsonname);
			  	
			  	//C:\Users\sheny\git\alantest\src\configuration\
			  	//mapper.writeValue(new File(jsonpath + jsonname), json);
			  	//mapper.readValue("\""+jsonpath + jsonname+"\"", JsonNode.class);
			  	//node=mapper.readTree(new File(jsonpath+jsonname));
			  	node =mapper.readValue(new File(jsonpath+jsonname), JsonNode.class);
			  	
			  	System.out.println(node.get("url").toString().replaceAll("\"", "")); 
			  	
			  	*/
			
				//String filePath="C:/Users/alans/Desktop/BYD/data migration/process_collection.xlsx";
				//String filePath1="C:/Users/alans/Desktop/BYD/data migration/result.xlsx" ;
				
				//ArrayList<ArrayList<Object>> result;
				//result=readExcel(filePath,0,0,4);
				//writeExcel(filePath1, result);
				String xmlFilePath="C:/Users/alans/Desktop/123/template.xml";
				readXML(xmlFilePath);
			
			  }
			  catch(Exception e) {
				  System.out.println(e);
				  
			  }
		

	}
	
	public static ArrayList<ArrayList<Object>> readExcel(String filePath, int sheetIndex, int rowStart, int columnIndex) {
		try {
			ArrayList<ArrayList<Object>> rowList=new ArrayList<ArrayList<Object>>();
			
			
            // 同时支持Excel 2003、2007  
            File excelFile = new File(filePath); // 创建文件对象  
            FileInputStream in = new FileInputStream(excelFile); // 文件流  
            checkExcelVaild(excelFile);  
            Workbook workbook = getWorkbok(in,excelFile);  
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
	
	@SuppressWarnings("resource")
	public static void writeExcel(String filePath, ArrayList<ArrayList<Object>> result) {
		
		try {
			File excelFile = new File(filePath);
			FileOutputStream os = new FileOutputStream(excelFile);
			XSSFWorkbook book = new XSSFWorkbook();
			XSSFSheet sheet = book.createSheet("result");
			System.out.println("row size: " + result.size());
			for(int rowCount=0;rowCount<result.size();rowCount++)
			{
				XSSFRow row=sheet.createRow(rowCount);
				for(int colCount=0;colCount<result.get(rowCount).size();colCount++) {
					XSSFCell cell=row.createCell(colCount);
					cell.setCellValue(result.get(rowCount).get(colCount).toString());
					 System.out.println("row: " + rowCount + " - col: " + colCount);
	                 System.out.println(result.get(rowCount).get(colCount).toString());;
				}
			}
			book.write(os);
			os.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** 
     * 判断Excel的版本,获取Workbook 
     * @param in 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static Workbook getWorkbok(InputStream in,File file) throws IOException{  
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
    public static void checkExcelVaild(File file) throws Exception{  
        if(!file.exists()){  
            throw new Exception("文件不存在");  
        }  
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){  
            throw new Exception("文件不是Excel");  
        }  
    }  
    
    @SuppressWarnings("deprecation")
	private static Object getValue(Cell cell) {
    	Object obj = null;
    	switch (cell.getCellTypeEnum()) {
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
	            break;
    	}
    	return obj;
    }

    public static void readXML(String filePath) {
    	try {   
    		     File f = new File(filePath);   
    		     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
    		     DocumentBuilder builder = factory.newDocumentBuilder();   
    		     Document doc = builder.parse(f);   
    		     NodeList nl = doc.getElementsByTagName("step");
    		     
    		     
    		     System.out.println(nl.getLength());
    		    for (int i = 0; i < nl.getLength(); i++) {
    		    	/*
    		    	System.out.println("step:" + i);
    		    	NamedNodeMap nnm=nl.item(i).getAttributes();
    		    	for (int j=0; j<nnm.getLength(); j++) {
    		    		Node n=nnm.item(j);
    		    		System.out.println(n.getNodeName() + ":" + n.getNodeValue());
    		    	}
    		    	
    		      System.out.println("step:"+ i + "===============");   
    		      //System.out.print("step:"+ nl.item(i).getFirstChild().getNodeName()); 
    		       
    		       */
    		    	
    		    	//XMLChild2Map(nl.item(i));
    		    	if(i==0)
    		    	{
    		    		Node2Step(nl.item(i),"name","拒绝");
    		    		System.out.println(HelloWorld.roleCode);
    		    		break;
    		    	}
    		     }   
    		    } catch (Exception e) {   
    		     e.printStackTrace();   
    		    }   
    }
    
    /*
     * function name: checkStepname
     * comments: lookup the step node by the specific step name
     * parameters=>
     * String stepName: step name
     * NodeList xmlResult: the result from reading xml file
     * return=>
     * NamedNodeMap 
     */
    public static NamedNodeMap checkStepName(String stepName, NodeList result) {
    	NamedNodeMap nnm=null;
    	for(int i=0;i<result.getLength();i++) {
    		nnm=result.item(i).getAttributes();
    		for(int j=0;j<nnm.getLength();j++) {
    			
    		}
    	}
    	return nnm;
    }
  
    
    public static void checkResult(ArrayList<ArrayList<Object>> checkPoints, int rowStart, int colStart, NodeList xmlResult) {
    	for(int rowCount=rowStart;rowCount<checkPoints.size();rowCount++)
    	{
    		for (int colCount=colStart;colCount<checkPoints.get(rowCount).size();colCount++) {
    			//check node name, if found check further attributes, if no mark result as failure
    			String stepName=checkPoints.get(rowCount).get(colCount).toString();
    			NamedNodeMap attributeList;
    			Node attribute;
    			String checkingResult="";
    			
    			for(int nodeCount=0;nodeCount<xmlResult.getLength();nodeCount++) {
    				attributeList=xmlResult.item(nodeCount).getAttributes();
    				for(int attCount=0;attCount<attributeList.getLength();attCount++) {
    					attribute=attributeList.item(attCount);
    					if(attribute.getNodeName().equalsIgnoreCase("name") && 
    							attribute.getNodeValue().equalsIgnoreCase("stepname")) {
    							checkingResult="Pass";
    							break;
    					}
    					else
    					{
    						checkingResult="False";
    						break;
    					}
    				}
    					
    			}
    		}
    	}
    	
    }
    
    public static NamedNodeMap XMLChild2Map(Node node) {
    	NodeList nl=node.getChildNodes();
    	for(int i=0;i<nl.getLength();i++) {
    		Node nodetmp=nl.item(i);
    		if(nodetmp.hasChildNodes()) {
    			NamedNodeMap nnm=nodetmp.getAttributes();
    			System.out.println("Node Name:" + nodetmp.getNodeName()+ " Value:" + nodetmp.getNodeValue());
    			if(nnm!=null) {
    				for(int j=0;j<nnm.getLength();j++) {
    					Node node2=nnm.item(j);
    					System.out.println(" Attribute:" + node2.getNodeName()+ " Value:"+node2.getNodeValue());
    				}
    			}
    		XMLChild2Map(nodetmp);
    		}
    		else {
    			if(nodetmp.getNodeName().equals("#text") || nodetmp.getNodeName().equals("#comment")) {
    				if(nodetmp.getNodeValue().toString().trim().length()!=0) {
    					/*
    					char[] c=nodetmp.getNodeValue().toString().toCharArray();
    					for(int ii=0;ii<c.length;ii++) {
    						System.out.println(c[ii]);
    					}*/
    					System.out.println("Key: " + node.getNodeName()+ " text: " +nodetmp.getNodeValue());
    				}
    					
    				continue;
    			}
    				
    			System.out.println("Key:" + nodetmp.getNodeName()+ " Value:"+ nodetmp.getNodeValue());
    			NamedNodeMap nnm=nodetmp.getAttributes();
    			if(nnm!=null) {
    				for(int j=0;j<nnm.getLength();j++) {
    					Node node2=nnm.item(j);
    					System.out.println(" Attribute:" + node2.getNodeName() + " Value:" + node2.getNodeValue());
    				}
    			}
    		}
    	}
    	return null;
    }
    
    private static Step step;
    private List<Step> ls;
    private static String roleCode;
    
    public static Step Node2Step(Node node, String attribute, String value) {
    	Step result;
    	NodeList nl=node.getChildNodes();
    	for(int i=0;i<nl.getLength();i++) {
    		Node nodetmp=nl.item(i);
    		if(nodetmp.hasChildNodes()) {
    			NamedNodeMap nnm=nodetmp.getAttributes();
    			System.out.println("Node name:" + nodetmp.getNodeName()+ " Value:" + nodetmp.getNodeValue());
    			if(nnm!=null) {
    				for(int j=0;j<nnm.getLength();j++) {
    					Node node2=nnm.item(j);
    					String nodeAttribute=node2.getNodeName();
    					String nodeValue=node2.getNodeValue();
    					System.out.println(" Attribute:" + nodeAttribute+ " Value:"+nodeValue);
    					if(nodeAttribute.equals(attribute) && nodeValue.equals(value)) {
    						roleCode=nodetmp.getNodeValue();
    					}
    						
    				}
    			}
    		Node2Step(nodetmp,attribute,value);
    		}
    		else {
    			if(nodetmp.getNodeName().equals("#text") || nodetmp.getNodeName().equals("#comment")) {
    				if(nodetmp.getNodeValue().toString().trim().length()!=0) {
    					System.out.println("Key: " + node.getNodeName()+ " text: " +nodetmp.getNodeValue());
    					NamedNodeMap nnm=node.getAttributes();
    					if(nnm!=null) {
    						for(int j=0;j<nnm.getLength();j++) {
    							Node node2=nnm.item(j);
    							String nodeAttribute=node2.getNodeName();
    							String nodeValue=node2.getNodeValue();
    							System.out.println(" Attribute: " + nodeAttribute + " Value:" + nodeValue);
    							if(nodeAttribute.equals(attribute) && nodeValue.equals(value))
    								roleCode=nodetmp.getNodeValue();
    						}
    					}
    				}
    				continue;
    			}
    			
    			System.out.println("Key:" + nodetmp.getNodeName()+ " Value:"+ nodetmp.getNodeValue());
    			NamedNodeMap nnm=nodetmp.getAttributes();
    			if(nnm!=null) {
    				for(int j=0;j<nnm.getLength();j++) {
    					Node node2=nnm.item(j);
    					String nodeAttribute=node2.getNodeName();
    					String nodeValue=node2.getNodeValue();
    					System.out.println(" Attribute:" + node2.getNodeName() + " Value:" + node2.getNodeValue());
    					if(nodeAttribute.equals(attribute) && nodeValue.equals(value))
    						roleCode=nodetmp.getNodeValue();
    					//else
    						//return null;
    				}
    			
    			}
    		}
    	
    	}
    return null;
    }
    
    public class Step {
    	private String stepName;
    	private String stepMultiple;
    	private String stepMultiple_type;
    	private String stepDispatch;
    	private String actionName;
    	private String argRoleCode;
    	
    	public void setStepName(String stepName) {
    		this.stepName=stepName;
    	}
    	
    	public String getStepName() {
    		return this.stepName;
    	}
    	
    	public void setStepMultiple(String stepMultiple) {
    		this.stepMultiple=stepMultiple;
    	}
    	
    	public String getStepMultiple() {
    		return this.stepMultiple;
    	}
    	
    	public void setStepMultiple_type(String stepMultiple_type) {
    		this.stepMultiple_type=stepMultiple_type;
    	}
    	
    	public String getStepMultiple_type() {
    		return this.stepMultiple_type;
    	}
    	
    	public void setStepDispatch(String stepDispatch) {
    		this.stepDispatch=stepDispatch;
    	}
    	
    	public String getStepDispatch() {
    		return this.stepDispatch;
    	}
    	
    	public void setActionName(String actionName) {
    		this.actionName=actionName;
    	}
    	
    	public String getActionName() {
    		return this.actionName;
    	}
    	
    	public void setArgRoleCode(String argRoleCode) {
    		this.argRoleCode=argRoleCode;
    	}
    	
    	public String getArgRoleCode() {
    		return this.argRoleCode;
    	}
    	
    	public boolean equals(Step step) {
    		return this.stepName.equals(step.getStepName()) && this.stepMultiple.equals(step.getStepMultiple()) &&
    				this.stepMultiple_type.equals(step.getStepMultiple_type()) && 
    				this.stepDispatch.equals(step.getStepDispatch()) &&
    				this.actionName.equals(step.getActionName()) &&
    				this.argRoleCode.equals(step.getArgRoleCode());
    		
    	}
    	
    	public String toString() {
    		return this.stepName + " " + this.stepMultiple + " " + this.stepMultiple_type +
    				" " + this.stepDispatch + " " + this.actionName + " " + this.argRoleCode;
    	}
    }
	
}