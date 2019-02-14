package shop.dongho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.dongho.model.Item;
import shop.dongho.model.Order;
import shop.dongho.service.ItemService;
import shop.dongho.service.OrderService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/list-order")
    public ModelAndView listOrder(Pageable pageable) {
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
            modelAndView.addObject("message", "không có hóa đơn trong ngày này");
        }
        return modelAndView;

    }

    @GetMapping("edit-order/{id}")
    public ModelAndView editBill(@PathVariable Integer id,@PageableDefault(size = 30) Pageable pageable) {
        Optional<Order> order = orderService.findById(id);
        Page<Item> items = itemService.findAllByOrder_Id(id, pageable);
        if (order.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("order/edit");
            modelAndView.addObject("order", order);
            modelAndView.addObject("items", items);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-order")
    public ModelAndView editBill(@PageableDefault Pageable pageable, @ModelAttribute("order") Order order) {
        orderService.save(order);
        Page<Order> orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("order/list");
        modelAndView.addObject("order", order);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/delete-order/{id}")
    public ModelAndView showDeleteBillForm(@PageableDefault(size = 10) Pageable pageable, @PathVariable Integer id) {
        Optional<Order> order = orderService.findById(id);
        List<Item> items = order.get().getItems();
        for (Item item : items) {
            itemService.remove(item.getId());
        }
        orderService.remove(id);
        Page<Order> orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/order/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;

    }
    @GetMapping("print-order/{id}")
    public ModelAndView printBill(@PathVariable Integer id,@PageableDefault(size = 30) Pageable pageable) {
        Optional<Order> order = orderService.findById(id);
        Page<Item> items = itemService.findAllByOrder_Id(id, pageable);
        if (order.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("order/print");
            modelAndView.addObject("order", order);
            modelAndView.addObject("items", items);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

}
