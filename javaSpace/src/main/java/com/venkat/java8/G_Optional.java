package com.venkat.java8;


import java.util.Optional;

public class G_Optional {
    public static void main(String[] args) throws Exception {
        Integer value = 52;
        Integer value2 = null;
        Optional<Object> empty = Optional.empty();
        empty.isPresent(); //false

        Optional<Integer> integer = Optional.of(10);//throws NPE if value is null
        Integer val1 = integer.get(); //throws NoSuchElementException if value is not present

        Integer integer1 = Optional.ofNullable(value).orElse(1);
        Integer integer2 = Optional.ofNullable(value).orElseGet(() -> {//orElseGet will be lazy evaluated
            System.out.println("orElseget");
            return 10;
        });
        Integer integer3 = Optional.ofNullable(value).orElseThrow(RuntimeException::new);
        Integer integer4 = Optional.ofNullable(value).orElseThrow(() -> new RuntimeException());
//        Integer integer5 = Optional.ofNullable(value).orElseThrow(()->{throw new RuntimeException();}); //TODO this having some trouble

        //TODO map & filter
        String number = "25";
        boolean present = Optional.of(number)
                .map(Integer::valueOf)
                .filter(e -> e > 20)
                .filter(e -> e > 30)
                .isPresent();
        //TODO flatMap
        Optional<Integer> integerOptional = Optional.of(25);
        Optional<Optional<Integer>> integerOptional1 = Optional.of(integerOptional);
        Optional<Integer> integerOptional2 = integerOptional1.flatMap(e -> e);//flatted
        //getting person name
        Optional<Person> po = Optional.of(new Person("naresh"));
        Optional<Optional<String>> po2 = po.map(Person::getName);
        Optional<String> po3 = po2.orElse(null);
        String name = po3.orElse(null);
        //getting person name
        Person person = po.orElse(null);
        person.getName();
        //using flatMap
        Optional<String> nameOp = po.flatMap(Person::getName);
        String name2 = nameOp.orElse(null);

        /*
        Optional is meant to be used as a return type. Trying to use it as a field type is not recommended.
        using Optional in a serializable class will result in a NotSerializableException
         */


    }
}

class Person {
    String name = "naresh";

    public Person(String name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
