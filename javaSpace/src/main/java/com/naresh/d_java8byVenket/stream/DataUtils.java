package com.naresh.d_java8byVenket.stream;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<Employee> getEmployeeData() {
    List<Employee> empList= new ArrayList<>();
    empList.add(new Employee("127","Cherry",500d,1,new Address("127","Asalapuram")));
    empList.add(new Employee("123","Naresh",500d,30,new Address("123","Asalapuram")));
    empList.add(new Employee("128","Naresh",500d,20,new Address("128","Asalapuram")));
    empList.add(new Employee("124","Gireesh",500d,29,new Address("124","Asalapuram")));
    empList.add(new Employee("125","Lalitha",500d,28,new Address("125","Asalapuram")));

    return empList;
    }
}
