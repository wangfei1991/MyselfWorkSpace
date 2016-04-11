package www.example.com.greendao.modle.www.example.com.greendao.modle.db;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangfei on 16-4-11.
 * @param <T> Entity type
 */
public class IndentityCacheLong<T> implements IndentityCache<Long,T> {

    private ReentrantLock mReentrantLock;
    private Map<Long, T> mCacheMap;
    public IndentityCacheLong() {
        this.mReentrantLock = new ReentrantLock();
        mCacheMap = new HashMap<>();
    }

    @Override
    public T get(Long key) {
        return mCacheMap.get(key);
    }

    @Override
    public void put(Long key, T entity) {
        mCacheMap.put(key, entity);
    }

    @Override
    public void remove(Long key) {

    }

    @Override
    public void remove(Iterable<Long> key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void lock() {
        mReentrantLock.lock();
    }

    @Override
    public void unlock() {
        mReentrantLock.unlock();
    }
}
