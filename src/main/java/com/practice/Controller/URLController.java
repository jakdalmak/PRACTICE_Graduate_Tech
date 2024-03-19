package com.practice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class URLController {

    @GetMapping("/qwer")
    public String toSite() {

        return "index";
    }

}
