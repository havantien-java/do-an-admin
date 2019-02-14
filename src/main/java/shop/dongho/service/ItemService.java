package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Item;

import java.util.Optional;

public interface ItemService {
    Page<Item> findAll(Pageable pageable);

    Optional<Item> findById(Integer id);

    Item save(Item item);

    void remove(Integer id);

    Page<Item> findAllByOrder_Id(Integer id, Pageable pageable);
}
