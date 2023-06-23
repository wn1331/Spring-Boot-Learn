package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있을 때는 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeDuplicateSolution(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanByChild(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모든 자식 조회")
    void findAllChildBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for(String key:beansOfType.keySet()){
            System.out.println("key : " + key+" value: " + beansOfType.get(key));
        }
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모든 자식 Object로 조회")
    void findAllChildBeanByParentTypeObject(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key:beansOfType.keySet()){
            System.out.println("key : " + key+" value: " + beansOfType.get(key));
        }
        //엄청 많이 조회된다. bean에 등록된 객체들은 전부 object 타입이기 때문.
//        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
