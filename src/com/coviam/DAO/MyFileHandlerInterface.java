package com.coviam.DAO;

import com.coviam.DTO.Employee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface MyFileHandlerInterface {
    public void read() throws IOException, ParseException, InterruptedException;
    public void write();
}
