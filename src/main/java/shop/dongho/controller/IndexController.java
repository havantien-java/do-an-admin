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
import shop.dongho.model.ProductType;
import shop.dongho.service.ProducerService;
import shop.dongho.service.ProductService;
import shop.dongho.service.ProductTypeService;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductTypeService productTypeService;


    @GetMapping("/home")
    public ModelAndView listProduct(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/giaodien/home");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;

    }

    @GetMapping("/choi/{id}")
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
        Page<Product> products = productService.findAllByUnitPriceBetween(3000000, 4999999, pageable);
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

    @GetMapping("/list01")
    public ModelAndView list01(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween(0, 999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/list13")
    public ModelAndView list13(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween(1000000, 2999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/list51")
    public ModelAndView list51(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween(5000000, 9999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/list102")
    public ModelAndView list102(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween(10000000, 20000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/energy/{id}")
    public ModelAndView energy(@PageableDefault(size = 10) Pageable pageable, @PathVariable Long id){
        Page<Product> products = productService.findAllByProductType_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        return new ModelAndView("/giaodien/checkout");
    }

    @GetMapping("/search")
    public ModelAndView search(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("giaodien/home");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
