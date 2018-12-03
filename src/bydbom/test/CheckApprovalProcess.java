package bydbom.test;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bydbom.common.BCommonFunction;
import bydbom.common.EnvJsonFile;
import bydbom.common.ProcessNode;

import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;

public class CheckApprovalProcess {
  @Test
  public void checkApprovalProcess() {
	  BCommonFunction bcf=new BCommonFunction();
	  bcf.readJasonFile(EnvJsonFile.TESTFILE);
	  String XMLFilePath=bcf.getProperty("ProcessXMLPath");
	  Document doc=bcf.readXML(XMLFilePath);
	  ArrayList<ProcessNode> listPN=XML2ProcessNodeList(doc);
	  System.out.println(listPN.size());
	  for(int i=0;i<listPN.size();i++) {
		  ProcessNode pn=listPN.get(i);
		  System.out.println(pn);
	  }
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  
  
  public ArrayList<ProcessNode> XML2ProcessNodeList(Document doc){
	  NodeList nl = doc.getElementsByTagName("step");
	  ArrayList<ProcessNode> listPN = new ArrayList<>();
	  System.out.println(nl.getLength());
	  for (int i = 0; i < nl.getLength(); i++) {
		  ProcessNode processNode=new ProcessNode();
		  NamedNodeMap namedNodeMap=nl.item(i).getAttributes();
		  if(namedNodeMap!=null) {
			  for(int j=0;j<namedNodeMap.getLength();j++) {
				  Node node=namedNodeMap.item(j);
				  switch(node.getNodeName()){
					  case "name":
						  processNode.setStepName(node.getNodeValue());
						  break;
					  case "multiple":
						  processNode.setStepMultiple(node.getNodeValue());
						  break;
					  case "multiple-type":
						  processNode.setStepMultiple_type(node.getNodeValue());
						  break;
					  case "dispatch":
						  processNode.setStepDispatch(node.getNodeValue());
				  }
			  }
		  }
		  
		  this.nodeResult=null;
		  GoThroughTreeNodes(nl.item(i),"arg","name","roleCode","V");
		  processNode.setArgRoleCode(this.nodeResult);
		  this.nodeResult=null;
		  GoThroughTreeNodes(nl.item(i),"action","name","¾Ü¾ø","N");
		  processNode.setActionName(this.nodeResult);
		  
		  listPN.add(processNode);
	    }
	  return listPN;
  } 
  
  public String nodeResult;
  /**
   * Go through the entire tree to find the node which attribute and value meet the parameters
   * @param node
   * @param attribute
   * @param value
   */
  private void GoThroughTreeNodes(Node node, String nodeName, String attribute, String value, String dataMode) {
  	NodeList nodeList=node.getChildNodes();
  	for(int i=0;i<nodeList.getLength();i++) {
  		Node nodetmp=nodeList.item(i);
  		if(nodetmp.hasChildNodes()) {
  			NamedNodeMap namedNodeMap=nodetmp.getAttributes();
  			System.out.println("Node name:" + nodetmp.getNodeName()+ " Value:" + nodetmp.getNodeValue());
  			if(namedNodeMap!=null) {
  				for(int j=0;j<namedNodeMap.getLength();j++) {
  					Node node2=namedNodeMap.item(j);
  					String nodeAttribute=node2.getNodeName();
  					String nodeValue=node2.getNodeValue();
  					System.out.println(" Attribute:" + nodeAttribute+ " Value:"+nodeValue);
  					if(nodeName.equals(nodetmp.getNodeName()) && nodeAttribute.equals(attribute) && nodeValue.equals(value)) {
  						switch(dataMode) {
  						case "N":
  							this.nodeResult=nodetmp.getNodeName();
  							break;
  						case "V":
  							this.nodeResult=nodetmp.getNodeValue();
  							break;
  						}
  						
  					}
  						
  				}
  			}
  			GoThroughTreeNodes(nodetmp,nodeName,attribute,value,dataMode);
  		}
  		else {
  			if(nodetmp.getNodeName().equals("#text") || nodetmp.getNodeName().equals("#comment")) {
  				if(nodetmp.getNodeValue().toString().trim().length()!=0) {
  					System.out.println("Key: " + node.getNodeName()+ " text: " +nodetmp.getNodeValue());
  					NamedNodeMap namedNodeMap=node.getAttributes();
  					if(namedNodeMap!=null) {
  						for(int j=0;j<namedNodeMap.getLength();j++) {
  							Node node2=namedNodeMap.item(j);
  							String nodeAttribute=node2.getNodeName();
  							String nodeValue=node2.getNodeValue();
  							System.out.println(" Attribute: " + nodeAttribute + " Value:" + nodeValue);
  							if(nodeName.equals(node.getNodeName()) && nodeAttribute.equals(attribute) && nodeValue.equals(value))
  								switch(dataMode) {
  		  						case "N":
  		  							this.nodeResult=node.getNodeName();
  		  							break;
  		  						case "V":
  		  							this.nodeResult=nodetmp.getNodeValue();
  		  							break;
  		  						}
  						}
  					}
  				}
  				continue;
  			}
  			
  			System.out.println("Key:" + nodetmp.getNodeName()+ " Value:"+ nodetmp.getNodeValue());
  			NamedNodeMap namedNodeMap=nodetmp.getAttributes();
  			if(namedNodeMap!=null) {
  				for(int j=0;j<namedNodeMap.getLength();j++) {
  					Node node2=namedNodeMap.item(j);
  					String nodeAttribute=node2.getNodeName();
  					String nodeValue=node2.getNodeValue();
  					System.out.println(" Attribute:" + node2.getNodeName() + " Value:" + node2.getNodeValue());
  					if(nodeName.equals(nodetmp.getNodeName()) && nodeAttribute.equals(attribute) && nodeValue.equals(value))
  						switch(dataMode) {
  						case "N":
  							this.nodeResult=nodetmp.getNodeName();
  							break;
  						case "V":
  							this.nodeResult=nodetmp.getNodeValue();
  							break;
  						}
  				}
  			
  			}
  		}
  	}
  }
  
}
