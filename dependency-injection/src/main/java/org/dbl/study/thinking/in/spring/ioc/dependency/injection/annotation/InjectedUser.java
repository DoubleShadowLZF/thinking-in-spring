package org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * ClassName: InjectedUser <br>
 * Description: 自定义依赖注解 <br>
 * date: 2020/7/19 0:53<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {}
