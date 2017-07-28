package com.cvrptenis.es.solver;

import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> VRPTenis
 */
public class SolverVRP {

    public static BufferedImage solveAndPrint(Collection<VehicleImpl> listaVehiculos, Collection<Service> listaNenes) {
        Date date = new Date();
//        final String IMG_PATH = "/src/main/resources/static/solution.png";
        final String IMG_PATH_HEROKU = "/src/main/resources/solution.png";
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
        final String PATH = s + IMG_PATH_HEROKU;
        System.out.println("Path available to write: " + PATH);


//        if (graphicSolution)
//            new GraphStreamViewer(problem, bestSolution).setRenderDelay(100).display();
//        else
        return new Plotter(problem, bestSolution).plot(PATH, "solution");
    }

}
