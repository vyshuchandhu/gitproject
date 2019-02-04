package com.coviam.Thread;

import com.coviam.DTO.Employee;
import com.coviam.DataCollection.MyCollection;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWriteThread implements Runnable {
    @Override
    public void run() {
        int index = MyCollection.getReadCounter();
        int tempIndex = MyCollection.getReadCounter();
        do {
            write(MyCollection.getInstance().get());
            index++;
        }while (index<tempIndex+98);

    }

    public void write(Employee emp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileWriter fileCreate = new FileWriter("employeeOutput.json", true);
            JsonGenerator g = mapper.getJsonFactory().createJsonGenerator(fileCreate);
            mapper.writeValue(g, emp);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
