package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.ProductType;
import shop.dongho.service.ProductTypeService;

import java.util.Optional;

@Controller
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("create-productType")
    public ModelAndView showCreateProductType() {
        ModelAndView modelAndView = new ModelAndView("/productType/create");
        modelAndView.addObject("productType", new ProductType());
        return modelAndView;
    }

    @PostMapping("create-productType")
    public ModelAndView saveProductType(@ModelAttribute("productType") ProductType productType) {
    productTypeService.save(productType);
    ModelAndView modelAndView = new ModelAndView("productType/create");
    modelAndView.addObject("productType", productType);
    modelAndView.addObject("message","New productType created successfully");
    return modelAndView;
    }

    @GetMapping("productTypes")
    public ModelAndView showProductType(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<ProductType> productTypes;
        if (s != null) {
            productTypes = productTypeService.findByNameContaining(s, pageable);
        } else {
            productTypes = productTypeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("productType/list");
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;
    }

    @GetMapping("edit-productType/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<ProductType> productType = productTypeService.findById(id);
        if (productType != null) {
            ModelAndView modelAndView = new ModelAndView("/productType/edit");
            modelAndView.addObject("productType", productType);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-productType")
    public ModelAndView updateProductType(@ModelAttribute("productType") ProductType productType) {
        productTypeService.save(productType);
        ModelAndView modelAndView = new ModelAndView("/productType/edit");
        modelAndView.addObject("productType", productType);
        modelAndView.addObject("message","ProductType update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-productType/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<ProductType> productType = productTypeService.findById(id);
        if (productType != null) {
            ModelAndView modelAndView = new ModelAndView("/productType/delete");
            modelAndView.addObject("productType", productType);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-productType")
    public String deleteProductType(@ModelAttribute("productType") ProductType productType){
        productTypeService.remove(productType.getId());
        return "redirect:productTypes";
    }
}
