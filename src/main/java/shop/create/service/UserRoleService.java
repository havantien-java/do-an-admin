package shop.create.service;

import shop.create.model.UserRole;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findById(Integer id);
}
