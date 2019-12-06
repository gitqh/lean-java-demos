package springbootboilerplate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootboilerplate.domain.feature.Feature;
import springbootboilerplate.read.FeatureInterfaceProjection;
import springbootboilerplate.read.FeatureProjection;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>,
        JpaSpecificationExecutor<Feature> {

    @Query("select distinct new springbootboilerplate.read.FeatureProjection(f.name) from Feature f")
    FeatureProjection findFirstByName(String name);

    List<FeatureProjection> findAllByName(String name);

    FeatureInterfaceProjection findDistinctFirstByName(String name);
}
