package com.cvrptenis.es.builder;


import com.cvrptenis.es.model.childs.Child;

/**
 * Created by Beruto and Pablo Berbel on 25/7/17. Project -> VRPTenis
 */

public class BuilderChild {


    private Integer cost = 1;
    private String id;
    private double x, y;

    public BuilderChild() {
    }

    public BuilderChild cost(int cost) {
        this.cost = cost;
        return this;
    }

    public BuilderChild id(String id) {
        this.id = id;
        return this;
    }

    public BuilderChild location(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Child build() {
        return new Child(this);
    }

    public int getCost() {
        if (cost != null)
            return cost;
        return 1;
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
}
