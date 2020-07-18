package org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * ClassName: UserGroup <br>
 * Description: 用户组注解<，拓展 {@link org.springframework.beans.factory.annotation.Qualifier}br> date:
 * 2020/7/18 11:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Target({
  ElementType.FIELD,
  ElementType.METHOD,
  ElementType.PARAMETER,
  ElementType.TYPE,
  ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier // 对 Bean 进行分组
public @interface UserGroup {}
