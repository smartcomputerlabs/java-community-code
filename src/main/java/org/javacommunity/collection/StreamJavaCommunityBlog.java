package org.javacommunity.collection;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamJavaCommunityBlog {
    public static void main(String[] args) {
        double sum = 0;

        // create a list
        List<Item> cart = Arrays.asList(new Item("Cosmetic", "Nail Polish",10),
                new Item("Grocery", "Pea Nuts",15.5),
                new Item("Cosmetic", "Eye Shadow",8.5),
                new Item("Cosmetic", "Eye Liner",11.5),
                new Item("Cosmetic", "Eye Pencil",11.5),
                new Item("Cosmetic", "Foundation",10));

        // filter a collection and find sum of prices of filtered collection of items
        sum = cart.stream().filter(item -> item.getType().equals("Cosmetic")).peek(System.out::println).
                collect(Collectors.summingDouble(item -> item.getPrice()));
        System.out.println(sum);

        // filter a collection and find sum of prices of filtered collection of items
        sum = cart.stream().filter(item -> item.getPrice() > 10).collect(Collectors.summingDouble(item -> item.getPrice()));
        System.out.println("sum for >10: "+sum);

        // filter a collection based on price and display names using a terminal function forEach
        cart.stream().filter(item -> item.getPrice() > 10).
                forEach(item -> System.out.println("Price of " + item.getName() + ": " + item.getPrice()));

        // filter prices greater than $10, map the prices to discounted values, and then add them up = (0.1*15.5)+(0.1*11.5)=2.7
        final Double totalOfDiscountedPrices =
                cart.stream()
                        .filter(item -> item.getPrice().compareTo(Double.valueOf(10)) > 0)
                        .map(item -> (item.getPrice()* 0.1))
                        .reduce(0.0, Double::sum);
        System.out.println("Total Discount Earned: " + totalOfDiscountedPrices);

        // filter a collection based on name and display their names
        cart.stream().filter(item -> item.getName().startsWith("Eye")).
                forEach(item -> System.out.println(item.getName()));

        // Total discount if every item is discounted at 20%
        System.out.println(cart.stream().map(item -> (item.getPrice()* 0.20)).reduce(0.0, Double::sum));

        // Total discount if every item is discounted at 20%
        System.out.println(cart.stream().map(item -> (item.getPrice()* 0.20)).reduce(0.0, Double::sum));

        // Sort the item list in ascending order with modifying the existing list
        List<Item> ascendingPrice =
                cart.stream()
                        .sorted((item1, item2) -> item1.getPrice().compareTo(item2.getPrice()))
                        .collect(toList());
        ascendingPrice.stream().forEach(item -> System.out.println(item.getName()));

        // Get the comma separated items names in the cart
        System.out.println(cart.stream().map(item -> item.getName()).
                collect(joining(", ")));

        // Get the item with maximum price in the cart
        final Optional<Item> maximumPrice =
                cart.stream().reduce((item1, item2) -> item1.getPrice() >= item2.getPrice() ? item1 : item2);

        maximumPrice.ifPresent(item ->
                System.out.println(String.format("Highest Price: %s", item.getName())));


        // Get the first record from the stream of a collection
        System.out.println("First value: "+cart.stream().findFirst());

        // Get the maximum price of all the items in a collection
        System.out.println("Maximum price: "+cart.stream().
                max(Comparator.comparing(Item::getPrice)));

        // Get total number of items in a collection
        System.out.println("Total objects in a cart: "+cart.stream().count());

        // Get the name of Item in the cart with name length of 8 characters
        Item itemWith8wordsName =
                cart.stream().filter(item -> item.getName().length() == 10).map(item -> {
                    System.out.println("map() invoked only for: "+item.getName());
                    item.setPrice(item.getPrice() * 0.1);
                    return item;
                }).findFirst().get();
        System.out.println(itemWith8wordsName.getName());

        try (Stream<String> lines = Files.lines(Paths.get("H:/javacommunity/Crime_Data_from_2020_to_Present.csv"))) {
            lines.filter(line -> line.length()>5).forEach(line -> System.out.println(line));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // code before java 8
        for (Item item : cart) {
            if (item.getType().equals("cosmetics")) {
                sum = sum + item.getPrice();
            }
        }
        System.out.println(sum);
    }
}
class Item {
    private int id;
    private String name;
    private String type;
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Item(String type, String name,double price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }
    public String toString(){
        return this.getName();
    }
}