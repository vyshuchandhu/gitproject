package com.coviam.Thread;

import com.coviam.DTO.Employee;
import com.coviam.DataCollection.MyCollection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFilleReadThread implements Runnable{
    private BufferedReader reader = new BufferedReader(new FileReader("employee.csv"));

    // read file line by line
    private String line = null;
    private  Scanner scanner = null;
    private int index = 0;
    private MyCollection myCollection = null;

    public CSVFilleReadThread() throws FileNotFoundException {
    }

    public Employee read() throws IOException, ParseException, InterruptedException {

        myCollection = myCollection.getInstance();
        Employee employee = new Employee();


            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    employee.setFirstName(data);
                else if (index == 1)
                    employee.setLastName(data);
                else if (index == 2)
                    employee.setDateOfBirth(new SimpleDateFormat("MM/DD/YYYY").parse(data));
                else if (index == 3)
                    employee.setExperience(Integer.parseInt(data));
                else
                    System.out.println("invalid data::" + data);
                index++;

            }
            index = 0;

        //close reader
        return employee;

    }



    @Override
    public void run() {
        try {
            while (((line = reader.readLine()) != null)){
                myCollection.addToArrayList(read());

            }
            reader.close();
            myCollection.get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    }

