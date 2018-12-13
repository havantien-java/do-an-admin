package shop.dongho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.repository.UserRepository;
import shop.dongho.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<shop.dongho.model.User> findAllByContaining(String s, Pageable pageable) {
        return userRepository.findAllByNameContaining(s, pageable);
    }

    @Override
    public Page<shop.dongho.model.User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<shop.dongho.model.User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(shop.dongho.model.User user) {
        userRepository.save(user);

    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);

    }
}
