package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        Integer one = 1;
        Integer two = 2;
        model.put("variable", "My Thymeleaf variable");
        model.put("firstOperation", two + " * " + two + " = ");
        model.put("secondOperation", two + " * " + two + " + " + two + " = ");
        model.put("thirdOperation", two + " - " + two + " * " + two + " = ");
        model.put("one", one);
        model.put("two", two);
        return "index";
    }
}
