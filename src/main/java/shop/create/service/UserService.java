package shop.create.service;

import shop.create.model.User;

public interface UserService {
//    Page<User> findAllByNameContaining(String s, Pageable pageable);
//
//    Page<User> findAll(Pageable pageable);
//
//    Optional<User> findById(Integer id);

    void save(User user);

    void remove(Integer id);

//    User findByName(String username);

}
