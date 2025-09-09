package org.javacommunity.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author javacommunity.org
 *
 */
public class Java8_ForEach {
    public static void main(String[] args) {

        // create a sample Collection of strings
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add("First");listOfStrings.add("Second");listOfStrings.add("Third");

        // lets traverse our listOfStrings using Iterator
        System.out.println("-------- traversing our listOfStrings using Iterator------------------");
        Iterator<String> strIterator = listOfStrings.iterator();
        while (strIterator.hasNext()) {
            String str = strIterator.next();
            System.out.println("Iterator Value = " + str);
        }

        // now traverse listOfStrings using java8 forEach method of Iterable with anonymous class
        System.out.println("-------- traversing our listOfStrings using java8 forEach method of Iterable with anonymous class ---------");
        listOfStrings.forEach(new Consumer<String>() {
            public void accept(String t) {
                System.out.println("forEach using anonymous class, Value = " + t);
            }
        });

        // now traverse listOfStrings with our Consumer interface implementation
        System.out.println("-------- traversing our listOfStrings using our Consumer interface implementation --------------");
        MyStringListConsumer action = new MyStringListConsumer();
        listOfStrings.forEach(action);
    }
}

//This is Consumer implementation for String variable that can be reused
class MyStringListConsumer implements Consumer<String>{
    public void accept(String t) {
        // Make any formatting changes here
        System.out.println("Consumer impl Value::"+t);
    }
}
