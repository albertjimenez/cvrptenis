package com.cvrptenis.es.controller;

import com.cvrptenis.es.builder.BuilderChild;
import com.cvrptenis.es.builder.BuilderVan;
import com.cvrptenis.es.model.childs.Child;
import com.cvrptenis.es.model.dao.VRProblem;
import com.cvrptenis.es.model.vehicles.Van;
import com.cvrptenis.es.solver.SolverVRP;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> cvrptenis
 */
@RestController
public class MainRestController {

    private final String JS_IMG = "<img src='data:image/png;base64,";


    @RequestMapping("/")
    @ResponseBody
    @CrossOrigin(origins = "https://albertjimenez.github.io/cvrptenisclient/")
//    @CrossOrigin(origins = "http://localhost:4200")
    String home() {
        return "If you are seeing this, is that the REST API is working! :)";
    }
    @ResponseBody
    @RequestMapping("/api")
    @CrossOrigin(origins = "https://albertjimenez.github.io/cvrptenisclient/")
//    @CrossOrigin(origins = "http://localhost:4200")
    String checkServer() {
        return new Date().toString();
    }


    @RequestMapping(value = "/api/solve", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "https://albertjimenez.github.io/cvrptenisclient/")
//    @CrossOrigin(origins = "http://localhost:4200")
    public String solveVRP(@RequestBody VRProblem vrProblem) {

        System.out.println(vrProblem);
        LinkedList<VehicleImpl> vehicles = new LinkedList<>();
        LinkedList<Service> services = new LinkedList<>();
        vrProblem.getVans().forEach(van -> vehicles.add(van.buildVehicle()));
        vrProblem.getChildren().forEach(child -> services.add(child.buildChild()));

        BufferedImage image = SolverVRP.solveAndPrint(vehicles, services);
        return openFile(image).trim();

    }

    @GetMapping(value = "/api/stub")
    @ResponseBody
    @CrossOrigin(origins = "https://albertjimenez.github.io/cvrptenisclient/")
//    @CrossOrigin(origins = "http://localhost:4200")
    public VRProblem stubVRP() {
        System.out.println("Accessing stub data");
        LinkedList<Van> listaFurgonetas = new LinkedList<>();
        LinkedList<Child> listaNenes = new LinkedList<>();
        final int NUM_VAN = 5;
        final int NUM_CHILD = 8;

        for (int i = 0; i < NUM_VAN; i++)
            listaFurgonetas.add(new BuilderVan().capacity(4).location(Math.random(), Math.random()).id(Integer.toString(i)).build());

        for (int i = 0; i < NUM_CHILD; i++)
            listaNenes.add(new BuilderChild().id(Integer.toString(i)).location(Math.random(), Math.random()).build());
        VRProblem v = new VRProblem(listaFurgonetas, listaNenes);
        return v;

    }

    private String openFile(BufferedImage bufferedImage) {
        String imgstr;
        imgstr = encodeToString(bufferedImage, "png");
        System.out.println(imgstr);
        return imgstr;

    }

    private static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}



