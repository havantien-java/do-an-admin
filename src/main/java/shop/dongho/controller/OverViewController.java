package shop.dongho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class OverViewController {
    @GetMapping("/over-view")
    public ModelAndView overView() {
        return new ModelAndView("/overview/list");
    }
}
