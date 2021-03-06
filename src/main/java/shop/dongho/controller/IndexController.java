package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.*;
import shop.dongho.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/member/home")
    public ModelAndView listProduct(@PageableDefault(size = 5) Pageable pageable,HttpServletRequest request) {
        Page<Product> products  = productService.findAll(pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("giaodien/home");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        return modelAndView;

    }

    @GetMapping("/choi/{id}")
    public ModelAndView listQQ(@PageableDefault(size = 5) Pageable pageable, @PathVariable Integer id,HttpServletRequest request) {
        Page<Product> products = productService.findAllByProducer_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));

        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }


//    @GetMapping("/gia")
//    public ModelAndView listProduct(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s, @RequestParam("number") Integer number) {
//
//
//        Page<Product> unitPriceLessThan = productService.findAllByUnitPriceLessThan(number, pageable);
//        Page<Product> products = productService.findAll(pageable);
//        Page<Producer> producers = producerService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
//        modelAndView.addObject("products", products);
//        modelAndView.addObject("producers", producers);
//        modelAndView.addObject("unitPriceLessThan", unitPriceLessThan);
//        return modelAndView;
//
//    }

    @GetMapping("/list35")
    public ModelAndView hehe(@PageableDefault(size = 5) Pageable pageable,HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(3000000, 4999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));

        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/list-product/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Optional<Product> product = productService.findById(id);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/single");
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        return modelAndView;
    }

    @GetMapping("/list01")
    public ModelAndView list01(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(0, 999999, pageable );
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/list13")
    public ModelAndView list13(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(1000000, 2999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/list51")
    public ModelAndView list51(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(5000000, 9999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/list102")
    public ModelAndView list102(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(10000000, 20000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/product-price200")
    public ModelAndView productPrice200(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween(20000001, 999999999, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/energy/{id}")
    public ModelAndView energy(@PageableDefault(size = 5) Pageable pageable, @PathVariable Integer id, HttpServletRequest request){
        Page<Product> products = productService.findAllByProductType_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(Pageable pageable, HttpServletRequest request) {
        HttpSession session = request.getSession();
      if (session.getAttribute("order") == null ){

          Page<Producer> producers = producerService.findAll(pageable);
          Page<ProductType> productTypes = productTypeService.findAll(pageable);

          ModelAndView modelAndView = new ModelAndView("giaodien/index");

          modelAndView.addObject("producers", producers);
          modelAndView.addObject("productTypes", productTypes);
          modelAndView.addObject("size", getSize(request));
          modelAndView.addObject("message", "Không có sản phảm trong giỏ hàng. Bạn vui lòng về trang chủ để đặt mua sản phẩm");

          return modelAndView;

        } else {
            Page<Product> products = productService.findAll(pageable);
            Page<Producer> producers = producerService.findAll(pageable);
            Page<ProductType> productTypes = productTypeService.findAll(pageable);

            ModelAndView modelAndView = new ModelAndView("giaodien/checkout");
            modelAndView.addObject("products", products);
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("size", getSize(request));

            return modelAndView;
        }
    }

    @GetMapping("/search")
    public ModelAndView search(@PageableDefault(size = 5) Pageable pageable, @ModelAttribute("s") String s, HttpServletRequest request) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("giaodien/index");
        modelAndView.addObject("products", products);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()){
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    public long getSize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long size = 0;
        if (session.getAttribute("order") == null) {
            return size;
        } else {
            Order order = (Order) session.getAttribute("order");
            List<Item> items = order.getItems();
            for (Item item : items) {
                size+= item.getQuantity();
            }
            return size;
        }
    }




}
