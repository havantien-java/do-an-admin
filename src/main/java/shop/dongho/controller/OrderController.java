package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Order;
import shop.dongho.service.OrderService;


@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list-order")
    public ModelAndView listOrder(Pageable pageable, @ModelAttribute("s") String s) {
        Page<Order> orders;
         orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/order/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;

    }

    @GetMapping("/search-order")
    public ModelAndView searchDateOrder(Pageable pageable, @ModelAttribute("dateOrder") String dateOrder) {
        Page<Order> orders;
        if (dateOrder == null) {
            orders = orderService.findAll(pageable);
        } else {
            orders = orderService.findALlByDateOrder(dateOrder, pageable);

        }
        ModelAndView modelAndView = new ModelAndView("/order/list");
        modelAndView.addObject("orders", orders);
        if (orders.isEmpty()) {
            modelAndView.addObject("message", "không có hóa đươn trong ngày này");
        }
        return modelAndView;

    }


}
