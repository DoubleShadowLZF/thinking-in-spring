package org.dbl.study.thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: BeanGarbageCollectionDemo <br>
 * Description: Bean 垃圾回收示例< br> date: 2020/7/13 0:40<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanGarbageCollectionDemo {
  public static void main(String[] args) throws InterruptedException {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class）
    applicationContext.register(BeanInitializationDemo.class);
    //    启动应用上下文
    applicationContext.refresh();
    //    关闭应用上下文
    applicationContext.close();
    //    强制触发 GC
    System.gc();
    Thread.sleep(5000);
    System.gc();
    Thread.sleep(5000);
  }
}
