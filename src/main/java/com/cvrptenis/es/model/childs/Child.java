package com.cvrptenis.es.model.childs;

import com.cvrptenis.es.builder.BuilderChild;
import com.cvrptenis.es.interfaces.ChildSelector;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.job.Service;

import java.io.Serializable;

/**
 * Created by Beruto 12/7/17. Project -> VRPTenis
 */

public class Child implements ChildSelector, Serializable {

    private int cost;
    private String id;
    private double x, y;
    private final int WEIGHT_INDEX = 0;

    public Child(BuilderChild builder) {
        this.cost = builder.getCost();
        this.id = builder.getId();
        this.x = builder.getX();
        this.y = builder.getY();
        buildChild();
    }
//Generated getters and setters for JSON serialize
    public Child() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWEIGHT_INDEX() {
        return WEIGHT_INDEX;
    }

    @Override
    public Service buildChild() {
        return Service.Builder.newInstance("child:" + id).addSizeDimension(WEIGHT_INDEX, cost).setLocation(Location.newInstance(x, y)).build();
    }

    @Override
    public String toString() {
        return "Child{" +
                "cost=" + cost +
                ", id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", WEIGHT_INDEX=" + WEIGHT_INDEX +
                '}';
    }
}
