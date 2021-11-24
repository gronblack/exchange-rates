package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    private final CurrencyService service;

    public UIController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currencies", service.getCurrencies());
        return "index";
    }
}
