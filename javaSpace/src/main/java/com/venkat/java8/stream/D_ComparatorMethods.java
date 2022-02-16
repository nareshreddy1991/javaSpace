package com.venkat.java8.stream;

import java.util.*;
import java.util.function.Function;

public class D_ComparatorMethods {
    public static void main(String[] args) {
        //before java8
        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            public int compare(Employee h1, Employee h2) {
                return h1.getEmpName().compareTo(h2.getEmpName());//sorting in asc order, for desc revere the statement
            }
        };
        List<Employee> employeeData = DataUtils.getEmployeeData();
        Collections.sort(employeeData, comparator);

        //java8
        Comparator<Employee> comparing = Comparator.comparing(Employee::getEmpName);
        //TODO imp to sort an object in natural order/reverse order that class mush implement Comparable
//        Comparator<Employee> comparing2 = Comparator.<Employee>naturalOrder();
        Collections.sort(employeeData, comparing);//sort in asc
        System.out.println("asc" + employeeData);
        Collections.sort(employeeData, Comparator.comparing(Employee::getEmpName, Comparator.reverseOrder()));//sort in desc
        System.out.println("desc" + employeeData);

        employeeData.sort(Comparator.comparing(Employee::getEmpName));
        employeeData.sort(Comparator.comparing(Employee::getEmpName, Comparator.reverseOrder()));
        //same as above
        employeeData.sort(Comparator.comparing(Employee::getEmpName, (l, r) -> {
            return l.compareTo(r);
        }));
        //sort by name and then by age
        employeeData.sort((l, r) -> {
            if (l.getEmpName().equals(r.getEmpName()))
                return Integer.compare(l.getAge(), r.getAge());
            else
                return l.getEmpName().compareTo(r.getEmpName());
        });
        //same as above
        System.out.println("name asc age asc lambda" + employeeData);
        employeeData.sort(Comparator.comparing(Employee::getEmpName)
                .thenComparing(Comparator.comparing(Employee::getAge)));
        System.out.println("name asc age asc method ref:" + employeeData);

        //TODO nulls in sorting throw NPE
        //So far, we implemented our Comparators in a way that they can't sort collections containing null values. That is,
        // if the collection contains at least one null element, then the sort method throws a NullPointerException:
        /* we can check nulls manually like this
        humans.sort((h1, h2) -> {
        if (h1 == null) {
            return h2 == null ? 0 : 1; //we are pushing all the nulls to end of the collection
        }
        else if (h2 == null) {
            return -1;
        }
        return h1.getName().compareTo(h2.getName());
    });
         */
        employeeData.add(null);//element should be null, getEmpName should have value othewise NPE
        employeeData.sort(Comparator.nullsFirst(Comparator.comparing(Employee::getEmpName)));
        System.out.println("null First:" + employeeData);

        employeeData.remove(null);
        //comparingInt, Double, Long same as comparing
        employeeData.sort(Comparator.comparingInt(Employee::getAge));
        //natural order
        List<Integer> values
                = Arrays.asList(212, 324,
                435, 566,
                133, 100, 121);

        // naturalOrder is a static method
        values.sort(Comparator.naturalOrder());
        values.sort(Comparator.reverseOrder());


        Comparator<Address> addressComparator = Comparator.<Address>naturalOrder();
//        List<Address> addresses = List.of(new Address("125", "address"));
        List<Address> address = Arrays.asList(new Address("125", "address"));
//        address.add(null); // Arrays.asList return fixed size array we can't add/remove new elements but we can sort
        //address.remove(0);
//        System.out.println("size:"+address.size());
        address.sort(addressComparator);//immutable collection can't be sorted throws UnSupportedOperation exception
        //TODO impt diff between immutable vs FIxed/ List.of vs Arrays.asList

        //comparator in streams

        List<String> strList = List.of("naresh", "Lalitha", "Chareesh", "Tinku", "naresh");
        List<String> intList = List.of("10", "20", "30", "40");
        List<Integer> integerList = List.of(60, 10, 20, 30, 40, 50);
        List<Integer> integerList2 = List.of(10, 20, 30, 40, 50, 10, 20, 30, 40, 50, 10, 20, 30, 40, 50);
        Comparator<Integer> c = Comparator.comparing(Function.identity());//user natualOrder
        //sorting, min , max
        Optional<Integer> min = integerList.stream()
                .sorted()
                .sorted(Comparator.naturalOrder())
//                .min(Comparator.naturalOrder());// min is 10
                .min(Comparator.reverseOrder());// min is 60
//                .max(Comparator.naturalOrder());
        System.out.println("min:" + min.get());

        //after sorting what ever is the first value that is min value, last value is  max
        Integer min1 = Collections.min(integerList, Comparator.naturalOrder());//10
//        Integer min1 = Collections.min(integerList, Comparator.reverseOrder());//60
        System.out.println("min1:" + min1);
    }
}
