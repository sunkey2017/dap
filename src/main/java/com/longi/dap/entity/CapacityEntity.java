package com.longi.dap.entity;

public class CapacityEntity {

    private String city;

    private String capacity;

    private String type;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CapacityEntity{" +
                "city='" + city + '\'' +
                ", capacity='" + capacity + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
