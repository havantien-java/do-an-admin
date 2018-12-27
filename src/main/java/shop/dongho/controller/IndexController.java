package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Product;
import shop.dongho.service.ProductService;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;


    @GetMapping("/index")
    public ModelAndView listProduct(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        return modelAndView;

    }
}
