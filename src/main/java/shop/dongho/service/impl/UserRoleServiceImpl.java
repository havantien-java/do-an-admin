package shop.dongho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import shop.dongho.model.UserRole;
import shop.dongho.repository.UserRoleRepository;
import shop.dongho.service.UserRoleService;

import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findById(Integer id) {
        return userRoleRepository.findById(id);
    }
}
