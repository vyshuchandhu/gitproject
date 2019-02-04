package com.coviam.Thread;

import com.coviam.DTO.Employee;
import com.coviam.DataCollection.MyCollection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLFileWriteThread implements Runnable {
    @Override
    public void run() {

    int index =0;
    int tempIndex = MyCollection.getReadCounter();
        do {
            try {
                write(MyCollection.getInstance().get());
                index++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (index<98);
    }
    public void write(Employee employee) throws IOException {
        try {
            FileWriter file = new FileWriter("employeeOutput.xml",true);
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(employee, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
