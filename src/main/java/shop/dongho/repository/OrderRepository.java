package shop.dongho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
