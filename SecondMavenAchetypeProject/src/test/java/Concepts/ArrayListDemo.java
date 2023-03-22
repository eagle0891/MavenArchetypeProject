package Concepts;

import org.codehaus.groovy.runtime.ArrayUtil;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ArrayListDemo {

    public static void main(String[] args){
        dynamicArrayListExample();
    }

    //static array
    public static void staticArrayExample() {
        int i[] = new int[4];

        i[0] = 100;
        i[3] = 200;
        i[4] = 500; //outside the size of the static array. This will cause the "array index out of bounds" exception

        System.out.println(i[3]);
    }

    public static void dynamicArrayListExample(){

        //this is raw and not recommended
        ArrayList rawList = new ArrayList();
        rawList.add(1);
        rawList.add(300);
        rawList.add("test");
        rawList.add(2.5);
        System.out.println(rawList);

        //need to add an object type to fix the "raw array" issue
        //default VIRTUAL capacity of dynamic ArrayList is 10 set by Java. Each item in th elist set to null by default until populated by code
        //PHYSICAL capacity is the size of the array written in code
        ArrayList<Object> list = new ArrayList<>(20); //The virtual capacity size can be amended by adding the initialCapacity as value e.g. 20
        list.add(1);
        list.add(300);
        list.add("test");
        list.add(2.5);
        System.out.println("Size of Array is: " + list.size()); //Physical size
        System.out.println(list.get(2));
        list.add(800);
        System.out.println("Size of Array is: " + list.size()); //Physical size
    }

    public static void genericArrayListExample(){
        ArrayList<Integer> genericIntegerList = new ArrayList<>(); //only accepts Integers
        ArrayList<Double> genericDoubleList = new ArrayList<>(); //only accepts Double
        ArrayList<String> genericStringList = new ArrayList<>(); //only accepts Strings
    }

    public static void arrayListIteration(){
        ArrayList<String> list = new ArrayList<>();

        list.add("Tom");
        list.add("Mohammed");
        list.add("jake");
        list.add("Daniel");
        list.add("Ade");
        list.add("Mr Schmidt");

        //Typical for loop
        for (int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }

        //Enhanced for loop equivalent
        for (String s : list) {
            System.out.println(s);
        }
    }

}
