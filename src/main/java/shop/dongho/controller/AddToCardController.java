package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.*;
import shop.dongho.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AddToCardController {

    private CustomerService customerService;
    private OrderService orderService;
    private ItemService itemService;
    private ProductService productService;
    private ProducerService producerService;
    private ProductTypeService productTypeService;


    @Autowired
    public void setUpController(ProductService productService,
                                OrderService orderService,
                                ItemService itemService,
                                ProducerService producerService,
                                ProductTypeService productTypeService,
                                CustomerService customerService) {
        this.productService = productService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.itemService = itemService;
        this.producerService = producerService;
        this.productTypeService = productTypeService;
    }

//    @Autowired
//    private OrderDetailsService orderDetailsService;


//    @GetMapping("/")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView("home");
//        return modelAndView;
//    }
//
////    @GetMapping("/list")
////    public ModelAndView list(Pageable pageable) {
////        Page<Product> products = productService.findAll(pageable);
////        ModelAndView modelAndView = new ModelAndView("list");
////        modelAndView.addObject("products", products);
////        return modelAndView;
////    }
//
//    @GetMapping("/addtocard/{id}")
//    public ModelAndView addToCard(@PathVariable("id") Integer id, HttpServletRequest request) {
//        long quantity = 1;
//        Optional<Product> product = productService.findById(id);
//        HttpSession session = request.getSession();
//        if (session.getAttribute("order") == null) {
//            Order order = new Order();
//            List<Item> items = new ArrayList<Item>();
//            Item item = new Item();
//            item.setId(id);
//            item.setProduct(product.get());
//            item.setQuantity(quantity);
//            item.setPrice(product.get().getUnitPrice());
//            items.add(item);
//            order.setItems(items);
//            session.setAttribute("order", order);
//        } else {
//            Order order = (Order) session.getAttribute("order");
//            List<Item> items = order.getItems();
//            boolean check = false;
//            for (Item item : items) {
//                if (item.getProduct().getId() == product.get().getId()) {
//                    item.setQuantity(item.getQuantity() + 1);
//                    check = true;
//                }
//            }
//            if (check == false) {
//                Item item = new Item();
//                item.setId(id);
//                item.setQuantity(quantity);
//                item.setPrice(product.get().getUnitPrice());
//                item.setProduct(product.get());
//                items.add(item);
//            }
//            session.setAttribute("order", order);
//
//
//
//        }
//        ModelAndView modelAndView = new ModelAndView("redirect:/member/home");
//        modelAndView.addObject("session", session);
//        return modelAndView;
//    }
//
//
//
//    @PostMapping("/save-order")
//    public ModelAndView saveOrder(HttpServletRequest request,@ModelAttribute Customer customer) {
//        HttpSession session = request.getSession();
//        Order order = (Order) session.getAttribute("order");
//        List<Item> items = (List<Item>) ((Order) session.getAttribute("order")).getItems();
//        order.setItems(items);
//        customerService.save(customer);
//        order.setCustomer(customer);
//        orderService.save(order);
//
//        for (Item item : items) {
//            item.setOrder(order);
//            itemService.save(item);
//        }
////        customerService.save(customer);
//        session.invalidate();
//
//
//        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
////        modelAndView.addObject("customer", customer);
//        modelAndView.addObject("message", "Bạn đã đặt hàng thành công");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete-item/{id}")
//    public ModelAndView removeItem(HttpServletRequest request, @PathVariable("id") Integer id) {
////        Optional<Product> product = productService.findById(id);
//        HttpSession session = request.getSession();
//        Order order = (Order) session.getAttribute("order");
//        List<Item> items = order.getItems();
//        if (items.size() != 0) {
//            try {
//                for (Item item : items) {
//                    if (item.getId() == id) {
//                        items.remove(item);
//                    }
//                }
//
//            } catch (ConcurrentModificationException e) {
//                System.out.println("lỗi");
//            }
//        }
//
//        order.setItems(items);
//        session.setAttribute("order", order);
//        ModelAndView modelAndView = new ModelAndView("redirect:/checkout");
//
//        return modelAndView;
//    }
//
//
    @GetMapping("/create-customer")
    public ModelAndView newRegister(@ModelAttribute("customer") Customer customer, HttpServletRequest request, @ModelAttribute("order") Order order, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("giaodien/customer");
        HttpSession session = request.getSession();
//        Order order = (Order) session.getAttribute("order");
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;
    }
@GetMapping("/addtocard/{id}")
public ModelAndView addToCard(@PathVariable("id") Integer id, HttpServletRequest request) {
    Integer quantity = 1;
    Optional<Product> product = productService.findById(id);
    HttpSession session = request.getSession();
    if (session.getAttribute("order") == null) {
        Order order = new Order();
        List<Item> items = new ArrayList<Item>();
        Item item = new Item();
        item.setId(id);
        item.setProduct(product.get());
        item.setQuantity(quantity);
        item.setPrice(product.get().getUnitPrice());
        items.add(item);
        order.setItems(items);
        session.setAttribute("order", order);

    } else {
        Order order = (Order) session.getAttribute("order");
        List<Item> items = order.getItems();
        boolean check = false;
        for (Item item : items) {
            if (item.getProduct().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                check = true;
            }
        }
        if (check == false) {
            Item item = new Item();
            item.setId(id);
            item.setQuantity(quantity);
            item.setPrice(product.get().getUnitPrice());
            item.setProduct(product.get());
            items.add(item);
        }
        session.setAttribute("order", order);
    }
    ModelAndView modelAndView = new ModelAndView("redirect:/member/home");
    modelAndView.addObject("session", session);
    return modelAndView;
}

    @GetMapping("/remove-item/{id}")
    public ModelAndView removeItem(HttpServletRequest request, @PathVariable("id") Integer id, Pageable pageable) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> items = order.getItems();
        if (items.size() != 0) {
            try {
//                for (Item item : items) {
//                    if (item.getId() == id) {
//                        items.remove(item);
//                    }
//                }
                Iterator iterator = items.iterator();
                while (iterator.hasNext()){
                    Item item = (Item) iterator.next();
                    if (item.getId().equals(id)) {
                        iterator.remove();
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Ơ. Lỗi");
            }
        }
        if (items.size() == 0) {
            return new ModelAndView("redirect:/member/home");
        }
        order.setItems(items);
        session.setAttribute("order", order);
        ModelAndView modelAndView = new ModelAndView("redirect:/checkout");
//        modelAndView.addObject("session", session);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);

        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;
    }

    @PostMapping("/save-order")
    public ModelAndView saveOrder(HttpServletRequest request, @ModelAttribute Customer customer) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> items = (List<Item>) ((Order) session.getAttribute("order")).getItems();
        order.setItems(items);
        customerService.save(customer);
        order.setCustomer(customer);
        Date date = new Date();
        String currentTime = new SimpleDateFormat("dd/MM/yyyy").format(date);
        order.setDateOrder(currentTime);
        orderService.save(order);

        for (Item item : items) {
            item.setOrder(order);
            itemService.save(item);
        }
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView("/giaodien/index");
        modelAndView.addObject("message", "Mua hàng thành công. Cửa hàng sẽ liên hệ cho bạn trong thời gian sớm nhất.");
        return modelAndView;
    }



}
