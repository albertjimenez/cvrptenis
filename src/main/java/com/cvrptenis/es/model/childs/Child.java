package com.cvrptenis.es.model.childs;

import com.cvrptenis.es.builder.BuilderChild;
import com.cvrptenis.es.interfaces.ChildSelector;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.job.Service;

/**
 * Created by Beruto 12/7/17. Project -> VRPTenis
 */
public class Child implements ChildSelector {

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


    @Override
    public Service buildChild() {
        return Service.Builder.newInstance("child:" + id).addSizeDimension(WEIGHT_INDEX, cost).setLocation(Location.newInstance(x, y)).build();
    }
}
