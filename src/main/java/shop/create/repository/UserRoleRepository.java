package shop.create.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import shop.create.model.UserRole;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {
    UserRole findByName(String name);
}
