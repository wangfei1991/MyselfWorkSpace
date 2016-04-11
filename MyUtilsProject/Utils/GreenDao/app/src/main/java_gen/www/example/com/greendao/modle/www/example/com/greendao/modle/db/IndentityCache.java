package www.example.com.greendao.modle.www.example.com.greendao.modle.db;

/**
 * Created by wangfei on 16-4-11.
 * cache data,
 * @param <K> primary key type
 * @param <T> entity type
 */
public interface IndentityCache<K, T> {
    T get(K key);

    void put(K key, T entity);

    void remove(K key);

    void remove(Iterable<K> key);

    void clear();

    void lock();

    void unlock();
}
