package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: NoUniqueBeanDefinitionExceptionDemo <br>
 * Description: {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException} 示例代码 <br>
 * date: 2020/7/16 22:20<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class NoUniqueBeanDefinitionExceptionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

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

  @Bean
  public String bean1() {
    return "bean1";
  }

  @Bean
  public String bean2() {
    return "bean2";
  }
}
