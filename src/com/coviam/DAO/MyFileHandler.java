package com.coviam.DAO;

import com.coviam.Thread.*;
import java.io.IOException;
import java.text.ParseException;

public class MyFileHandler implements MyFileHandlerInterface{

    @Override
    public void read() throws IOException, ParseException, InterruptedException {
        Thread threadXML = new Thread(new XMLFileReadThread());
        threadXML.start();
        Thread threadCSV = new Thread(new CSVFilleReadThread());
        threadCSV.start();
        Thread threadJSON = new Thread(new JSONFileReadThread());
        threadJSON.start();
        threadCSV.join();
        threadXML.join();
        threadJSON.join();
    }

    @Override
    public void write( ) {

        Thread threadCSV  = new Thread(new CSVFileWriteThread());
        Thread threadXML = new Thread(new XMLFileWriteThread());
        Thread threadJSON = new Thread(new JSONFileWriteThread());
        threadXML.start();
        threadCSV.start();
        threadJSON.start();


    }
}
