package com.bridgelabz;

import com.google.gson.Gson;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Inventory {
    static Gson gson = new Gson();
    static int orderTotal = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<FoodItem> foodItems = new LinkedList<>();
        fileRead(foodItems);
        System.out.println("Welcome..");
        while (true) {
            System.out.print("To add items to your cart press 1 To exit press 2 : ");
            if (new Scanner(System.in).nextInt() == 1) {
                cart(foodItems);
                fileWrite(foodItems.get(0), foodItems.get(1), foodItems.get(2));
            } else break;
        }
        System.out.println("Order Total -> " + orderTotal + " Pay at exit");
    }

    public static void fileRead(LinkedList<FoodItem> foodItems) throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader("Wheat.json");
        FileReader fileReader1 = new FileReader("Rice.json");
        FileReader fileReader2 = new FileReader("Pulse.json");
        BufferedReader br = new BufferedReader(fileReader);
        BufferedReader br1 = new BufferedReader(fileReader1);
        BufferedReader br2 = new BufferedReader(fileReader2);
        FoodItem Wheat1 = gson.fromJson(br, FoodItem.class);
        FoodItem Rice1 = gson.fromJson(br1, FoodItem.class);
        FoodItem Pulse1 = gson.fromJson(br2, FoodItem.class);
        foodItems.add(Wheat1);
        foodItems.add(Rice1);
        foodItems.add(Pulse1);
    }

    public static void fileWrite(FoodItem Wheat, FoodItem Rice, FoodItem Pulse) throws IOException {
        String js = gson.toJson(Wheat);
        String js1 = gson.toJson(Rice);
        String js2 = gson.toJson(Pulse);
        FileWriter fr = new FileWriter("Wheat.json");
        FileWriter fr1 = new FileWriter("Rice.json");
        FileWriter fr2 = new FileWriter("Pulse.json");
        fr.write(js);
        fr1.write(js1);
        fr2.write(js2);
        fr.close();
        fr1.close();
        fr2.close();
    }

    public static void cart(LinkedList<FoodItem> foodItems) throws InterruptedException {
        System.out.println("Available food items in inventory are : ");
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getWeight() > 0) {
                System.out.println("-> " + foodItem.getName() + " (" + foodItem.getWeight() + " KGs)");
            }
        }
        Thread.sleep(1000);
        System.out.print("Enter the name of food item to add to the cart : ");
        String nameScan = new Scanner(System.in).nextLine();
        boolean flag = true;
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getName().equals(nameScan)) {
                flag = false;
                System.out.print("Enter quantity in KGs: ");
                int quantity = new Scanner(System.in).nextInt();
                if (foodItem.getWeight() >= quantity) {
                    foodItem.setWeight(foodItem.getWeight() - quantity);
                    System.out.println("Food item successfully added.");
                    orderTotal += foodItem.getPriceKG() * quantity;
                    Thread.sleep(500);
                } else {
                    System.out.println("\nQuantity can not be greater than available storage.");
                    Thread.sleep(2000);
                }
                System.out.println("Remaining quantity of " + foodItem.getName() + " is " + foodItem.getWeight() + " KG.\n");
                Thread.sleep(500);
            }
        }
        if (flag) {
            System.out.println("Wrong food name");
            System.out.println("Please enter correct food name.\n");
        }
    }
}