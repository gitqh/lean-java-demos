package springbootboilerplate.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import springbootboilerplate.domain.Department;
import springbootboilerplate.domain.Role;
import springbootboilerplate.domain.User;
import springbootboilerplate.domain.UserAvatar;
import springbootboilerplate.read.UserReadModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTest {


    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void testJPQL() {
        Set<Department> departments = departmentRepository.departmentsByName("研发部");
    }
}
