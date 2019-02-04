package com.coviam.Controller;

import com.coviam.DAO.MyFileHandler;
import com.coviam.DAO.MyFileHandlerInterface;
import com.coviam.Thread.CSVFilleReadThread;
import com.coviam.Thread.XMLFileReadThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class MyController {
    public static void main(String[] args) throws FileNotFoundException {
        deleteExistingFiles();
        MyFileHandlerInterface myFileHandler = new MyFileHandler();
        try {
            myFileHandler.read();
            myFileHandler.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void deleteExistingFiles(){
        File file = new File("employeeOutput.xml");
        File file1 = new File("employeeOutput.csv");
        File file2 = new File("employeeOutput.json");
        file.delete();
        file1.delete();
        file2.delete();
    }
}
