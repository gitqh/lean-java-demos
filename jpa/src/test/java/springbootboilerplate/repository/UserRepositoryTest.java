package springbootboilerplate.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springbootboilerplate.domain.User;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(10, users.size());
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
}
