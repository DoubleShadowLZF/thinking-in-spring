package org.dbl.study.thinking.in.spring.bean.scope;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * ClassName: ThreadLocalScopeDemo <br>
 * Description: <br>
 * date: 2020/7/23 20:12<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class ThreadLocalScopeDemo {

  @Bean
  @Scope(ThreadLocalScope.SCOPE_NAME)
  public User user() {
    return createUser();
  }

  private static User createUser() {
    User user = new User();
    user.setId(System.nanoTime());
    return user;
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(ThreadLocalScopeDemo.class);

    applicationContext.addBeanFactoryPostProcessor(
        beanFactory -> {
          // 注册自定义 scope
          beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

    //  启动 Spring
    applicationContext.refresh();

    scopeBeanByLookup(applicationContext);

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
    for (int i = 0; i < 3; i++) {
      new Thread() {
        @Override
        public void run() {
          // singletonUser 是共享 Bean 对象
          User singletonUser = applicationContext.getBean("user", User.class);
          System.out.printf(
              "Thread id : %d user = %s%n", Thread.currentThread().getId(), singletonUser);
        }
      }.start();
    }
  }
}
