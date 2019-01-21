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
import shop.dongho.model.Customer;
import shop.dongho.model.Item;
import shop.dongho.model.Order;
import shop.dongho.model.Product;
import shop.dongho.service.CustomerService;
import shop.dongho.service.ItemService;
import shop.dongho.service.OrderService;
import shop.dongho.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AddToCardController {

    private CustomerService customerService;
    private OrderService orderService;
    private ItemService itemService;
    private ProductService productService;


    @Autowired
    public void setUpController(ProductService productService,
                                OrderService orderService,
                                ItemService itemService,
                                CustomerService customerService) {
        this.productService = productService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.itemService = itemService;
    }

//    @Autowired
//    private OrderDetailsService orderDetailsService;


    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

//    @GetMapping("/list")
//    public ModelAndView list(Pageable pageable) {
//        Page<Product> products = productService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

    @GetMapping("/addtocard/{id}")
    public ModelAndView addToCard(@PathVariable("id") Integer id, HttpServletRequest request) {
        long quantity = 1;
        Optional<Product> product = productService.findById(id);
        HttpSession session = request.getSession();
        if (session.getAttribute("order") == null) {
            Order order = new Order();
            List<Item> items = new ArrayList<Item>();
            Item item = new Item();
            item.setProduct(product.get());
            item.setQuantity(quantity);
            item.setPrice(product.get().getUnitPrice());
            items.add(item);
            order.setItems(items);
//            order = orderService.save(order);
//            itemService.save(item);
            System.out.println(order.getItems().size());


            session.setAttribute("order", order);
        } else {
            Order order = (Order) session.getAttribute("order");
            List<Item> items = order.getItems();
            boolean check = false;
            for (Item item : items) {
                if (item.getProduct().getId() == product.get().getId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    check = true;
                }
//                itemService.save(item);
            }
            if (check == false) {
                Item item = new Item();
                item.setQuantity(quantity);
                item.setPrice(product.get().getUnitPrice());
                item.setProduct(product.get());
                items.add(item);
//                itemService.save(item);
            }
            session.setAttribute("order", order);

        }
        ModelAndView modelAndView = new ModelAndView("redirect:/member/home");
        modelAndView.addObject("session", session);
        return modelAndView;
    }



    @PostMapping("/save-order")
    public ModelAndView saveOrder(HttpServletRequest request,@ModelAttribute Customer customer) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> items = (List<Item>) ((Order) session.getAttribute("order")).getItems();
        order.setItems(items);
        customerService.save(customer);
        order.setCustomer(customer);
        orderService.save(order);

        for (Item item : items) {
            item.setOrder(order);
            itemService.save(item);
        }
//        customerService.save(customer);


        ModelAndView modelAndView = new ModelAndView("/giaodien/home");
//        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/save-customer")
    public ModelAndView newRegister(@ModelAttribute("customer") Customer customer, HttpServletRequest request, @ModelAttribute("order") Order order) {
        ModelAndView modelAndView = new ModelAndView("giaodien/customer");
        HttpSession session = request.getSession();
//        Order order = (Order) session.getAttribute("order");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
//
//    @PostMapping("/save-customer")
//    public ModelAndView save(@ModelAttribute("customer") Customer customer) {
//        ModelAndView modelAndView = new ModelAndView("giaodien/register");
//        customerService.save(customer);
//
//        modelAndView.addObject("customer", customer);
//        return modelAndView;
//    }




}
