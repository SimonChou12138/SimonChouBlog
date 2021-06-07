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
import top.simonchou.blog.po.Type;
import top.simonchou.blog.service.TypeService;

import javax.validation.Valid;

/**
 * @基本功能:
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-15 15:00:38
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    /***
     * @函数功能：分页
     * @param: pageable
     * @param: model
     * @return：java.lang.String
     */
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    //新增
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    //@Vaild校验type对象
    public String post(@Valid Type type,BindingResult result, RedirectAttributes attributes){
        //分类的重复性校验
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name","nameError","该分类不能重复添加");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if(t==null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    //编辑
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    //@Vaild校验type对象 此方法的参数必须按此顺序
    public String editPost(@Valid Type type,BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        //分类的重复性校验
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name","nameError","该分类不能重复添加");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if(t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

}