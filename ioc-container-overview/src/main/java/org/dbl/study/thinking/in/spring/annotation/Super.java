package org.dbl.study.thinking.in.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Super <br>
 * Description: 超级<br>
 * date: 2020/7/11 18:39<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {}
