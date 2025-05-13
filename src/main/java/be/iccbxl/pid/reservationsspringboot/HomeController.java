package be.iccbxl.pid.reservationsspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/dev/")
    public String home() {
        return "/home";
    }
}
