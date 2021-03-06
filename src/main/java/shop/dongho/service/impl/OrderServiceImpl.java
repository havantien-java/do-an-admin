package shop.dongho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Order;
import shop.dongho.repository.OrderRepository;
import shop.dongho.service.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> remove(Integer id) {
        orderRepository.deleteById(id);

        return null;
    }

    @Override
    public Page<Order> findALlByDateOrder(String date, Pageable pageable) {
        return orderRepository.findALlByDateOrder(date, pageable);
    }

}
