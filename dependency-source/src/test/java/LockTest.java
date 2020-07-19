import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LockTest <br>
 * Description: 可重入锁的测试用例 <br>
 * date: 2020/7/19 18:03<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class LockTest {
  /** 线程可以在控制台打印信息，线程不会被锁住 */
  @Test
  public void reentryLockTest() {
    TestLock testLock = new TestLock();
    testLock.reentryrefresh("test", "1");
    System.out.println(testLock.getCache());
  }

  class TestLock {
    private Object lock = new Object();
    private Map<String, Object> cache = new HashMap<String, Object>();

    public void refresh(String key, Object val) {
      synchronized (lock) {
        cache.put(key, val);
      }
    }

    /**
     * 当前线程获取锁后，还可以通过 synchronized 关键字，重新获取线程的资源
     *
     * @param key
     * @param val
     */
    public void reentryrefresh(String key, Object val) {
      synchronized (lock) {
        refresh(key, val);
      }
    }

    public Map<String, Object> getCache() {
      return cache;
    }
  }
}
