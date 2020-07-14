package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: ObjectProviderDemo <br>
 * Description: 通过 {@link ObjectProvider} 进行依赖查找 <br>
 * date: 2020/7/13 22:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class HierarchicalDependencyLookupDemo { // @Configuration 是非必须的注解

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(HierarchicalDependencyLookupDemo.class);

    // 1、获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    //    System.out.println(
    //        "当前 BeanFactory 的 Parent  BeanFactory ： " + beanFactory.getParentBeanFactory());

    // 2、设置 Parent BeanFactory
    HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
    beanFactory.setParentBeanFactory(parentBeanFactory);

    displayContainsLocalBean(beanFactory, "user");
    displayContainsLocalBean(parentBeanFactory, "user");

    displayContainsBean(beanFactory, "user");
    displayContainsBean(parentBeanFactory, "user");

    //    System.out.println(
    //        "当前 BeanFactory 的 Parent  BeanFactory ： " + beanFactory.getParentBeanFactory());
    //  启动 Spring
    applicationContext.refresh();

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
    System.out.println(
        String.format(
            "BeanFactory[%s]是否包含 bean[name : %s] : %s",
            beanFactory, beanName, containsBean(beanFactory, beanName)));
  }

  /**
   * 借鉴{@link
   * BeanFactoryUtils#beansOfTypeIncludingAncestors(org.springframework.beans.factory.ListableBeanFactory,
   * java.lang.Class)}
   */
  private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
    BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
    if (parentBeanFactory instanceof HierarchicalBeanFactory) {
      HierarchicalBeanFactory hierarchicalBeanFactory =
          HierarchicalBeanFactory.class.cast(parentBeanFactory);
      if (hierarchicalBeanFactory.containsLocalBean(beanName)) {
        return true;
      }
    }
    return beanFactory.containsLocalBean(beanName);
  }

  private static void displayContainsLocalBean(
      HierarchicalBeanFactory beanFactory, String beanName) {
    System.out.println(
        String.format(
            "当前 BeanFactory[%s]是否包含 bean[name : %s] : %s",
            beanFactory, beanName, beanFactory.containsLocalBean(beanName)));
  }

  private static HierarchicalBeanFactory createParentBeanFactory() {
    // 创建 BeanFactory 容器
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    //    XMl 配置文件 ClassPath 路径
    String location = "classpath:/META-INF/dependency-lookup-context.xml";
    //    加载配置
    reader.loadBeanDefinitions(location);
    return beanFactory;
  }
}
