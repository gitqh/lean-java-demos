package springbootboilerplate.fixture;

import springbootboilerplate.domain.feature.Feature;
import springbootboilerplate.domain.feature.FeatureValue;

import java.util.Arrays;
import java.util.List;

public class FeatureDataFixture {

    public static List<Feature> prepareFeatures() {
        return Arrays.asList(
                prepareAgeFeature(),
                prepareGenderFeature()
        );
    }

    public static Feature prepareAgeFeature() {
        Feature ageFeature = new Feature();
        ageFeature.setName("年龄");
        ageFeature.setStep(2);

        ageFeature.setFeatureValues(Arrays.asList(
                new FeatureValue(null, "10-20", ageFeature),
                new FeatureValue(null, "20-30", ageFeature)
        ));
        return ageFeature;
    }

    public static Feature prepareGenderFeature() {
        Feature genderFeature = new Feature();
        genderFeature.setName("性别");
        genderFeature.setStep(1);
        genderFeature.setFeatureValues(Arrays.asList(
                new FeatureValue(null, "male", genderFeature),
                new FeatureValue(null, "female", genderFeature)
        ));
        return genderFeature;
    }
}
