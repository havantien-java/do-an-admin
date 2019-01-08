package shop.dongho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.User;
import shop.dongho.repository.UserRepository;
import shop.dongho.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAllByNameContaining(String s, Pageable pageable) {
        return userRepository.findAllByNameContaining(s, pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User findByName(String username) {
        return  userRepository.findUserByEmail(username);
    }
}
