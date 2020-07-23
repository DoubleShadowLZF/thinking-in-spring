package org.dbl.study.thinking.in.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import reactor.util.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ThreadLocalScope <br>
 * Description: ThreadLocal 级别 Scope <br>
 * date: 2020/7/23 20:03<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class ThreadLocalScope implements Scope {
  public static final String SCOPE_NAME = "thread-local";

  private final NamedThreadLocal<Map<String, Object>> threadLocal =
      new NamedThreadLocal<Map<String, Object>>("thread-local-scope") {
        @Override
        protected Map<String, Object> initialValue() {
          return new HashMap<>();
        }
      };

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {

    Map<String, Object> context = getContext();
    Object obj = context.get(name);
    if (obj == null) {
      obj = objectFactory.getObject();
      context.put(name, obj);
    }
    return obj;
  }

  @NonNull
  private Map<String, Object> getContext() {
    return threadLocal.get();
  }

  @Override
  public Object remove(String name) {
    Map<String, Object> context = getContext();
    return context.remove(name);
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    // TODO
  }

  @Override
  public Object resolveContextualObject(String key) {
    Map<String, Object> context = getContext();
    return context.get(key);
  }

  @Override
  public String getConversationId() {
    Thread thread = Thread.currentThread();
    return String.valueOf(thread.getId());
  }
}
