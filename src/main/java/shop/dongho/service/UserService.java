package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.User;

import java.util.Optional;

public interface UserService {
    Page<User> findAllByNameContaining(String s, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Integer id);

    void save(User user);

    void remove(Integer id);

    User findByName(String username);

}
