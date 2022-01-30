package com.venkat.java8.stream;

public class Employee {
    private String empId;
    private String empName;
    private Double salary;
    private Integer age;
    private Address address;

    public Employee(String empId, String empName, Double salary, Integer age, Address address) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.age = age;
        this.address = address;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
