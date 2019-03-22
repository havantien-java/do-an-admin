package shop.create.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import shop.create.model.User;
import shop.create.repository.UserRepository;
import shop.create.service.UserService;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Page<User> findAllByNameContaining(String s, Pageable pageable) {
//        return userRepository.findAllByNameContaining(s, pageable);
//    }
//
//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }
//
//    @Override
//    public Optional<User> findById(Integer id) {
//        return userRepository.findById(id);
//    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);

    }

//    @Override
//    public User findByName(String username) {
//        return  userRepository.findUserByEmail(username);
//    }
}
