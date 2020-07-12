package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * ClassName: BeanDefinitionCreationDemo <br>
 * Description: {@link org.springframework.beans.factory.support.BeanDefinitionBuilder} 构建示例<br>
 * date: 2020/7/12 17:12<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanDefinitionCreationDemo {
  public static void main(String[] args) {
    // 1、通过 BeanDefinitionBuilder 构建
    BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(User.class);
    //    通过属性设置
    beanDefinitionBuilder.addPropertyValue("age", 25).addPropertyValue("name", "句号君");
    //    获取示例
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    //    BeanDefinition 并非 Bean 最终状态，可以自定义修改

    //    2、通过 AbstractBeanDefinition 以及派生类
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    // 设置 Bean 类型
    genericBeanDefinition.setBeanClass(User.class);
    //    通过 MutablePropertyValues 批量操作属性
    MutablePropertyValues propertyValues = new MutablePropertyValues();
    //    propertyValues.addPropertyValue("id", 1);
    //    propertyValues.addPropertyValue("name", "句号君");
    propertyValues.add("id", 1).add("name", "句号君");
    //    通过 MutablePropertyValues 批量操作属性
    genericBeanDefinition.setPropertyValues(propertyValues);
  }
}
