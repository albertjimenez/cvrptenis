package com.cvrptenis.es.model.vehicles;

import com.cvrptenis.es.builder.BuilderVan;
import com.cvrptenis.es.interfaces.VehicleSelector;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public class Van implements VehicleSelector {

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
        if (endX == null || endY == null){
            endX = x;
            endY = y;
        }
    }


    @Override
    public VehicleImpl buildVehicle() {
        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX, capacity);
        VehicleType vehicleType = vehicleTypeBuilder.build();
        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle:" + id);
        vehicleBuilder.setStartLocation(Location.newInstance(x, y));
        vehicleBuilder.setEndLocation(Location.newInstance(endX,endY));
        vehicleBuilder.setType(vehicleType);
        return vehicleBuilder.build();
    }
}
