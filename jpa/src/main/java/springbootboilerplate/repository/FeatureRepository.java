package springbootboilerplate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootboilerplate.domain.feature.Feature;
import springbootboilerplate.read.FeatureInterfaceProjection;
import springbootboilerplate.read.FeatureProjection;
import springbootboilerplate.read.FeatureProjectionWithValues;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>,
        JpaSpecificationExecutor<Feature> {

    @Query("select distinct new springbootboilerplate.read.FeatureProjection(f.name) from Feature f")
    FeatureProjection findFirstByName(String name);

    List<FeatureProjection> findAllByName(String name);

    FeatureInterfaceProjection findDistinctFirstByName(String name);

    @Query("select avg(feature.step),sum(feature.id) from Feature feature group by feature.step")
    List<Object[]> aggregateTest();


    @Query("select distinct feature from Feature feature inner join fetch feature.featureValues")
    List<Feature> findFeatureWithJoin();

    @Modifying
    @Query("delete from Feature")
    void batchDeleteAll();

    @Modifying
    @Query("update Feature feature set feature.name = ?1")
    void batchUpdateName(String newName);
}
