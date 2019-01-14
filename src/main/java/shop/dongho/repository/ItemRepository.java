package shop.dongho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
}
