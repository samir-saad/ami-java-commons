package org.ssaad.ami.common.swagger.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@ApiIgnore
public class HomeController {

    //@RequestMapping({"/", "/swagger"})
    @RequestMapping(path = {"/", "/swagger"}, method = {RequestMethod.GET})
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}