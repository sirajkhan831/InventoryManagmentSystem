package com.bridgelabz;

public class FoodItem {
    private String name;
    private int weight;
    private int priceKG;

    public FoodItem(String name, int weight, int priceKG) {
        this.name = name;
        this.weight = weight;
        this.priceKG = priceKG;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPriceKG() {
        return priceKG;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPriceKG(int priceKG) {
        this.priceKG = priceKG;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", priceKG=" + priceKG +
                '}';
    }
}
