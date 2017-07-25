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

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public class SolverVRP {

    public static void solveAndPrint(boolean graphicSolution, Collection<VehicleImpl> listaVehiculos, Collection<Service> listaNenes) {
        final String IMG_PATH = "/src/main/resources/output/solution.png";
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addAllVehicles(listaVehiculos);
        vrpBuilder.addAllJobs(listaNenes);
        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
        SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        if (graphicSolution)
            new GraphStreamViewer(problem, bestSolution).setRenderDelay(100).display();
        else
            new Plotter(problem, bestSolution).plot(s + IMG_PATH, "solution");
    }

}
