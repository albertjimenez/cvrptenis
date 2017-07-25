package com.cvrptenis.es.interfaces;

import com.graphhopper.jsprit.core.problem.job.Service;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public interface ChildSelector {
    public Service buildChild();
}
