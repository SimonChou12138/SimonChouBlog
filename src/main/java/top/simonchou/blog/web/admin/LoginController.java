package top.simonchou.blog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.simonchou.blog.po.User;
import top.simonchou.blog.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @基本功能:登录控制器
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-15 11:14:49
 **/

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        System.out.println("---"+username);
        System.out.println("---"+password);
        User user = userService.checkUser(username, password);
        if (user!=null) {
            user.setPassword(null);
            session.setAttribute("user",user);
//            System.out.println("-------进入");
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
//            System.out.println("-------错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}