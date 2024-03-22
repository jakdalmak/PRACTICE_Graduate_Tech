package com.practice.graduateWork.Controller;

import com.practice.graduateWork.Domain.NovelDTO;
import com.practice.graduateWork.Service.NovelService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Controller
public class JustController {
    public NovelService novelService;

    public JustController(NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/makePage")
    public String asdf(Model model,
                       @RequestParam("pageName") String pageName) {
        NovelDTO page = novelService.makePage(pageName);
        model.addAttribute("pageInfo", page);

        return "index";
    }


}
