package com.bootdo.common.service.impl;

import com.bootdo.common.service.RedisService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lihq on 2018/10/11 14:19
 * Description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Resource
    private RedisConnectionFactory connectionFactory;

    @Autowired
    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisTemplate<Serializable, Object> getRedisTemplate() {
        RedisTemplate<Serializable, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }

    public RedisServiceImpl() {

    }

    @Override
    public void sendMessage(String channel, Serializable message) {

    }

    @Override
    public void remove(String... keys) {

    }

    @Override
    public void removePattern(String pattern) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public boolean set(String key, Object value) {
        return false;
    }

    @Override
    public Object getSet(String key) {
        return null;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        boolean result = false;

        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        result = true;
        return result;
    }

    @Override
    public boolean setHash(String key, String hashKey, Object hashValue, Long time) {
        return false;
    }

    @Override
    public boolean setAdd(String key, String value) {
        return false;
    }

    @Override
    public boolean removeSetValue(String key, String value) {
        return false;
    }

    @Override
    public Object getHash(String key, String hashKey) {
        return null;
    }

    @Override
    public Object hmGet(String key, List<String> hashKey) {
        return null;
    }

    @Override
    public boolean setList(String key, Object Value, Long time) {
        boolean result = false;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            operations.leftPush(key, Value);
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean hsetList(String key, String[] value, Long time) {
        return false;
    }

    @Override
    public List<Object> getList(String key) {
        return null;
    }

    @Override
    public Boolean tryLock(String key, String value, Long timeout) {
        return null;
    }

    @Override
    public void unLock(String key) {

    }

    @Override
    public <T> void hmset(String key, T t) {

    }

    @Override
    public Map<String, String> hmget(String key, String... fields) {
        return null;
    }

    @Override
    public void hset(String key, String field, String value) {

    }

    @Override
    public String hget(String key, String fields) {
        return null;
    }

    @Override
    public Long hDelete(String key, String fields) {
        return null;
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return null;
    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, long value) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long decrBy(String key, long value) {
        return null;
    }

    @Override
    public Long getIncrOrDecrValue(String key) {
        return null;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deletePrefix(String key) {

    }
}
