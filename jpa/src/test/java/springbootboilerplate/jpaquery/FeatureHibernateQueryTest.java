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
import springbootboilerplate.domain.feature.Feature;
import springbootboilerplate.read.FeatureInterfaceProjection;
import springbootboilerplate.read.FeatureProjection;
import springbootboilerplate.repository.FeatureRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureHibernateQueryTest {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    private Feature savedFeature;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "feature");

        savedFeature = featureRepository.save(FeatureDataFixture.prepareAgeFeature());
    }

    @Test
    public void shouldQueryFeatureWithHibernateById() {
        Feature feature = entityManager.find(Feature.class, savedFeature.getId());
        assertEquals("remark", feature.getName());
    }

    @Test
    @Transactional
    public void shouldQueryFeatureWithHibernateByRelationship() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        Feature feature = entityManager.find(Feature.class, feature1.getId());

        assertEquals(2, feature.getFeatureValues().size());
    }

    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQL() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        Query query = entityManager.createQuery("select f from Feature f");
        List<Feature> list = query.getResultList();

        assertEquals(1, list.size());
    }


    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQLProjection() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        Query query = entityManager.createQuery("" +
                "select feature.name from Feature feature");

        List<String> list = query.getResultList();

        assertEquals(1, list.size());
    }


    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQLProjectionInterface() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        FeatureInterfaceProjection featureInterfaceProjection = featureRepository.findDistinctFirstByName("年龄");

        assertEquals("年龄", featureInterfaceProjection.getName());
    }


    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQLProjectionObject() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        List<FeatureProjection> list = featureRepository.findAllByName("年龄");

        assertEquals("年龄", list.get(1).getName());
    }

    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQLProjectionObjectJPQL() {
        Feature feature1 = FeatureDataFixture.prepareAgeFeature();
        entityManager.persist(feature1);

        FeatureProjection featureProjection = featureRepository.findFirstByName("年龄");

        assertEquals("年龄", featureProjection.getName());
    }
}
