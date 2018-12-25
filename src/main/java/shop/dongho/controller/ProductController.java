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
import shop.dongho.model.ImageProduct;
import shop.dongho.model.Producer;
import shop.dongho.model.Product;
import shop.dongho.model.ProductType;
import shop.dongho.service.ImageProductService;
import shop.dongho.service.ProducerService;
import shop.dongho.service.ProductService;
import shop.dongho.service.ProductTypeService;

import javax.xml.crypto.Data;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductTypeService productTypeService;

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
    public ModelAndView createProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult, @RequestParam("file-image") MultipartFile avartaImage) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/product/create");
            return modelAndView;
        } else {
            productService.save(product);
//            ModelAndView modelAndView = new ModelAndView("/product/create");
//            modelAndView.addObject("product", product);
//            modelAndView.addObject("message", "Thành Công");
//            return modelAndView;

            return doUpload(avartaImage, product);
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
        modelAndView.addObject("imageProduct", products.get());
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

    @Autowired
    private ImageProductService imageProductService;

    public ModelAndView doUpload (MultipartFile avartaImage, Product product ) {
        uploadAvartaImage(avartaImage, product);
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    private void uploadAvartaImage(MultipartFile file, Product product) {
        File uploadRootDir = new File("F:/image/");

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        String name = file.getOriginalFilename();
        System.out.println("Client File Name = " + name);

        Date date = new Date();

        String url = "";
        url = String.valueOf((name + date.toString()).hashCode());
        ImageProduct image = new ImageProduct(0, url,product);
        imageProductService.save(image);

        if (name != null && name.length() > 0) {
            try {
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + url);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
            } catch (IOException e) {
//                failedFile = "Error Write file: " + name;
            }
        }
    }

//    private void uploadDetailImages(MultipartFile[] files, Product product) {
//        File uploadRootDir = new File("file:///C:/Users/Ha%20Tien/Desktop/DongHo/");
//
//        //Tao thu muc goc neu no khong ton tai
//        if (!uploadRootDir.exists()) {
//            uploadRootDir.mkdirs();
//        }
////        List<File> uploadedFiles = new ArrayList<>();
////        List<String> failedFiles = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            //Ten goc tai Client
//            String name = file.getOriginalFilename();
//            System.out.println("Client File Name = " + name);
//
//            Date date = new Date();
//
//            String url = "";
//            url = String.valueOf((name + date.toString()).hashCode());
//            ImageProduct image = new ImageProduct(1, url, product);
//            imageProductService.save(image);
//
//            if (name != null && name.length() > 0) {
//                try {
//                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + url);
//                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                    stream.write(file.getBytes());
//                    stream.close();
//                    //
////                    uploadedFiles.add(serverFile);
////                    System.out.println("Write file: " + serverFile);
//                } catch (IOException e) {
////                    System.out.println("Error Write file: " + name);
////                    failedFiles.add(name);
//                }
//            }
//        }
//    }

}
