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
import springbootboilerplate.read.UserReadModelTwo;

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
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReadModelRepository userReadModelRepository;

    @Autowired
    UserReadModelTwoRepository userReadModelTwoRepository;

    @Test
    @Transactional
    public void testFindAll() {
        List<User> users = userRepository.findAll();
    }

    @Test
    public void testDeleteOneToMany() {
        User user = userRepository.findById(1L).get();

        user.setRoles(null);
        userRepository.saveAndFlush(user);
    }

    @Test
    public void testDeleteWholeAggregate() {
        User user = userRepository.findById(1L).get();
        userRepository.delete(user);
    }

    @Test
    public void testSpecification() {
        Specification<UserReadModel> specification = new Specification<UserReadModel>() {

            @Override
            public Predicate toPredicate(Root<UserReadModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.like(root.get("username").as(String.class), "%" + "admin" + "%"));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            };
        };

        List<UserReadModel> users = userReadModelRepository.findAll(specification);
    }

    @Test
    public void testJoin() {
        Specification<UserReadModel> specification = new Specification<UserReadModel>() {

            @Override
            public Predicate toPredicate(Root<UserReadModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.like(root.get("username").as(String.class), "%" + "admin" + "%"));

                Join<UserReadModel, Department> join = root.join("department", JoinType.INNER);
                list.add(
                        cb.or(
                                cb.like(join.get("name").as(String.class),"研发部"),
                                cb.like(join.get("name").as(String.class),"测试部")
                        )
                );

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            };
        };

        List<UserReadModel> users = userReadModelRepository.findAll(specification);
    }

    @Test
    public void saveWithRelationShip1() {
        User user = User.builder().email("testemail").password("").phone("13668").build();
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.builder().id(1L).build());

        user.setDepartment(Department.builder().name("测试部门").id(2L).build()).setRoles(roles);
        userRepository.save(user);
    }

    @Test
    public void saveWithFullRelationShip() {
        User user = User.builder().email("testemail1"+ UUID.randomUUID().toString()).password("").phone("13668").build();
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.builder().id(1L).build());

        user.setDepartment(Department.builder().name("测试部门").id(2L).build()).setRoles(roles);
        user.setUserAvatar(UserAvatar.builder().path("xxx").realName("user_name").build());
        userRepository.save(user);
    }

    @Test
    public void testFindByDefind() {
        Optional<UserReadModelTwo> userReadModelTwo  = userReadModelTwoRepository.findByDefind(1L);
    }
}
