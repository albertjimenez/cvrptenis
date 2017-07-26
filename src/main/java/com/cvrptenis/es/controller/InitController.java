package com.cvrptenis.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Beruto on 26/7/17. Project -> cvrptenis
 */
@Controller
public class InitController {

        @RequestMapping("/")
        @ResponseBody
        String home() {
            return "Hello World!";
        }

}
