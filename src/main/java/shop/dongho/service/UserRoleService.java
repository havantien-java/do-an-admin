package shop.dongho.service;

import shop.dongho.model.UserRole;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findById(Integer id);
}
