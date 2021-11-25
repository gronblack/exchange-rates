package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.service.ExchangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    private final ExchangeService service;

    public UIController(ExchangeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currencies", service.getCurrencies());
        return "index";
    }
}
