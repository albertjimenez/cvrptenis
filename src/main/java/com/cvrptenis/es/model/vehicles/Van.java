package com.cvrptenis.es.model.vehicles;

import com.cvrptenis.es.builder.BuilderVan;
import com.cvrptenis.es.interfaces.VehicleSelector;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;

import java.io.Serializable;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public class Van implements VehicleSelector, Serializable {

    private int capacity;
    private String id;
    private final int WEIGHT_INDEX = 0;
    private double x, y;
    private Double endX, endY;

    public Van(BuilderVan builder) {
        this.capacity = builder.getCapacity();
        this.id = builder.getId();
        this.x = builder.getX();
        this.y = builder.getY();
        if (endX == null || endY == null) {
            endX = x;
            endY = y;
        }
    }

    public Van() {
    }

    @Override
    public String toString() {
        return "Van{" +
                "capacity=" + capacity +
                ", id='" + id + '\'' +
                ", WEIGHT_INDEX=" + WEIGHT_INDEX +
                ", x=" + x +
                ", y=" + y +
                ", endX=" + endX +
                ", endY=" + endY +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWEIGHT_INDEX() {
        return WEIGHT_INDEX;
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

    public Double getEndX() {
        return endX;
    }

    public void setEndX(Double endX) {
        this.endX = endX;
    }

    public Double getEndY() {
        return endY;
    }

    public void setEndY(Double endY) {
        this.endY = endY;
    }

    @Override
    public VehicleImpl buildVehicle() {
        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(0, capacity);
        VehicleType vehicleType = vehicleTypeBuilder.build();
        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle:" + id);
        vehicleBuilder.setStartLocation(Location.newInstance(x, y));
        if (endX != null && endY != null)
            vehicleBuilder.setEndLocation(Location.newInstance(endX, endY));

        vehicleBuilder.setType(vehicleType);
        return vehicleBuilder.build();
    }
}
