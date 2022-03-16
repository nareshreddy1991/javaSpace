package com.naresh.d_java8byVenket.stream;

public class Address implements Comparable<Object> {
    private String empId;
    private String address;

    public Address(String empId, String address) {
        this.empId = empId;
        this.address = address;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
