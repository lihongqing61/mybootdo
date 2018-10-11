package com.bootdo.common.redis.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Lihq on 2018/10/11 9:38
 * Description:
 */
public class RedisCache<K, V> implements Cache<K, V> {

    /**
     * The wrapped Jedis instance.
     */
    private RedisManager cache;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_redis_session:";


    @Override
    public V get(K key) throws CacheException {
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        return null;
    }

    @Override
    public V remove(K key) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public RedisCache(RedisManager cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    public RedisCache(RedisManager cache, String prefix) {
        this(cache);
        this.keyPrefix = prefix;
    }
}
