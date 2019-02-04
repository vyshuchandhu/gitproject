package com.coviam.DataCollection;

import com.coviam.DTO.Employee;

//public class MyCollection {
//    private static MyCollection myCollection;
//private MyCollection(){}
//public static MyCollection getInstance(){
//    if(myCollection==null)
//        myCollection= new MyCollection();
//    return myCollection;
//}
//public  void addToArrayList(Employee employee){
//    System.out.println(employee.toString());
//}
//}
import java.util.ArrayList;
public class MyCollection {
    private static  MyCollection myCollection = null;
    private MyCollection(){}
    public static MyCollection getInstance(){
        if(myCollection==null)
            myCollection=new MyCollection();
        return myCollection;
    }
    private static ArrayList<Employee> arrayList=new ArrayList<>(300);
    private static int readCounter=-1;
    private static int writeCounter=-1;
    public static int getReadCounter() {
        return readCounter;
    }
    public static int getWriteCounter() {
        return writeCounter;
    }
    public static void addToArrayList(Employee employee){
        synchronized (MyCollection.class) {
            arrayList.add(employee);
            writeCounter += 1;
        }
    }
    public static synchronized Employee get(){
        readCounter+=1;
        return arrayList.get(readCounter);
    }
}