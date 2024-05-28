package main.mendeleyev.table_site.controllers;

import main.mendeleyev.table_site.db.DbManager;
import main.mendeleyev.table_site.models.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String home() {
        return "main";
    }

    @GetMapping(value = "/elements")
    public String elementsPage(Model model) {
        try {
            ArrayList<Element> elements = DbManager.getAllElements();
            if (elements.isEmpty()) {
                System.out.println("No elements found in the database.");
            }
            model.addAttribute("elements", elements);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return "elements";
    }
    @GetMapping(value = "/admin")
    public String admin(Model model){
        try {
            ArrayList<Element> elements = DbManager.getAllElements();
            if (elements.isEmpty()) {
                System.out.println("No elements found in the database.");
            }
            model.addAttribute("elements", elements);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return "admin";
    }

    @GetMapping(value = "/add-element-page")
    public String addElementPage() {
        return "add-element-page";
    }

    @GetMapping(value = "/details-element/{id}")
    public String getElementById(Model model, @PathVariable int id) throws SQLException, IOException {
        model.addAttribute("element", DbManager.getElementById(id));
        System.out.println(DbManager.getElementById(id).getName());
        return "details-element";
    }

    @PostMapping(value = "/add-element")
    public String addElement(Element element) {
        DbManager.addElement(element);
        return "redirect:/admin";
    }

    @PostMapping(value = "/update-element")
    public String updateElement(Element element){
        DbManager.updateElement(element);
        System.out.println(element.toString());
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete-element")
    public String deleteElement(@RequestParam int id) {
        System.out.println("Attempting to delete element with ID: " + id);
        DbManager.deleteElement(id);
        return "redirect:/admin";
    }

}
