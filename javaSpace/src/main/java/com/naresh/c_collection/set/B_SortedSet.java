package com.naresh.c_collection.set;

import java.util.*;

/*
SortedSet:
public interface SortedSet<E> extends Set<E>
    - Maintains sorted order



TreeSet:
public class TreeSet<E> extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, java.io.Serializable

    - Internally use TreeMap
    - no nulls
    - not thread safe, use Collections.synchronizedset()
    - only homogeneous data will be allowed(same type of data) becuase we need to compare the objects
    - if comparator is not passed in the constructor then all the elements will be sorted in natural order
Note: All the elements of a SortedSet must implement the Comparable interface (or be accepted by the specified Comparator)
and all such elements must be mutually comparable. Mutually Comparable simply means that two objects accept each other as the argument
 to their compareTo method.
 ****************
 How does TreeSet work Internally?

TreeSet is basically an implementation of a self-balancing binary search tree like a Red-Black Tree.
Therefore operations like add, remove, and search takes O(log(N)) time. The reason is that in a self-balancing tree,
it is made sure that the height of the tree is always O(log(N)) for all the operations. Therefore, this is considered as one of the
most efficient data structures in order to store the huge sorted data and perform operations on it. However,
 operations like printing N elements in the sorted order take O(N) time.
 */
public class B_SortedSet {
    public static void main(String[] args) {
        SortedSet<Integer> sortedSet = new TreeSet<>();
//        SortedSet<String> sortedSet= new TreeSet<>(Comparator.reverseOrder());
        System.out.println(sortedSet.comparator());//return null if comparator is not given
        sortedSet.add(20);
        sortedSet.add(10);
        sortedSet.add(0);
        sortedSet.add(3);
        sortedSet.first();
        sortedSet.last();
        System.out.println(sortedSet.headSet(10)); //<10
        System.out.println(sortedSet.tailSet(10)); //>=10

        Iterator<Integer> iterator = sortedSet.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next());

        //Emp implements Comparable
        SortedSet<Emp> empSet = new TreeSet<>();
        empSet.add(new Emp("C"));//throws Exception when adding element if Emp is not extending comparable
        empSet.add(new Emp("A"));//if compareTo/equals methods are not implemented then this will not work properly
        empSet.add(new Emp("D"));
        empSet.add(new Emp("B"));
        System.out.println("size:" + empSet.size());
        for (Emp e : empSet)
            System.out.println(e);

        //StringBuffer implements Comparable not comparator
        System.out.println("String buffer");
        Set<StringBuffer> ts = new TreeSet<>();

        // Adding elements to above object
        // using add() method
        ts.add(new StringBuffer("A"));
        ts.add(new StringBuffer("Z"));
        ts.add(new StringBuffer("L"));
        ts.add(new StringBuffer("B"));
        ts.add(new StringBuffer("O"));
        ts.add(new StringBuffer(1));

        // Note: StringBuffer implements Comparable
        // interface

        // Printing the elements
        System.out.println(ts);//still working

        //Emp2 implements Comparator
        System.out.println("emp2 comparator");
//        SortedSet<Emp2> empSet2 = new TreeSet<Emp2>(Comparator.comparing(Emp2::getEmpName)); //this will work
        SortedSet<Emp2> empSet2 = new TreeSet<Emp2>();
        empSet2.add(new Emp2("C"));//throws Exception when adding element if Emp is not extending comparable
        empSet2.add(new Emp2("A"));//if compareTo/equals methods are not implemented then this will not work properly
        empSet2.add(new Emp2("D"));
        empSet2.add(new Emp2("B"));
        System.out.println("size:" + empSet.size());
        for (Emp2 e : empSet2)
            System.out.println(e);

    }
}

class Emp implements Comparable<Emp> {
    private String name;

    public Emp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Emp o) {
        return this.name.compareTo(o.getName());
    }
}

class Emp2 implements Comparator<Emp2> {//tODO TreeSet will not work with Comparator class mush implement Comparable
    private String empName;

    public Emp2(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp2 emp2 = (Emp2) o;
        return Objects.equals(empName, emp2.empName);
    }


    @Override
    public int hashCode() {
        return Objects.hash(empName);
    }

    @Override
    public int compare(Emp2 o1, Emp2 o2) {
        return o1.getEmpName().compareTo(o2.getEmpName());
    }

    @Override
    public String toString() {
        return "Emp2{" +
                "empName='" + empName + '\'' +
                '}';
    }
}