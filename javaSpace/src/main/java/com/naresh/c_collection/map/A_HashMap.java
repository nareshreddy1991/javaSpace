package com.naresh.c_collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Map:(It is the parent interface)
    - HashMap, LinkedhashMap allows null keys
    - TreeMap dont allow nulls



HashMap:
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable

 */
public class A_HashMap {
    public static void main(String[] args) {
        //java 8 methods
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 5);
        map.put("k2", 6);
        Integer x = map.getOrDefault("X", 10);
        //when K, v are not present - insert, return null
        //when k is there v is not - insert, return null
        // when k,v are present then dont insert return old value
        Integer key = map.putIfAbsent("key", 10);
        //used to modify every value in the map
        map.replaceAll((k, v) -> v * 5);//multiply value by 5
        //remove if both key & value are same
        boolean k1 = map.remove("k1", 4);
        boolean k11 = map.remove("k1", 5);
        //
        map.put("k3", 6);
        Integer oldVal = map.replace("k3", 7);
        boolean b = map.replace("k3", 6, 7);//replace when key & val are matching
        /*computeIfAbsent
        computeIfAbsent is used to compute the value of the specified key
            if the value is null
            Or the key is not already associated with value
         */
        //old
        Map<Integer, List<Integer>> mapList = new HashMap<>();
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        for (Integer val : list) {
            List<Integer> list1 = mapList.get(val);
            if (list1 == null) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(val);
                mapList.put(val, tempList);
            } else {
                list1.add(val);
            }
        }
        //new way with computeIfAbsent
        mapList.clear();
        for (Integer val : list) {
            mapList.computeIfAbsent(val, ArrayList::new)//it returns value(ArrayList)
                    .add(val);
        }
        System.out.println(mapList);
        /*
        Important part here is that if the mappingFunction produces a null value then Map is not modified at all.
         Also don’t try to modify Map using mappingFunction. If you are modifying Map using mappingFunction you are using mappingFunction wrong.
         Try using the putIfAbsent method.
         */

        /*TODO computeIfPresent
        computeIfPresent method is used to compute the new mapping given that the key specified in the parameter is present in Map and also value is not null.
        It is paramount to note that if the remappingFunction computes and yields null then the mapping is removed from Map.
        Also, the remappingFunction must not try to modify the Map.
         */
        //oldway
        List<String> words = List.of("A", "A", "B", "C");
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String word : words) {
            if (!wordCounter.containsKey(word))
                wordCounter.put(word, 0);
            wordCounter.put(word, wordCounter.get(word) + 1);
        }
        //new way
        wordCounter.clear();
        for (String word : words) {
            //below logic alone will not work, if result of remapping function is null then no insertion happens to map
            //wordCounter.computeIfPresent(word,(k, v)->v+1); // here v is null so nothing happens no keys will be inserted to map

            if (!wordCounter.containsKey(word)) {
                wordCounter.put(word, 0);
            }
            wordCounter.computeIfPresent(word, (k, v) -> v + 1);
        }
        System.out.println(wordCounter);

        //computeIfAbsent vs putifAbsernt
        Map<String, String> testMap = new HashMap<>();
        testMap.putIfAbsent("k1", "10");
        testMap.computeIfAbsent("k2", v -> v);
        //
        /*TODO compute
        compute() method is used to compute a mapping for the specified key and its value using remappingFunction.
         remappingFunction is a BiFunction which accepts key and value as input and returns new value.
         */
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);
        //3          3     4
        names.compute("Jon", (k, v) -> k.length() + v + "Snow".length());
        names.compute("Bran", (k, v) -> null);// key will be removed if mapping fun returns null
        names.compute("Naresh", (k, v) -> 10); //new record will be inserted
//        names.computeIfPresent("Jon",(k,v)->null); // key will be removed in case of computeIfPresent also
        names.computeIfAbsent("Jon", k -> null);//key will not be removed in case of compureIfAbsent

        System.out.println(names);

        /*
        TODO merge
        merge() method is similar to compute method. It is used to merge the specified value with the value already mapped to specified key in parameter.
         */
        Map<String, Integer> names2 = new HashMap<>();
        names2.put("Bran", 4);
        names2.put("Jon", 3);
        names2.merge("Bran", 10, (v1, v2) -> v1 + v2); // oldVal+ new (4+10)=10
        names2.merge("Jon", 10, (v1, v2) -> null); // if null then key will be removed
        names2.merge("Naresh", 10, (v1, v2) -> v1 + v2); //if key is not exists then it will be added val is 10
        System.out.println(names2);
        System.out.println(map);

        //TODO java9
        Map<String, String> key1 = Map.of("key", "10");
        Map<String, String> stringStringMap = Map.copyOf(key1);

        //iterating
        for (Map.Entry<String, String> stringStringEntry : key1.entrySet()) {

        }
        key1.forEach((k,v)->{

        });


    }
}

