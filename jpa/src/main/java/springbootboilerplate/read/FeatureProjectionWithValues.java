package springbootboilerplate.read;

import springbootboilerplate.domain.feature.FeatureValue;

import java.util.List;

public class FeatureProjectionWithValues {
    private String name;
    private List<FeatureValue> featureValues;

    public FeatureProjectionWithValues(String name, List<FeatureValue> featureValues) {
        this.name = name;
        this.featureValues = featureValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FeatureValue> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(List<FeatureValue> featureValues) {
        this.featureValues = featureValues;
    }
}
