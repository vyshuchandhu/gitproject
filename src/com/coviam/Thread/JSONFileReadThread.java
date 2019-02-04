package com.coviam.Thread;

import com.coviam.DTO.Employee;
import com.coviam.DataCollection.MyCollection;
import com.oracle.javafx.jmx.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JSONFileReadThread implements Runnable {
    private static ArrayList<Employee> empList = new ArrayList<Employee>();
    int index = 0;
    private static JSONParser jsonParser = new JSONParser();
    private static FileReader fileCreator;

    static {
        try {
            fileCreator = new FileReader("employee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object obj;

    static {
        try {
            obj = jsonParser.parse(fileCreator);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray employeeList = (JSONArray) obj;

    public Employee read() throws org.json.simple.parser.ParseException {
        JSONObject jsonObject = null;
        try {


            String jsonString = employeeList.get(index).toString();
            jsonObject = (JSONObject) JSONValue.parse(jsonString);


        } catch (JSONException e) {
            System.out.println(e);

        }
        return getEmployeeObject(jsonObject);


    }

    private static Employee getEmployeeObject(JSONObject employee) {
        Employee employee1 = new Employee();
        try {
            String firstName = (String) employee.get("firstName");
            employee1.setFirstName(firstName);
            String lastName = (String) employee.get("lastName");
            employee1.setLastName(lastName);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(employee.get("dateOfBirth").toString());
            employee1.setDateOfBirth(date1);
            Double experience = Double.parseDouble(employee.get("experience").toString());

            employee1.setTitle(null);
            employee1.setMiddleName(null);
            employee1.setDepartmentName(null);
        } catch (NullPointerException | java.text.ParseException e) {
            System.out.println("Null pointer");
        }
        return employee1;

    }

    @Override
    public void run() {
        for (index = 0; index < employeeList.size(); index++) {
            try {
                MyCollection.addToArrayList(read());
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        try {
            fileCreator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
