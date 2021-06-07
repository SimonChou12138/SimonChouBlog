package top.simonchou.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.simonchou.blog.po.Tag;
import top.simonchou.blog.qo.BlogQuery;
import top.simonchou.blog.service.BlogService;
import top.simonchou.blog.service.TagService;

import java.util.List;

/**
 * @基本功能:标签展示
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-24 17:30:15
 **/
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        //拿到分类
        List<Tag> tags = tagService.listTagTop(100);
        if(id==-1){
            id=tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}