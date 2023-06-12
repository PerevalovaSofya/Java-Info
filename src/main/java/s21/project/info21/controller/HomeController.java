package s21.project.info21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/main")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/")
    public String mainPageTwo() {
        return "home";
    }

    @GetMapping("/alltables")
    public String allTables() {
        return "alltables";
    }

    @GetMapping("/procedure")
    public String allProcedures() {
        return "procedures";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }
}
