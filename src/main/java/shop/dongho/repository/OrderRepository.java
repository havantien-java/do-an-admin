package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Order;

import java.util.Date;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    Page<Order> findALlByDateOrder(String dateOrder, Pageable pageable);
}
