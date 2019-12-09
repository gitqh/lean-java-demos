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
import springbootboilerplate.read.FeatureProjectionWithValues;
import springbootboilerplate.repository.FeatureRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
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

    @Test
    @Transactional
    public void shouldQueryFeatureWithJPQLAggregation() {
        // 在 JPQL 中使用聚合函数
        Feature ageFeature = FeatureDataFixture.prepareAgeFeature();
        Feature genderFeature = FeatureDataFixture.prepareGenderFeature();

        entityManager.persist(ageFeature);
        entityManager.persist(genderFeature);

        List<Object[]> objects = featureRepository.aggregateTest();

        for (Object[] object : objects)
            System.out.println(Arrays.toString(object));
    }

    @Test
    @Transactional
    public void shouldQueryFeatureWithJoin() {
        // 在 JPQL 中使用聚合函数
        Feature ageFeature = FeatureDataFixture.prepareAgeFeature();
        Feature genderFeature = FeatureDataFixture.prepareGenderFeature();

        entityManager.persist(ageFeature);
        entityManager.persist(genderFeature);

        List<Feature> features = featureRepository.findFeatureWithJoin();

        System.out.println(features);
    }

    @Test
    @Transactional
    public void shouldBatchDelete() {
        //批量删除

        Feature ageFeature = FeatureDataFixture.prepareAgeFeature();
        Feature genderFeature = FeatureDataFixture.prepareGenderFeature();

        entityManager.persist(ageFeature);
        entityManager.persist(genderFeature);

        featureRepository.batchDeleteAll();

        assertEquals(0, featureRepository.count());
    }

    @Test
    @Transactional
    public void shouldBatchUpdate() {
        //批量更新

        Feature ageFeature = FeatureDataFixture.prepareAgeFeature();
        Feature genderFeature = FeatureDataFixture.prepareGenderFeature();

        entityManager.persist(ageFeature);
        entityManager.persist(genderFeature);

        featureRepository.batchUpdateName("新名字");

        assertEquals(3, featureRepository.findAllByName("新名字").size());
    }

    @Test
    @Transactional
    public void queryByCriteria() {
        //使用 Criteria 查询

        Feature ageFeature = FeatureDataFixture.prepareAgeFeature();
        Feature genderFeature = FeatureDataFixture.prepareGenderFeature();

        entityManager.persist(ageFeature);
        entityManager.persist(genderFeature);

        // 获取 builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 创建 query
        CriteriaQuery<Feature> query = criteriaBuilder.createQuery(Feature.class);
        // 获取根对象，根对象可以有多个
        Root<Feature> root = query.from(Feature.class);

        // 查询大于 1 的条件
        Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("id"), 1L);
        Predicate predicate1 = criteriaBuilder.equal(root.get("name"), "年龄");

        query.where(criteriaBuilder.and(predicate, predicate1));

        // 执行构造出的条件

        List<Feature> resultList = entityManager.createQuery(query).getResultList();

        assertEquals(3, resultList.size());
    }
}
