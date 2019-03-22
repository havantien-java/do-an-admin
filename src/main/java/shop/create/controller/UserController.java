package shop.create.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.create.model.User;
import shop.create.service.impl.UserDetailServiceImpl;


@Controller
public class UserController {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView Login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("login/registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView creatNewUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("login/registration");
        userDetailService.saveUser(user);
        modelAndView.addObject("message", "User has been registered successfully");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

}
