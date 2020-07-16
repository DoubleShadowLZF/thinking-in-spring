package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: BeanInstantiationExceptionDemo <br>
 * Description: {@link org.springframework.beans.BeanInstantiationException} 示例<br>
 * date: 2020/7/16 22:28<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanInstantiationExceptionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 BeanDefinition Bean Class 是一个 CharSequence 接口
    BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
    applicationContext.registerBeanDefinition(
        "errorBean", beanDefinitionBuilder.getBeanDefinition());

    //  启动 Spring
    applicationContext.refresh();

    try {
      applicationContext.getBean(String.class);
    } catch (NoUniqueBeanDefinitionException e) {
      System.err.printf(
          " Spring 应用上下文存在 %d 个 %s 类型的 Bea， 具体原因： %s",
          e.getNumberOfBeansFound(), String.class.getName(), e.getMessage());
    }

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }
}
