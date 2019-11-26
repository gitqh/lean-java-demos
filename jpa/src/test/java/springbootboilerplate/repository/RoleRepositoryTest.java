package springbootboilerplate.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import springbootboilerplate.domain.Role;
import springbootboilerplate.domain.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "role");
    }

    @Test
    public void shouldInsertDataIntoDatabase() {
        roleRepository.save(Role.builder().name("10").remark("remark").build());

        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "role"));
    }

    @Test
    public void shouldInsertEventUsersHere() {
        Set<User> users = new HashSet<>();
        users.add(User.builder().id(1L).build());

        roleRepository.save(Role
                .builder()
                .name("10")
                .remark("remark").build()
        );

        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "role"));
    }

    @Test
    public void shouldFindList() {
        roleRepository.save(Role.builder().name("10").remark("remark").build());
        List<Role> users = roleRepository.findAll();

        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "role"));
    }
}
