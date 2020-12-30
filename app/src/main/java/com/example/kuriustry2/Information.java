package com.example.kuriustry2;

public class Information {

    public Information () {}

    private String Address;
    private String Inventory;

    public Information(String address, String inventory) {
        this.Address = address;
        this.Inventory = inventory;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getInventory() {
        return Inventory;
    }

    public void setInventory(String inventory) {
        this.Inventory = inventory;
    }
}