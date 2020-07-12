package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.annotation.Super;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * ClassName: BeanFactoryIoCContainerDemo <br>
 *
 * <p>Description: BeanFactory {@link BeanFactory} 作为IoC 容器示例< br>
 *
 * <p>date: 2020/7/12 11:03<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanFactoryIoCContainerDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    //    XMl 配置文件 ClassPath 路径
    String location = "classpath:/META-INF/dependency-lookup-context.xml";
    //    加载配置
    int beanDefinitionsCount = reader.loadBeanDefinitions(location);
    System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);

    lookupByAnnotationType(beanFactory);
  }

  /** 通过注解类型查找 */
  private static void lookupByAnnotationType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
      System.out.println("查找标注 @Super 所有的 User 集合对象：" + users);
    }
  }
}
