package springLogSecure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerClassic {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/managerisVymysleny")
    public String managerPage(){
        return "managers";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
