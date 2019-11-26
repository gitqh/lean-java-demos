package springbootboilerplate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import springbootboilerplate.domain.Role;
import springbootboilerplate.domain.User;

@Component
public interface RoleRepository extends JpaRepository<Role, Long>,
        JpaSpecificationExecutor<User> {
}
