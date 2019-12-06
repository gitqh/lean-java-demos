package springbootboilerplate.jpaquery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import springbootboilerplate.FeatureDataFixture;
import springbootboilerplate.domain.Role;
import springbootboilerplate.domain.feature.Feature;
import springbootboilerplate.repository.FeatureRepository;
import springbootboilerplate.repository.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleHibernateQueryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    private Role savedRole;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "role");

        savedRole = roleRepository.save(Role.builder().name("10").remark("remark").build());
    }

    @Test
    public void shouldQueryRoleWithHibernateById() {
        Role role = entityManager.find(Role.class, savedRole.getId());
        assertEquals("remark", role.getRemark());
    }

}
