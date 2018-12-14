package shop.dongho.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Producer;
import shop.dongho.repository.ProducerRepository;
import shop.dongho.service.ProducerService;

import java.util.Optional;

public class ProducerServiceImpl implements ProducerService {
@Autowired
    private ProducerRepository producerRepository;

    @Override
    public Page<Producer> findByNameContaining(String name, Pageable pageable) {
        return producerRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Producer> findAll(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void remove(Long id) {
        producerRepository.deleteById(id);
    }
}
