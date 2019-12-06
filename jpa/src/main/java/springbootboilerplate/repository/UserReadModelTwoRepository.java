package springbootboilerplate.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import springbootboilerplate.read.UserReadModel;
import springbootboilerplate.read.UserReadModelTwo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReadModelTwoRepository extends JpaRepository<UserReadModelTwo, Long>,
        JpaSpecificationExecutor<UserReadModelTwo> {
        @Query(value = "select u from UserReadModelTwo u l" +
                "eft join Department d on  u.departId = d.id where u.id = ?1")
    Optional<UserReadModelTwo> findByDefind(Long id);
}
