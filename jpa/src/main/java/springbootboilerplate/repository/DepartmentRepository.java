package springbootboilerplate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import springbootboilerplate.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>,
        JpaSpecificationExecutor<Department> {
}
