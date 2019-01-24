package shop.dongho.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.User;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Page<User> findAllByNameContaining(String s, Pageable pageable);
    User findUserByEmail(String name);
    User findByEmail(String name);
}