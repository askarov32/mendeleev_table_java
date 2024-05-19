package main.mendeleyev.table_site.controllers;

import main.mendeleyev.table_site.db.DbManager;
import main.mendeleyev.table_site.models.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String mainPage() {
        return "main";
    }

    @GetMapping(value = "/elements")
    public String elementsPage(Model model) {
        try {
            ArrayList<Element> elements = DbManager.getAllElements();
            model.addAttribute("elements", elements);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return "elements";
    }
}
