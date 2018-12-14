package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Producer;

import java.util.Optional;

public interface ProducerService {
    Page<Producer> findByNameContaining(String s, Pageable pageable);

    Page<Producer> findAll(Pageable pageable);

    Optional<Producer> findById(Long id);

    void save(Producer producer);

    void remove(Long id);
}
