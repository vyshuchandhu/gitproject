package com.coviam.Thread;

import com.coviam.DTO.Employee;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.coviam.DataCollection.MyCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileReadThread implements Runnable{
    private File fileCreate = new File("employee.xml");
    private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder dbBuilder;
    private MyCollection myCollection= null;
    private int index=0;
    private NodeList nodeList;


    public Employee read() {
        myCollection = myCollection.getInstance();


                return getEmployee(( nodeList.item(index)));


    }



    private static Employee getEmployee(Node node) {
        Employee emp = new Employee();
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            try {
                Element element = (Element) node;
                emp.setFirstName(getTagValue("firstName", element));
                emp.setLastName(getTagValue("lastName", element));
                Date date1;
                String s1 = (getTagValue("dateOfBirth", element));
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
                emp.setDateOfBirth(date1);
                emp.setDepartmentName(null);
                emp.setExperience(Double.parseDouble(getTagValue("experience", element)));
                emp.setMiddleName(null);
                emp.setTitle(null);
            }catch (ParseException e)
            {
                System.out.println(e);
            }


        }
        return emp;
    }
    private static String getTagValue(String tag,Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }


    @Override
    public void run() {
        try {
            dbBuilder=dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc= null;
        try {
            doc = dbBuilder.parse(fileCreate);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        nodeList=doc.getElementsByTagName("employee");
        for(index=0;index<nodeList.getLength();index++)
        {
            myCollection.addToArrayList(read());
        }
        myCollection.get();
    }
}
