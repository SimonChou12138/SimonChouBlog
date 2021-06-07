package top.simonchou.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @基本功能:
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-24 20:34:03
 **/
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}