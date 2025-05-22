package io.dev.lecture.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("baseUrl", "http://localhost:8080/v1/lecture");
        return "index"; // templates/index.html
    }
}
