package top.simonchou.blog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.simonchou.blog.po.Tag;
import top.simonchou.blog.service.TagService;

import javax.validation.Valid;

/**
 * @基本功能:
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-15 15:00:38
 **/
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    /***
     * @函数功能：分页
     * @param: pageable
     * @param: model
     * @return：java.lang.String
     */
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    //新增
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    //@Vaild校验tag对象
    public String post(@Valid Tag tag,BindingResult result, RedirectAttributes attributes){
        //分类的重复性校验
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该分类不能重复添加");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if(t==null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    //编辑
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    //@Vaild校验tag对象 此方法的参数必须按此顺序
    public String editPost(@Valid Tag tag,BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        //分类的重复性校验
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该分类不能重复添加");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if(t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

}