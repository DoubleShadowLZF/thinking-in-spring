package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * ClassName: AutoWiringByNameDependencySetterInjectionDemo <br>
 * Description: "byName" Auto-wiring 依赖 Setter 方法注入示例<br>
 * date: 2020/7/17 0:49<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 依赖查找并且创建 Bean
    UserHolder userHolder = beanFactory.getBean(UserHolder.class);
    System.out.println(userHolder);
  }
}
