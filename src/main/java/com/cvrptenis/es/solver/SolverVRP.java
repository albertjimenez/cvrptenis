package com.cvrptenis.es.solver;

import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public class SolverVRP {

    public static String solveAndPrint(boolean graphicSolution, Collection<VehicleImpl> listaVehiculos, Collection<Service> listaNenes) {
        Date date = new Date();
        final String IMG_PATH = "/src/main/resources/static/solution" + date.toString() +".png";
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addAllVehicles(listaVehiculos);
        vrpBuilder.addAllJobs(listaNenes);
        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
        SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);
        Path currentRelativePath = Paths.get("");
        final String s = currentRelativePath.toAbsolutePath().toString();
        final String PATH = s+IMG_PATH;


        if (graphicSolution)
            new GraphStreamViewer(problem, bestSolution).setRenderDelay(100).display();
        else
            new Plotter(problem, bestSolution).plot(s + IMG_PATH, "solution"+date.toString());
        return PATH;
    }

}
