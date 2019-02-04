package com.coviam.Thread;

import com.coviam.DTO.Employee;
import com.coviam.DataCollection.MyCollection;

import java.io.*;
import java.util.Date;

public class CSVFileWriteThread implements Runnable{
    public void write(Employee employee) throws IOException
    {
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        Date dob = employee.getDateOfBirth();
        Double exp = employee.getExperience();

        try {
            FileWriter fileWriter = new FileWriter("employeeOutput.csv",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(firstName);
            stringBuilder.append(',');
            stringBuilder.append(lastName);
            stringBuilder.append(',');
            stringBuilder.append(dob.toString());
            stringBuilder.append(',');
            stringBuilder.append(exp.toString());
            stringBuilder.append('\n');

            printWriter.println(stringBuilder.toString());
            printWriter.close();
        } catch (IOException exceptionWrite) {
            exceptionWrite.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int index = 0;index<=99;index++){
            try {
                write(MyCollection.getInstance().get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
