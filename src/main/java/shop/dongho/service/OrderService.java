package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Order;

import java.util.Optional;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Integer id);

    void save(Order order);

    void remove(Integer id);
}
