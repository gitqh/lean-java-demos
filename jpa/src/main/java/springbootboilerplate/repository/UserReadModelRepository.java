package springbootboilerplate.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import springbootboilerplate.read.UserReadModel;

import java.util.List;

@Repository
public interface UserReadModelRepository extends JpaRepository<UserReadModel, Long>,
        JpaSpecificationExecutor<UserReadModel> {

    @EntityGraph(value = "userReadModel.department" , type= EntityGraph.EntityGraphType.FETCH)
    List<UserReadModel> findAll(@Nullable Specification<UserReadModel> spec);
}
