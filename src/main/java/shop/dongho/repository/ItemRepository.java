package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    Page<Item> findAllByOrder_Id(Integer id,Pageable pageable);
}
