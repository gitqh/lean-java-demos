package springbootboilerplate.fixture;

import springbootboilerplate.domain.Role;

import java.sql.Timestamp;

public class RoleDataFixture {

    public static Role prepareAdminRole() {
        Role role = new Role();
        role.setName("admin");
        role.setRemark("测试admin");
        role.setCreateAt(new Timestamp(System.currentTimeMillis()));
        role.setUpdateAt(new Timestamp(System.currentTimeMillis()));

        return role;
    }

    public static Role prepareUserRole() {
        Role role = new Role();
        role.setName("user");
        role.setRemark("测试用户");
        role.setCreateAt(new Timestamp(System.currentTimeMillis()));
        role.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return role;
    }
}
