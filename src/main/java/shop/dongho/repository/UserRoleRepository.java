package shop.dongho.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.UserRole;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {
}
