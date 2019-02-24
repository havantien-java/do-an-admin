package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Producer;
import shop.dongho.model.Product;
import shop.dongho.model.ProductType;
import shop.dongho.model.User;
import shop.dongho.service.ProducerService;
import shop.dongho.service.ProductService;
import shop.dongho.service.ProductTypeService;
import shop.dongho.service.UserService;

@Controller
@RequestMapping("/admin")
public class OverViewController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private UserService userService;
    @GetMapping("/over-view")
    public ModelAndView overView(Pageable pageable) {
        int count = 0, count1 = 0, count2 = 0, count3 = 0;
        Page<Product> products = productService.findAll(pageable);
        for (Product product : products) {
            count++;
        }
        Page<Producer> producers = producerService.findAll(pageable);
        for (Producer producer : producers){
            count1++;
        }
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        for (ProductType productType : productTypes){
            count2++;
        }
        Page<User> users = userService.findAll(pageable);
        for (User user : users){
            count3++;
        }
        ModelAndView modelAndView = new ModelAndView("/overview/view");
        modelAndView.addObject("count", count);
        modelAndView.addObject("count1", count1);
        modelAndView.addObject("count2",count2);
        modelAndView.addObject("count3", count3);

        return modelAndView;
    }
}
