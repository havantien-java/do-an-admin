package shop.dongho.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Producer;
import shop.dongho.model.Product;
import shop.dongho.model.ProductType;
import shop.dongho.service.ProducerService;
import shop.dongho.service.ProductService;
import shop.dongho.service.ProductTypeService;
import shop.dongho.storage.StorageService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private final StorageService storageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductTypeService productTypeService;

    public ProductController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ModelAttribute("producers")
    public Page<Producer> producers(Pageable pageable) {
        return producerService.findAll(pageable);
    }

    @ModelAttribute("productTypes")
    public Page<ProductType> productTypes(Pageable pageable) {
        return productTypeService.findAll(pageable);
    }

//    @GetMapping("/admin/")
//    public ModelAndView home() {
//        return new ModelAndView("/product/list");
//    }
    @GetMapping("/create-product")
    public ModelAndView formCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult, @RequestParam("file")MultipartFile file) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/product/create");
            return modelAndView;
        } else {
            if (!file.isEmpty()) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                product.setImage(timeStamp + file.getOriginalFilename());
                storageService.store(file);
            }
            productService.save(product);
            ModelAndView modelAndView = new ModelAndView("/product/create");
            modelAndView.addObject("product", product);
            modelAndView.addObject("message", "Thành Công");
            return modelAndView;
        }

    }

    @GetMapping("/products")
    public ModelAndView listProduct(@PageableDefault(size = 10)Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;

    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message","Thành Công");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:products";
    }

}
