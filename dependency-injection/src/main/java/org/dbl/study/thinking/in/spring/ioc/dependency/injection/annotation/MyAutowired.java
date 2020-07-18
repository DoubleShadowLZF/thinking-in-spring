package org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * ClassName: MyAutowired <br>
 * Description: 自定义注解（元标注 {@link Autowired} ）<br>
 * date: 2020/7/19 0:49<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {
  /**
   * Declares whether the annotated dependency is required.
   *
   * <p>Defaults to {@code true}.
   */
  boolean required() default true;
}
