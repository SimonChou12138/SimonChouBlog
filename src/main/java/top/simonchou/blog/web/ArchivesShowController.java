package top.simonchou.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.simonchou.blog.service.BlogService;

/**
 * @基本功能:归档控制层
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-24 19:59:28
 **/
@Controller
public class ArchivesShowController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}