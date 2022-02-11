package com.naresh.collection.list;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 AbstractCollection(AC) ----> Collection(I)------> Iterable(I)
 AbstractList<E> extends AbstractCollection<E> implements List<E>
 ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable

ArrayList:
    -- initial capacity will be zero(new ArrayList()), if not specified, after adding the first element capacity will be 10
    -- capacity will be increased by 50% when 11th element is beeing added
    -- Every time capacity changes resizing takes place
    -- When elements are removed/cleared capacity is not decreased
    -- Internally it store data in Object[]
    -- Not thread safe, so faster in the performance
    -- we can synchronize list with Collection.syncronizedList()
 */
public class A_ArrayList {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Object> list = new ArrayList<>();//When list is created capacity is zero
//        list.add(null);// when first element is added then capacity  will be 10
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));
        Stream.iterate(0, i -> i < 10000, i -> ++i) //i++ will leads to infinite loop / we can use i=i+1
                .forEach(i -> {
                    System.out.println(i);
                    list.add(i);
                });
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));//after adding 10 elements to list still the capacity is 10
        list.add(1);
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));// capacity 15 increased by 50% percent
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));//capacity 22 increased by <=50% of the current capacity

        ArrayList<Integer> intArray = new ArrayList<>();//This approach many times resizing happens
        long start = System.currentTimeMillis();
        Stream.iterate(0, i -> i < 1000000, i -> ++i)
                .forEach(i -> {
                    intArray.add(i);
                });
        System.out.println("Time Taken to insert:" + (System.currentTimeMillis() - start));
        System.out.println("Size:" + intArray.size() + " Capacity:" + getCapacity(intArray));

        ArrayList<Integer> intArray2 = new ArrayList<>(1000000);// no resizing takes place
        start = System.currentTimeMillis();
        Stream.iterate(0, i -> i < 1000000, i -> ++i)
                .forEach(i -> {
                    intArray2.add(i);
                });
        System.out.println("Time Taken to insert:" + (System.currentTimeMillis() - start));
        System.out.println("Size:" + intArray2.size() + " Capacity:" + getCapacity(intArray2));

        //ensure capacity
        list.ensureCapacity(100);// increase capacity to 100
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));

        //size when removing elements?
        System.out.println("After removing elements capacity changes?");
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));
        Iterator<Object> iterator = list.iterator();
        int i = 10000;
        while (iterator.hasNext()) {
            --i;
            if (i == -8) break;
            iterator.next();
            iterator.remove();
        }
        list.clear();
        System.out.println("Size:" + list.size() + " Capacity:" + getCapacity(list));

        Object clone = list.clone();//its implementing clonable

        //
        System.out.println("Trim to size:");
        System.out.println("Size:" + intArray.size() + " Capacity:" + getCapacity(intArray));
        intArray.trimToSize();// trim the capacity to save the space , it will make size= capacity
        System.out.println("Size:" + intArray.size() + " Capacity:" + getCapacity(intArray));

        //difference
        Iterator<Integer> iterator1 = intArray.iterator();
        Iterator<Integer> iterator2 = intArray.stream().iterator();
        ListIterator<Integer> integerListIterator = intArray.listIterator();
        Spliterator<Integer> spliterator = intArray.spliterator();

        List<Integer> listArray = new ArrayList<>();
        listArray.add(5);
        listArray.add(10);
        listArray.add(20);
        listArray.removeIf(e -> e < 20);//remove all the elements satisfy this condition, internally its iterate the array
        System.out.println("Remove if:" + listArray);

    }

    @SuppressWarnings("unchecked")
    private static int getCapacity(ArrayList list) throws NoSuchFieldException, IllegalAccessException {
        Field elementData = list.getClass().getDeclaredField("elementData");//internally data will be store in Object[]
        elementData.setAccessible(true);
        Object[] array = (Object[]) elementData.get(list);
        return array.length;
    }
}
