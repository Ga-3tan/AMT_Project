package com.example.amtech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebAppController {

    @GetMapping("/")
    public String homepage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/about")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "about";
    }
    @GetMapping("/blog")
    public String blog(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "blog";
    }
    @GetMapping("/blog-details")
    public String blogDetails(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "blog-details";
    }
    @GetMapping("/contact")
    public String contact(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "contact";
    }
    @GetMapping("/portfolio-details")
    public String porfolioDetails(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "portfolio-details";
    }

    @GetMapping("/portfolio-masonry-grid")
    public String porfolioGrid(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "portfolio-masonry-grid";
    }

    @GetMapping("/pricing")
    public String pricing(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "pricing";
    }

    @GetMapping("/process")
    public String process(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "process";
    }

    @GetMapping("/services")
    public String services(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "services";
    }
    @GetMapping("/sign-in")
    public String signIn(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "sign-in";
    }
    @GetMapping("/sign-up")
    public String signUp(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "sign-up";
    }
    @GetMapping("/team")
    public String team(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "team";
    }
    @GetMapping("/404-error")
    public String error(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "404-error";
    }
}
