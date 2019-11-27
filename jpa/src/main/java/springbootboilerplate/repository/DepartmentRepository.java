package springbootboilerplate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springbootboilerplate.domain.Department;

import java.util.List;
import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long>,
        JpaSpecificationExecutor<Department> {

    @Query("select d from Department d where d.name = :name")
    Set<Department> departmentsByName(@Param("name") String name);
}
