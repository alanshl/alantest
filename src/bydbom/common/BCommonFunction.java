package bydbom.common;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BCommonFunction {
	private JsonNode node;
	private ObjectMapper mapper=new ObjectMapper();
	public static void main(String[] args)
	{
		BCommonFunction cf=new BCommonFunction();
		//cf.readJasonFile(EnvJsonFile.BASICFILE);
		//cf.getProperty("integration");
		cf.getTimeStamp();
		
	}
	public void readJasonFile(EnvJsonFile ejf) {
		
		try {
			 	String jsonpath;
			 	String jsonname;
			  	jsonname=ejf.getDesc();
			  	jsonpath=this.getProjectPath() + jsonname;
			  	node =mapper.readValue(new File(jsonpath), JsonNode.class); 	
		}
		catch(Exception e) {
			System.out.print(e);
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
		value=node.get(name).toString().replaceAll("\"", "");
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
}

