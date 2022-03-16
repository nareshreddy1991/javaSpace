package com.naresh.m_concurrencyDefogTech;

public class _8A_SingletonDoubleCheckLocking {
}

class EmployeeService {
    //eager loading single is thread safe
//    private static EmployeeService eagerService = new EmployeeService();

    //TODO I never seen volatile in singleton
    private static volatile EmployeeService service = null;//jvm will not reorder the instructions

    private EmployeeService() {
    }

    public EmployeeService getInstance() {
        if (service == null) { //step:3 improving performance because every time synchronizing not a good idea
            synchronized (this) { //step:2 making thread safe
                if (service == null) { //step:1 to check if service is null
                    /* As per java memory  model below statement has three instructions
                        1. Construct empty resource
                        2. Call constructor
                        3. Assign to service
                        But these instruction may not execute in the same order always
                        if the JVM reorder the instructions as 1 3 2 then it may create some issues
                        While jvm is done with  1 3 & not 2,at that time if any thread is checking if(service == null) then that thread will get an empty object
                        To fix this issue, we need to tell jvm not to reorder the statements by adding volatile to service.
                     */
                    service = new EmployeeService();
                }
            }
        }
        return service;
    }
}

//Creating lazy singleton & thread safe using Holder design pattern
class EmployeeDAO {
    private EmployeeDAO() {
    }

    private static class Holder {
        static EmployeeDAO INSTANCE = new EmployeeDAO();
    }

    public EmployeeDAO getInstance() {
        return Holder.INSTANCE;//When called for the first time static class will be initialized & instance will be created
    }
}

//singleton with enum - Lazy loading & thread safe, it will create instance when someone references it for the first time
enum EmpEnum {
    NARESH;

    EmpEnum() {
    }
}