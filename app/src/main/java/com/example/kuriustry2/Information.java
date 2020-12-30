package com.example.kuriustry2;

public class Information {

    public Information () {}

    private String Address;
    private String Fruits;
    private String Veggies;
    private String Protein;
    private String Dairy;
    private String Cans;
    private String Snacks;
    private String Drinks;
    private String Sweets;
    private String Condiments;

    public Information(String address, String fruits, String veggies, String protein, String dairy, String cans, String snacks, String drinks, String sweets, String condiments) {
        this.Address = address;
        this.Fruits = fruits;
        this.Veggies = veggies;
        this.Protein = protein;
        this.Dairy = dairy;
        this.Cans = cans;
        this.Snacks = snacks;
        this.Drinks = drinks;
        this.Sweets = sweets;
        this.Condiments = condiments;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getFruits() {
        return Fruits;
    }

    public void setFruits(String fruits) {
        this.Fruits = fruits;
    }

    public String getVeggies() {
        return Veggies;
    }

    public void setVeggies(String veggies) {
        this.Veggies = veggies;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        this.Protein = protein;
    }

    public String getDairy() {
        return Dairy;
    }

    public void setDairy(String dairy) {
        this.Dairy = dairy;
    }

    public String getCans() {
        return Cans;
    }

    public void setCans(String cans) {
        this.Cans = cans;
    }

    public String getSnacks() {
        return Snacks;
    }

    public void setSnacks(String snacks) {
        this.Snacks = snacks;
    }

    public String getDrinks() {
        return Drinks;
    }

    public void setDrinks(String drinks) {
        this.Drinks = drinks;
    }

    public String getSweets() {
        return Sweets;
    }

    public void setSweets(String sweets) {
        this.Sweets = sweets;
    }

    public String getCondiments() {
        return Condiments;
    }

    public void setCondiments(String condiments) {
        this.Condiments = condiments;
    }
}