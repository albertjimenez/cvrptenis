package com.cvrptenis.es.mytest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Beruto and Pablo Berbel on 12/7/17. Project -> cvrptenis
 */
@RestController
public class TestRestController {

    @RequestMapping("/api/hello")
    public String hola(){
        return "hola";
    }
}
