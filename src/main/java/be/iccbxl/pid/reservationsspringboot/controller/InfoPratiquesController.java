package be.iccbxl.pid.reservationsspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoPratiquesController {
    @GetMapping("/infos-pratiques")
    public String infosPratiques(Model model) {
        model.addAttribute("module", "infos");
        return "info/infos-pratiques";
    }
}
