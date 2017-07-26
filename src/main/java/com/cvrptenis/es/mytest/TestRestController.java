package com.cvrptenis.es.mytest;

import com.cvrptenis.es.builder.BuilderChild;
import com.cvrptenis.es.builder.BuilderVan;
import com.cvrptenis.es.model.childs.Child;
import com.cvrptenis.es.model.dao.VRProblem;
import com.cvrptenis.es.model.vehicles.Van;
import com.cvrptenis.es.solver.SolverVRP;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> cvrptenis
 */
@RestController
public class TestRestController {

    private final String JS_IMG = "<img src='data:image/png;base64,";


    @RequestMapping(value = "/api/solve", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String solveVRP(@RequestBody VRProblem vrProblem) {

        System.out.println(vrProblem);
        LinkedList<VehicleImpl> vehicles = new LinkedList<>();
        LinkedList<Service> services = new LinkedList<>();
        vrProblem.getVans().forEach(van -> vehicles.add(van.buildVehicle()));
        vrProblem.getChildren().forEach(child -> services.add(child.buildChild()));

        String path = SolverVRP.solveAndPrint(false, vehicles, services);
        return openFile(path);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "Hi";
    }

    @GetMapping(value = "/api/stub")
    @ResponseBody
    public VRProblem stubVRP() {
        LinkedList<Van> listaFurgonetas = new LinkedList<>();
        LinkedList<Child> listaNenes = new LinkedList<>();
        final int NUM_VAN = 5;
        final int NUM_CHILD = 8;

        for (int i = 0; i < NUM_VAN; i++)
            listaFurgonetas.add(new BuilderVan().capacity(4).location(Math.random(), Math.random()).id(Integer.toString(i)).build());

        for (int i = 0; i < NUM_CHILD; i++)
            listaNenes.add(new BuilderChild().id(Integer.toString(i)).location(Math.random(), Math.random()).build());
        return new VRProblem(listaFurgonetas, listaNenes);

    }

    private String openFile(String path) {
        File file = new File(path);
        try (FileInputStream fis = new FileInputStream(file);) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            final String image = new String(Base64.encodeBase64(bytes), "UTF-8");
            Files.deleteIfExists(file.toPath());
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}



