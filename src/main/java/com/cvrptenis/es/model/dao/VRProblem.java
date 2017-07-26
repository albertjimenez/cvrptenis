package com.cvrptenis.es.model.dao;

import com.cvrptenis.es.model.childs.Child;
import com.cvrptenis.es.model.vehicles.Van;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Beruto on 26/7/17. Project -> cvrptenis
 */
public class VRProblem implements Serializable{

    private Collection<Van> vans;
    private Collection<Child> children;

    public VRProblem(Collection<Van> vans, Collection<Child> children) {
        this.vans = vans;
        this.children = children;
    }
    public VRProblem(){ }

    @Override
    public String toString() {
        return "VRProblem{" +
                "vans=" + vans +
                ", children=" + children +
                '}';
    }

    public Collection<Van> getVans() {
        return vans;
    }

    public void setVans(Collection<Van> vans) {
        this.vans = vans;
    }

    public Collection<Child> getChildren() {
        return children;
    }

    public void setChildren(Collection<Child> children) {
        this.children = children;
    }
}
