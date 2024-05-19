package main.mendeleyev.table_site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String mainPage(){
        return "main";
    }

    @GetMapping(value = "/elements")
    public String elementsPage(){
        return "elements";
    }
}
