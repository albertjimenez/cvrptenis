package com.cvrptenis.es.builder;


import com.cvrptenis.es.model.vehicles.Van;

/**
 * Created by Beruto on 25/7/17. Project -> VRPTenis
 */
public class BuilderVan {

    private String id;
    private double x, y;
    private int capacity;
    private Double endX, endY;

    public BuilderVan() {
    }

    public BuilderVan id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCapacity() {
        return capacity;
    }

    public BuilderVan capacity(int capacity) {
        this.capacity = capacity;

        return this;
    }

    public BuilderVan location(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public BuilderVan endLocation(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Van build() {
        return new Van(this);
    }

    public Double getEndX() {
        return endX;
    }

    public Double getEndY() {
        return endY;
    }
}
