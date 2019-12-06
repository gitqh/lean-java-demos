package springbootboilerplate.fixture;
import springbootboilerplate.domain.Product;
import springbootboilerplate.domain.feature.FeatureValue;

import java.util.Arrays;

public class ProductDataFixture {

    public static Product prepareProduct1(FeatureValue feature) {
        Product product = new Product();
        product.setName("小礼物");
        product.setDetail("小礼物详情");
        product.setFeatureValues(Arrays.asList(
                feature
        ));
        return product;
    }

    public static Product prepareProduct2(FeatureValue featureValue) {
        Product product = new Product();
        product.setName("小礼物2");
        product.setDetail("小礼物详情2");
        product.setFeatureValues(Arrays.asList(
                featureValue
        ));
        return product;
    }

}
