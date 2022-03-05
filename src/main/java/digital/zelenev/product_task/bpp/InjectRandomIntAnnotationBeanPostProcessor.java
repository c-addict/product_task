package digital.zelenev.product_task.bpp;

import digital.zelenev.product_task.annotations.InjectRandomInt;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt injectRandomIntAnnotation = field.getAnnotation(InjectRandomInt.class);
            if (injectRandomIntAnnotation != null) {
                int min = injectRandomIntAnnotation.min();
                int max = injectRandomIntAnnotation.max();
                int randomValue = RandomUtils.nextInt(min, max + 1);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, randomValue);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
