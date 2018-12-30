package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Producer;
import shop.dongho.model.Product;
import shop.dongho.service.ProducerService;
import shop.dongho.service.ProductService;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;


    @GetMapping("/index")
    public ModelAndView listProduct(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        Page<Producer> producers = producerService.findAll(pageable);
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;

    }

    @GetMapping("choi/{id}")
    public ModelAndView listQQ(@PageableDefault(size = 10) Pageable pageable, @PathVariable Long id) {
        Page<Product> products = productService.findAllByProducer_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }


    @GetMapping("/gia")
    public ModelAndView listProduct(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s, @RequestParam("number") Integer number) {


        Page<Product> unitPriceLessThan = productService.findAllByUnitPriceLessThan(number, pageable);
        Page<Product> products = productService.findAll(pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("unitPriceLessThan", unitPriceLessThan);
        return modelAndView;

    }

    @GetMapping("/list35")
    public ModelAndView hehe(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween(3000000, 5000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/list-product/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id, Pageable pageable) {
        Optional<Product> product = productService.findById(id);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/single");
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }



}
