package com.longi.dap.entity;

import org.springframework.stereotype.Repository;

@Repository
public class MarketShareEntity {
    private String country;
    private String total;
    private String ours;
    private Double rate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOurs() {
        return ours;
    }

    public void setOurs(String ours) {
        this.ours = ours;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MarketShareEntity{" +
                "country='" + country + '\'' +
                ", total='" + total + '\'' +
                ", ours='" + ours + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
